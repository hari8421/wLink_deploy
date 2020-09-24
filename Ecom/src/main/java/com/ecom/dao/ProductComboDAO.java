package com.ecom.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecom.common.CommonFunctions;
import com.ecom.common.CommonMethodsDAO;
import com.ecom.domain.BannerHdrDtl;
import com.ecom.domain.ProductComboDtl;
import com.ecom.domain.ProductComboHdr;
import com.ecom.domain.ProductComboResponse;
import com.ecom.domain.ProductSubHdrDtl;
import com.ecom.rowmapper.BannerHdrDtlRowMapper;
import com.ecom.rowmapper.ProductComboDtlRowMapper;
import com.ecom.rowmapper.ProductComboHdrRowMapper;
import com.ecom.rowmapper.ProductSubHdrDtlRowMapper;


@Repository
@Transactional
public class ProductComboDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProductComboDAO.class);
	@Autowired
	JdbcTemplate jdbc;

	public List<ProductComboResponse> getProductComboDetailsBiID(String vendorId, String pchId) {
		List<ProductComboResponse> pcList = new ArrayList<ProductComboResponse>();
		try {
			
			String getProductComboHdr="select distinct ph.*,pd.* from product_combo_hdr ph,product_combo_dtl pd where ph.PCH_ID=pd.PCH_ID and ph.VENDOR_ID=? and ph.PCH_ID=? and ph.IS_ACTIVE=1";
			List<ProductComboHdr> phList=this.jdbc.query(getProductComboHdr, new Object[] { vendorId,pchId }, new ProductComboHdrRowMapper());
			phList.get(0).setNoOfImages(CommonMethodsDAO.getImageCount("FL006",vendorId,phList.get(0).getPchId(), jdbc));
			for(int i=0;i<phList.size();i++) {
				ProductComboResponse pcObj=new ProductComboResponse();
				pcObj.setPhList(phList.get(i));
				String getComboDtlQry="select * from product_combo_dtl pd,product_details pm where pd.PROD_ID=pm.PROD_ID and pd.PCH_ID=?";
				List<ProductComboDtl> pcdList=this.jdbc.query(getComboDtlQry, new Object[] { pchId }, new ProductComboDtlRowMapper());
				for(int j=0;j<pcdList.size();j++) {
					String psId=pcdList.get(j).getPsId();
					String getPsDtlQry="SELECT * FROM product_sub_hdr ph,product_sub_dtl ps,product_varient_type pv where ph.PS_ID=ps.PS_ID and ps.VARIENT_TYPE_CODE=pv.VARIENT_TYPE_CODE and ph.PS_ID=?";
					List<ProductSubHdrDtl> psList=this.jdbc.query(getPsDtlQry, new Object[] { psId }, new ProductSubHdrDtlRowMapper());
					pcdList.get(j).setPsList(psList);
				}
				pcObj.setPdList(pcdList);
				pcList.add(pcObj);
			}
			
		} catch (Exception e) {
			logger.error("getProductComboDetails DAO error-->"+e);
		}
		return pcList;
	}

	public List<BannerHdrDtl> getProductBanerDetails(String vendorId, String bType) {
		List<BannerHdrDtl> pbList=null;
		try {
			String getBannerQry= "SELECT \r\n" + "    *\r\n" + "FROM\r\n" + "    product_banner_hdr ph,\r\n"
					+ "    product_banner_dtl pd,\r\n" + "    product_combo_hdr pch\r\n" + "WHERE\r\n"
					+ "    pch.IS_ACTIVE = 1\r\n" + "        AND ph.PB_HDR_ID = pd.PB_HDR_ID\r\n"
					+ "        AND pch.PCH_ID = pd.PCH_ID\r\n" + "        AND ph.PRODUCT_BANNER_TYPE = ?\r\n"
					+ "        AND ph.VENDOR_ID = ?\r\n" + "        and pch.IS_ACTIVE = 1\r\n"
					+ "        and ? between pch.EFFECTIVE_START_DATE and pch.EFFECTIVE_END_DATE\r\n"
					+ "ORDER BY ph.UPDATED_DATETIME DESC";
			pbList=this.jdbc.query(getBannerQry, new Object[] { bType,vendorId,CommonFunctions.getCurrentDate() }, new BannerHdrDtlRowMapper());
			
		} catch (Exception e) {
			logger.error("getProductBanerDetails DAO error-->"+e);
		}
		return pbList;
	}

	public List<List<ProductComboHdr>> getTrendingProductDetails(String vendorId, String type) {
		List<List<ProductComboHdr>> pcList = new ArrayList<List<ProductComboHdr>>();
		try {
			
			String getProductComboHdrNew="select  ph.*,pd.PCD_ID,pd.PROD_ID,pd.PS_ID,pd.QTY from product_combo_hdr ph,product_combo_dtl pd where ph.PCH_ID=pd.PCH_ID and ph.VENDOR_ID=?  and ph.IS_ACTIVE=1 and ph.PC_TYPE=? group by ph.PCH_ID order by ph.LAST_UPDATED_DATETIME asc limit 3";
			List<ProductComboHdr> phList=this.jdbc.query(getProductComboHdrNew, new Object[] { vendorId,type }, new ProductComboHdrRowMapper());
			
			String newComString="";
			for(int i=0;i<phList.size();i++) {
				newComString=newComString+phList.get(i).getPchId()+",";
			}
			String getProductComboHdrEnding="select  ph.*,pd.PCD_ID,pd.PROD_ID,pd.PS_ID,pd.QTY from product_combo_hdr ph,product_combo_dtl pd where ph.PCH_ID=pd.PCH_ID and ph.VENDOR_ID=?  and ph.IS_ACTIVE=1 and ph.PC_TYPE=? and ph.PCH_ID not in(?) group by ph.PCH_ID order by ph.LAST_UPDATED_DATETIME desc limit 5";
			List<ProductComboHdr> phEndList=this.jdbc.query(getProductComboHdrEnding, new Object[] { vendorId,type ,newComString}, new ProductComboHdrRowMapper());
			phEndList.removeAll(phList);
			pcList.add(phList);
			pcList.add(phEndList);
			
			
		} catch (Exception e) {
			logger.error("getTrendingProductDetails DAO error-->"+e);
		}
		return pcList;
	}

	public List<ProductComboHdr> getAllTrendingProductDetails(String vendorId, String type) {
		List<ProductComboHdr> pcList = new ArrayList<ProductComboHdr>();
		try {
			
			String getProductComboHdrNew="select  ph.*,pd.PCD_ID,pd.PROD_ID,pd.PS_ID,pd.QTY from product_combo_hdr ph,product_combo_dtl pd where ph.PCH_ID=pd.PCH_ID and ph.VENDOR_ID=?  and ph.IS_ACTIVE=1 and ph.PC_TYPE=? group by ph.PCH_ID order by ph.LAST_UPDATED_DATETIME desc";
			 pcList=this.jdbc.query(getProductComboHdrNew, new Object[] { vendorId,type }, new ProductComboHdrRowMapper());
			
			
		} catch (Exception e) {
			logger.error("getAllTrendingProductDetails DAO error-->"+e);
		}
		return pcList;
	}


	
}