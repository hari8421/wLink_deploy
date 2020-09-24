package com.ecom.dao;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ecom.domain.OrderInCartVarSummary;
import com.ecom.domain.ProductComboDtl;
import com.ecom.domain.ProductHdrDtl;
import com.ecom.domain.ProductSubHdrDtl;
import com.ecom.domain.WIshListRequest;
import com.ecom.domain.WishListProdSummary;
import com.ecom.rowmapper.OrderVarSummaryRowMapper;
import com.ecom.rowmapper.ProductComboDtlRowMapper;
import com.ecom.rowmapper.ProductHdrDtlRowMapper;
import com.ecom.rowmapper.ProductSubHdrDtlRowMapper;
import com.ecom.rowmapper.WishListProdRowMapper;

@Repository
@Transactional
public class WishListDAO {

	private static final Logger logger = LoggerFactory.getLogger(WishListDAO.class);
	@Autowired
	JdbcTemplate jdbc;

	public List<WishListProdSummary> getWishListItems(String vendorId, String customerId) {
		List<WishListProdSummary> returnList = null;
		try {

			String que = "SELECT \r\n" + "    pd.PROD_ID,\r\n" + "    pd.PROD_CODE,\r\n" + "    pd.PROD_DESC,\r\n"
					+ "    phdr.DTL_DESC AS SUB_DESC,\r\n" + "    phdr.PRICE,\r\n" + "    pch.COM_DESC,\r\n"
					+ "    cw.CW_ID,cw.PS_ID \r\n" + "FROM\r\n" + "    product_details pd,\r\n" + "    product_sub_hdr phdr,\r\n"
					+ "    customer_wishlist cw\r\n" + "        LEFT JOIN\r\n"
					+ "    product_combo_hdr pch ON pch.PCH_ID = cw.PCH_ID\r\n" + "WHERE\r\n"
					+ "    cw.PROD_ID = pd.PROD_ID\r\n" + "        AND cw.PS_ID = phdr.PS_ID\r\n"
					+ "        AND cw.CD_ID = ?\r\n" + "        AND cw.VENDOR_ID = ?";
			RowMapper<WishListProdSummary> rowMapper = new WishListProdRowMapper();
			returnList = this.jdbc.query(que, rowMapper, customerId, vendorId);
			logger.info("size " + returnList.size());
			for (int k = 0; k < returnList.size(); k++) {
				String getQ = "SELECT \r\n" + "   ptype.VARIENT_TYPE_DESC,\r\n" + "   dtl.VALUE\r\n" + "FROM\r\n"
						+ "    customer_wishlist_varient_dtl dtl,\r\n" + "    product_varient_type ptype\r\n"
						+ "WHERE\r\n" + "    ptype.VARIENT_TYPE_CODE = dtl.VARIENT_TYPE_CODE \r\n"
						+ "    and CWD_ID = ?";
				logger.info("returnList.get(k).getCwid()" + returnList.get(k).getCwid());
				RowMapper<OrderInCartVarSummary> rowMapper2 = new OrderVarSummaryRowMapper();
				returnList.get(k).setVarSum(this.jdbc.query(getQ, rowMapper2, returnList.get(k).getCwid()));
			}

		} catch (Exception ex) {
			logger.error("getWishListItems DAO exception" + ex);
		}
		return returnList;
	}

	public String insertWishList(WIshListRequest wishListReq) {
		String retMsg = "";
		try {

			String cdId = wishListReq.getCdId();
			String vendorId = wishListReq.getVendorId();
			String psId = wishListReq.getPsId();
			String isCombo = wishListReq.getIsCombo();
			String pchId = wishListReq.getPchId();
			String prodId=wishListReq.getProdId();
			LocalDateTime upDate = java.time.LocalDateTime.now();

			String getProdByPsid = "SELECT \r\n" + "    pm.*, bm.*, pc.*, ps.*\r\n" + "FROM\r\n"
					+ "    product_details pm,\r\n" + "    brand_mst bm,\r\n" + "    product_category_hdr pc,\r\n"
					+ "    product_sub_hdr ps\r\n" + "WHERE\r\n" + "    pm.BM_ID = bm.BM_ID\r\n"
					+ "        AND pm.PARENT_CATE_HDR_ID = pc.PARENT_CATE_HDR_ID\r\n"
					+ "        AND ps.PROD_ID = pm.PROD_ID and ps.PS_ID=?";

			String insPsubTble = "insert into customer_wishlist_varient_dtl (CW_ID,VARIENT_TYPE_CODE,VALUE) values(?,?,?)";
			String insHdrQry = "insert into customer_wishlist (CD_ID,PCH_ID,PROD_ID,PS_ID,CREATED_DATETIME,LAST_UPDATED_DATETIME,VENDOR_ID) values(?,?,?,?,?,?,?)";

			// If there is an existing item in wishlist

			String delExistProdvar = "delete from customer_wishlist_varient_dtl where CW_ID in (select CW_ID from customer_wishlist where PS_ID=?)";
			String delExistProdFromHdr = "delete from customer_wishlist where  PS_ID=?";
			// For non combo add to wishlist
			if (isCombo.equalsIgnoreCase("0")) {
				this.jdbc.update(delExistProdvar, psId);
				this.jdbc.update(delExistProdFromHdr, psId);
				//List<ProductHdrDtl> pList = null;
				// Getting product details from psId
				//pList = this.jdbc.query(getProdByPsid, new Object[] { psId }, new ProductHdrDtlRowMapper());

				//final String fProdCode = pList.get(0).getProdCode();
				
				// Inserting product list in order detail table
				KeyHolder orderDtlHolder = new GeneratedKeyHolder();
				jdbc.update(connection -> {
					PreparedStatement ps = connection.prepareStatement(insHdrQry, new String[] { "ID" });
					ps.setString(1, cdId);
					ps.setString(2, pchId);
					ps.setString(3, prodId);
					ps.setString(4, psId);
					ps.setString(5, upDate + "");
					ps.setString(6, upDate + "");
					ps.setString(7, vendorId);

					return ps;
				}, orderDtlHolder);

				int insDtl = orderDtlHolder.getKey().intValue();
				if (insDtl > 0) {
					String getPsDtlQry = "SELECT * FROM product_sub_hdr ph,product_sub_dtl ps,product_varient_type pv where ph.PS_ID=ps.PS_ID and ps.VARIENT_TYPE_CODE=pv.VARIENT_TYPE_CODE and ph.PS_ID=?";
					List<ProductSubHdrDtl> psList = this.jdbc.query(getPsDtlQry, new Object[] { psId },
							new ProductSubHdrDtlRowMapper());
					// Inserting values in order varient table
					for (int s = 0; s < psList.size(); s++) {
						int psIns = this.jdbc.update(insPsubTble,
								new Object[] { insDtl, psList.get(s).getVarientTypeCode(), psList.get(s).getValue() });
						if (psIns > 0) {
							retMsg = "Success";
						} else {
							retMsg = "Failure";
						}
					}

				}

			} // For combo add to wishlist
			else {
				 delExistProdvar = "delete from customer_wishlist_varient_dtl where CW_ID in (select CW_ID from customer_wishlist where PS_ID=? and PCH_ID=?)";
				 delExistProdFromHdr = "delete from customer_wishlist where  PS_ID=? and PCH_ID=?";
				String getComboDtlQry = "select * from product_combo_dtl pd,product_details pm where pd.PROD_ID=pm.PROD_ID and pd.PCH_ID=?";
				List<ProductComboDtl> pcdList = this.jdbc.query(getComboDtlQry, new Object[] { pchId },
						new ProductComboDtlRowMapper());
				// Iterating combo products
				for (int j = 0; j < pcdList.size(); j++) {

					List<ProductHdrDtl> pList = null;
					pList = this.jdbc.query(getProdByPsid, new Object[] { pcdList.get(j).getPsId() },
							new ProductHdrDtlRowMapper());
					final String fProdCode = pList.get(0).getProdCode();
					final String cPsid = pcdList.get(j).getPsId();
					this.jdbc.update(delExistProdvar, cPsid,pchId);
					this.jdbc.update(delExistProdFromHdr, cPsid,pchId);
					KeyHolder orderDtlHolder = new GeneratedKeyHolder();
					jdbc.update(connection -> {
						PreparedStatement ps = connection.prepareStatement(insHdrQry, new String[] { "ID" });
						ps.setString(1, cdId);
						ps.setString(2, pchId);
						ps.setString(3, fProdCode);
						ps.setString(4, cPsid);
						ps.setString(5, upDate + "");
						ps.setString(6, upDate + "");
						ps.setString(7, vendorId);

						return ps;
					}, orderDtlHolder);

					int insDtl = orderDtlHolder.getKey().intValue();
					if (insDtl > 0) {
						String getPsDtlQry = "SELECT * FROM product_sub_hdr ph,product_sub_dtl ps,product_varient_type pv where ph.PS_ID=ps.PS_ID and ps.VARIENT_TYPE_CODE=pv.VARIENT_TYPE_CODE and ph.PS_ID=?";
						List<ProductSubHdrDtl> psList = this.jdbc.query(getPsDtlQry, new Object[] { cPsid },
								new ProductSubHdrDtlRowMapper());
						for (int s = 0; s < psList.size(); s++) {
							int dtlUp = this.jdbc.update(insPsubTble, new Object[] { insDtl,
									psList.get(s).getVarientTypeCode(), psList.get(s).getValue() });
							if (dtlUp > 0) {
								retMsg = "Success";
							}
						}

					}
				}
			}

		} catch (Exception e) {
			logger.error("insertWishList DAO error-->" + e);
		}
		return retMsg;
	}

}
