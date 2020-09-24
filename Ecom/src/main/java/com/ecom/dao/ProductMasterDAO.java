package com.ecom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ecom.common.CommonFunctions;
import com.ecom.domain.AdminIndProdExtra;
import com.ecom.domain.AdminIndProdMst;
import com.ecom.domain.AdminIndProdVar;
import com.ecom.domain.AdminIndProdVarId;
import com.ecom.domain.OrderInCartVarSummary;
import com.ecom.domain.ProdComHdr;
import com.ecom.domain.ProdExtnDtl;
import com.ecom.domain.ProdReviewDtl;
import com.ecom.domain.ProdSingleCmbDtl;
import com.ecom.domain.ProductComboHdr;
import com.ecom.domain.ProductDetailsByName;
import com.ecom.domain.ProductHdrDtl;
import com.ecom.domain.ProductSubHdrADtl;
import com.ecom.domain.SingleProdDetails;
import com.ecom.requestEntity.insertProductMstRequest;
import com.ecom.rowmapper.AdminProdExtraRowMapper;
import com.ecom.rowmapper.AdminProdMstRowMapper;
import com.ecom.rowmapper.AdminProdSubRowMapper;
import com.ecom.rowmapper.AdminProdVarMapper;
import com.ecom.rowmapper.OrderVarSummaryRowMapper;
import com.ecom.rowmapper.PSRowMapper;
import com.ecom.rowmapper.ProdCmbDtlRowMapper;
import com.ecom.rowmapper.ProdComHdrRowMapper;
import com.ecom.rowmapper.ProdExtnRowMapper;
import com.ecom.rowmapper.ProdReviewRowMapper;
import com.ecom.rowmapper.ProdSubHdtADtlIIRowMapper;
import com.ecom.rowmapper.ProdSubHdtADtlRowMapper;
import com.ecom.rowmapper.ProductComboHdrRowMapper;
import com.ecom.rowmapper.ProductHdrDtlRowMapper;
import com.ecom.rowmapper.SingleProdDtlRowMapper;

@Repository
@Transactional
public class ProductMasterDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProductMasterDAO.class);
	@Autowired
	JdbcTemplate jdbc;

	public List<ProductHdrDtl> getProductDetailsByPrimaryCategory(String vendorId, String pCategory) {
		List<ProductHdrDtl> pList = null;
		try {
			String getQ = "SELECT \r\n" + "    pm.*, bm.*, pc.*, ps.*\r\n" + "FROM\r\n" + "    product_details pm,\r\n"
					+ "    brand_mst bm,\r\n" + "    product_category_hdr pc,\r\n" + "    product_sub_hdr ps\r\n"
					+ "WHERE\r\n" + "    pm.BM_ID = bm.BM_ID\r\n"
					+ "        AND pm.PARENT_CATE_HDR_ID = pc.PARENT_CATE_HDR_ID\r\n"
					+ "        AND ps.PROD_ID = pm.PROD_ID where pm.PARENT_CATE_HDR_ID=?";
			pList = this.jdbc.query(getQ, new Object[] { vendorId }, new ProductHdrDtlRowMapper());
		} catch (Exception e) {
			logger.error("getProductDetailsByPrimaryCategory error-->" + e);
		}
		return pList;
	}

	public List<SingleProdDetails> getSingleProdDet(String vendorId, String psId) {
		List<SingleProdDetails> singleProdDtl = null;
		try {

			String getPs = "select PROD_ID from product_sub_hdr where PS_ID = ? and VENDOR_ID = ?";
			String prodId = this.jdbc.queryForObject(getPs, new Object[] { psId, vendorId }, String.class);

			String getQ = "SELECT \r\n" + "    bm.BM_DESC,\r\n" + "    pd.PROD_CODE,\r\n" + "    pd.PROD_DESC,\r\n"
					+ "    pd.PROD_ID,\r\n" + "    pd.PROD_NAME,\r\n" + "    pd.TAGS,\r\n" + "    shdr.DTL_DESC,\r\n"
					+ "    shdr.PRICE,\r\n" + "    shdr.PRIMARY_UOM,\r\n" + "    shdr.STATUS,\r\n"
					+ "    sm.STATUS_DESC,\r\n" + "    um.UOM_DESC,\r\n" + "    shdr.PS_ID,\r\n"
					+ "    phdr.COM_DESC,\r\n" + "    phdr.COM_LONG_DESC,\r\n" + "    phdr.ORIGINAL_RATE,\r\n"
					+ "    phdr.ACTUAL_RATE,\r\n" + "    phdr.DISCOUNT_RATE,\r\n" + "    phdr.DISCOUNT_PERCENTAGE,\r\n"
					+ "    shdr.RATING\r\n" + "FROM\r\n" + "    product_details pd,\r\n" + "    brand_mst bm,\r\n"
					+ "    uom_mst um,\r\n" + "    status_mst sm,\r\n" + "    product_sub_hdr shdr\r\n"
					+ "        LEFT JOIN\r\n" + "    product_combo_dtl pdtl ON pdtl.PS_ID = shdr.PS_ID\r\n"
					+ "        LEFT JOIN\r\n" + "    product_combo_hdr phdr ON phdr.PCH_ID = pdtl.PCH_ID\r\n"
					+ "        AND phdr.PC_TYPE = 'PC002' and ? between phdr.EFFECTIVE_START_DATE and phdr.EFFECTIVE_END_DATE\r\n"
					+ "WHERE\r\n" + "    pd.PROD_ID = shdr.PROD_ID\r\n" + "        AND bm.BM_ID = pd.BM_ID\r\n"
					+ "        AND um.UOM_ID = shdr.PRIMARY_UOM\r\n" + "        AND sm.STATUS_CODE = shdr.STATUS\r\n"
					+ "        AND pd.PROD_ID = ?\r\n" + "        AND shdr.VENDOR_ID = ?\r\n"
					+ "        AND shdr.STATUS = ?";
			singleProdDtl = this.jdbc.query(getQ,
					new Object[] { CommonFunctions.getCurrentDateTime(), prodId, vendorId, "ST008" },
					new SingleProdDtlRowMapper());

			for (int h = 0; h < singleProdDtl.size(); h++) {
				getQ = "SELECT \r\n" + "    ty.VARIENT_TYPE_DESC,\r\n" + "    sdtl.VALUE\r\n" + "FROM\r\n"
						+ "    product_sub_dtl sdtl,\r\n" + "    product_varient_type ty\r\n" + "WHERE\r\n"
						+ "    ty.VARIENT_TYPE_CODE = sdtl.VARIENT_TYPE_CODE\r\n" + "    and sdtl.PS_ID=?";
				RowMapper<OrderInCartVarSummary> rowMapper2 = new OrderVarSummaryRowMapper();
				singleProdDtl.get(h).setOrderSumm(this.jdbc.query(getQ, rowMapper2, singleProdDtl.get(h).getPsId()));

				if (singleProdDtl.get(h).getPsId().equalsIgnoreCase(psId)) {
					singleProdDtl.get(h).setIsRequired("1");
				} else {
					singleProdDtl.get(h).setIsRequired("0");
				}

				String getImQ = "select count(*) from file_manager where REFERENCE_CODE = ? and REFERENCE_ID =?";
				singleProdDtl.get(h).setNoOfImages(this.jdbc.queryForObject(getImQ,
						new Object[] { "FL003", singleProdDtl.get(h).getPsId() }, String.class));

				getQ = "SELECT pr.*,cd.CD_ID,cd.NAME FROM product_review_dtl pr , customer_details cd where PS_ID=? and cd.CD_ID = pr.CD_ID";
				RowMapper<ProdReviewDtl> rowMapper4 = new ProdReviewRowMapper();
				singleProdDtl.get(h).setReviewExtn(this.jdbc.query(getQ, rowMapper4, singleProdDtl.get(h).getPsId()));

				List<ProdComHdr> prodCmb = null;
				String getHQ = "SELECT \r\n" + "    phdr.PCH_ID\r\n" + "FROM\r\n" + "    product_combo_hdr phdr,\r\n"
						+ "    product_combo_dtl pdtl\r\n" + "WHERE\r\n" + "    phdr.PCH_ID = pdtl.PCH_ID\r\n"
						+ "        AND PS_ID = ? and ? between EFFECTIVE_START_DATE and EFFECTIVE_END_DATE";
				RowMapper<ProdComHdr> rowMapper6 = new ProdComHdrRowMapper();
				prodCmb = this.jdbc.query(getHQ, rowMapper6, singleProdDtl.get(h).getPsId(),
						CommonFunctions.getCurrentDateTime());
				String ch = "";
				if (prodCmb.size() > 0) {

					for (int l = 0; l < prodCmb.size(); l++) {
						if (ch.equalsIgnoreCase("")) {
							ch = prodCmb.get(l).getPchId();
						} else {
							ch = ch + "," + prodCmb.get(l).getPchId();
						}
					}
				}

				if (!ch.equalsIgnoreCase("")) {
					getQ = "SELECT \r\n" + "    phdr.ORIGINAL_RATE,\r\n" + "    phdr.DISCOUNT_PERCENTAGE,\r\n"
							+ "    phdr.DISCOUNT_RATE,\r\n" + "    phdr.ACTUAL_RATE,\r\n" + "    phdr.COM_DESC,\r\n"
							+ "    phdr.PCH_ID,\r\n" + "    psh.DTL_DESC\r\n" + "    \r\n" + "FROM\r\n"
							+ "    product_combo_hdr phdr,\r\n" + "    product_combo_dtl pdtl,\r\n"
							+ "    product_sub_hdr psh\r\n" + "WHERE\r\n" + "    phdr.PC_TYPE = 'PC001'\r\n"
							+ "        AND pdtl.PCH_ID = phdr.PCH_ID\r\n" + "        and psh.PS_ID = pdtl.PS_ID\r\n"
							+ "        and find_in_set(phdr.PCH_ID,?)";
					RowMapper<ProdSingleCmbDtl> rowMapper3 = new ProdCmbDtlRowMapper();
					singleProdDtl.get(0).setCmbDtl((this.jdbc.query(getQ, rowMapper3, ch)));

				}

				getQ = "select PROD_TITLE,PROD_KEY,PROD_VALUE from product_extra_dtl where PROD_ID=?";
				RowMapper<ProdExtnDtl> rowMapper3 = new ProdExtnRowMapper();
				singleProdDtl.get(h).setProdExtn(this.jdbc.query(getQ, rowMapper3, prodId));

				getQ = "SELECT count(*) FROM product_review_dtl where PS_ID=? and EARNED_RATING=?";
				singleProdDtl.get(h).setOneRat(this.jdbc.queryForObject(getQ,
						new Object[] { singleProdDtl.get(h).getPsId(), 1 }, String.class));
				singleProdDtl.get(h).setTwoRat(this.jdbc.queryForObject(getQ,
						new Object[] { singleProdDtl.get(h).getPsId(), 2 }, String.class));
				singleProdDtl.get(h).setThreeRat(this.jdbc.queryForObject(getQ,
						new Object[] { singleProdDtl.get(h).getPsId(), 3 }, String.class));
				singleProdDtl.get(h).setFourRat(this.jdbc.queryForObject(getQ,
						new Object[] { singleProdDtl.get(h).getPsId(), 4 }, String.class));
				singleProdDtl.get(h).setFiveRat(this.jdbc.queryForObject(getQ,
						new Object[] { singleProdDtl.get(h).getPsId(), 5 }, String.class));

			}

		} catch (Exception e) {
			logger.error("getSingleProdDet error-->" + e);
		}
		return singleProdDtl;
	}

	public List<ProductHdrDtl> getProductByFilter(String catId, String catCode, String minPrice, String maxPrice,
			String brand, String vendorId, String vCodeQry) {
		List<ProductHdrDtl> pList = null;
		try {
			ProductCategoryDAO pcDao = new ProductCategoryDAO();
			String checkIfPrimary = "select count(*) from product_category_hdr where PCM_CODE=? and IS_PRIMARY=1";
			int pCnt = this.jdbc.queryForObject(checkIfPrimary, new Object[] { catCode }, Integer.class);
			String getProdQry = "";
			String catString = "";
			String bmQry = "";
			String bVal = "";
			if (!brand.equalsIgnoreCase("")) {
				String[] bArray = brand.split(",");
				for (int bi = 0; bi < bArray.length; bi++) {
					if (!bArray[bi].equalsIgnoreCase("")) {
						if (bi == bArray.length - 1) {
							bVal = bVal + "'" + bArray[bi] + "'";
						} else {
							bVal = bVal + "'" + bArray[bi] + "'" + ",";
						}
					}
				}

				bmQry = bmQry + "and bm.BM_CODE in (" + bVal + ")";
			}
			if (!vCodeQry.equalsIgnoreCase("")) {
				vCodeQry = "and" + "(" + vCodeQry + ")";
			}
			if (pCnt > 0) {
				getProdQry = "SELECT pd.*,bm.*,ps.*\r\n" + "    \r\n" + "FROM\r\n" + "    product_details pd,\r\n"
						+ "    product_sub_hdr ps,brand_mst bm,\r\n" + "    product_sub_dtl psd\r\n" + "WHERE\r\n"
						+ "    pd.BM_ID=bm.BM_ID    and pd.PROD_ID = ps.PROD_ID\r\n"
						+ "     AND ps.PS_ID = psd.PS_ID \r\n" + "        AND pd.PARENT_CATE_HDR_ID IN (" + catId
						+ ") and ps.PRICE between ? and ? and pd.VENDOR_ID=?\r\n" + bmQry + vCodeQry
						+ " group by pd.PROD_ID,pd.BM_ID";
			} else {
				catString = pcDao.getCategoriesFromCatId(catId, catCode, this.jdbc);
				getProdQry = "SELECT pd.*,bm.*,ps.*\r\n" + "    \r\n" + "FROM\r\n" + "    product_details pd,\r\n"
						+ "    product_sub_hdr ps,brand_mst bm,\r\n" + "    product_sub_dtl psd\r\n" + "WHERE\r\n"
						+ "    pd.BM_ID=bm.BM_ID   and pd.PROD_ID = ps.PROD_ID\r\n"
						+ "     AND ps.PS_ID = psd.PS_ID \r\n" + "        AND pd.PARENT_CATE_DTL_ID IN (" + catString
						+ ") and ps.PRICE between ? and ? and pd.VENDOR_ID=?\r\n" + bmQry + vCodeQry
						+ " group by pd.PROD_ID,pd.BM_ID";
			}
			pList = this.jdbc.query(getProdQry, new Object[] { minPrice, maxPrice, vendorId },
					new ProductHdrDtlRowMapper());
		} catch (Exception e) {
			logger.error("getProductByFilter DAO error-->" + e);
		}
		return pList;
	}

	public String insertProductMst(insertProductMstRequest insertProd) {
		int prodId = 0;
		try {

			String inquery = "INSERT INTO product_details (BM_ID, PARENT_CATE_DTL_ID, PROD_CODE, PROD_NAME, PROD_DESC, TAGS, IS_ACTIVE, CREATED_DATETIME, LAST_UPDATED_DATETIME, PARENT_CATE_HDR_ID, VENDOR_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(inquery, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, insertProd.getBrand());
					ps.setString(2, insertProd.getProdCat());
					ps.setString(3, insertProd.getProductCode());
					ps.setObject(4, insertProd.getProductName());
					ps.setObject(5, insertProd.getProductDesc());
					ps.setObject(6, insertProd.getTags());
					ps.setObject(7, "1");
					ps.setObject(8, CommonFunctions.getCurrentDateTime());
					ps.setString(9, CommonFunctions.getCurrentDateTime());
					ps.setString(10, insertProd.getPrimCat());
					ps.setString(11, insertProd.getVendorId());
					return ps;
				}
			}, holder);
			prodId = holder.getKey().intValue();
			final int prod = prodId;
			if (prod > 0) {
				String subQ = "INSERT INTO product_sub_hdr (PROD_ID, STATUS, PRICE, VENDOR_ID,DTL_DESC) VALUES (?,?, ?, ?, ?)";

				holder = new GeneratedKeyHolder();
				jdbc.update(new PreparedStatementCreator() {

					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(subQ, Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, prod + "");
						ps.setString(2, "ST008");
						ps.setString(3, "0");
						ps.setObject(4, insertProd.getVendorId());
						ps.setString(5, "");
						return ps;
					}
				}, holder);
				int subHdrId = holder.getKey().intValue();

				if (subHdrId > 0) {
					String varArr[] = insertProd.getVarient().split("\\,");
					for (int k = 0; k < varArr.length; k++) {
						String insertDtl = "INSERT INTO product_sub_dtl (PS_ID, VARIENT_TYPE_CODE, VALUE) VALUES (?, ?, ?)";
						this.jdbc.update(insertDtl, subHdrId, varArr[k], "0");
					}

					String insertE = "INSERT INTO product_extra_dtl (PROD_ID, PROD_TITLE, PROD_KEY, PROD_VALUE, IS_ACTIVE) VALUES (?, ?, ?, ?, ?)";
					this.jdbc.update(insertE, prod, "", "", "", 1);

				}
			}

		} catch (Exception e) {
			logger.error("insertProductMst DAO error-->" + e);
		}
		return prodId + "";
	}

	public List<ProductSubHdrADtl> getProdExtnDtl(String prodId, String vendorId) {
		List<ProductSubHdrADtl> returnList = null;
		try {
			String getQ = "SELECT \r\n" + "    pd.PROD_ID,\r\n" + "    DTL_DESC,\r\n" + "    STATUS_DESC,\r\n"
					+ "    PRICE,\r\n" + "    pd.PROD_CODE,\r\n" + "    pd.PROD_DESC,\r\n" + "    pd.PROD_ID,\r\n"
					+ "    pd.PROD_NAME\r\n" + "FROM\r\n" + "    product_sub_hdr hdr,\r\n" + "    status_mst sm,\r\n"
					+ "    product_details pd\r\n" + "WHERE\r\n" + "    pd.PROD_ID = ? AND pd.VENDOR_ID = ?\r\n"
					+ "        AND pd.PROD_ID = hdr.PROD_ID\r\n" + "        AND sm.STATUS_CODE = hdr.STATUS";
			returnList = this.jdbc.query(getQ, new Object[] { prodId, vendorId }, new ProdSubHdtADtlRowMapper());
			for (int k = 0; k < returnList.size(); k++) {

				getQ = "SELECT \r\n" + "    pv.VARIENT_TYPE_CODE , pv.VARIENT_TYPE_DESC\r\n" + "FROM\r\n"
						+ "    product_sub_dtl dtl,\r\n" + "    product_varient_type pv\r\n" + "WHERE\r\n"
						+ "    dtl.PS_ID = ?\r\n" + "        AND pv.VARIENT_TYPE_CODE = dtl.VARIENT_TYPE_CODE\r\n"
						+ "        and VENDOR_ID= ?\r\n" + "        order by  pv.VARIENT_TYPE_DESC";
				returnList.get(k).setVarList(this.jdbc.query(getQ,
						new Object[] { returnList.get(k).getPsId(), vendorId }, new ProdSubHdtADtlIIRowMapper()));

			}

		} catch (Exception e) {
			logger.error("getProdExtnDtl DAO error-->" + e);
		}
		return returnList;
	}

	public List<ProductHdrDtl> getBestSellers(String vendorId) {
		List<ProductHdrDtl> pList = new ArrayList<ProductHdrDtl>();
		try {
			String getBestSellerQry = "SELECT \r\n" + "   distinct  pd.*, psh.*,bm.*,pc.*,COUNT(od.PROD_ID) AS pCnt\r\n"
					+ "FROM\r\n" + "    order_information_dtl od, brand_mst bm,  product_category_hdr pc,\r\n"
					+ "    product_details pd,\r\n" + "    product_sub_hdr psh\r\n" + "WHERE\r\n"
					+ "    od.PROD_CODE = pd.PROD_CODE \r\n"
					+ "        AND pd.PROD_ID = psh.PROD_ID and pd.VENDOR_ID=psh.VENDOR_ID and pd.BM_ID = bm.BM_ID  AND pd.PARENT_CATE_HDR_ID = pc.PARENT_CATE_HDR_ID and pd.VENDOR_ID=?\r\n"
					+ "GROUP BY od.PROD_ID\r\n" + "ORDER BY pCnt DESC";
			pList = this.jdbc.query(getBestSellerQry, new Object[] { vendorId }, new ProductHdrDtlRowMapper());

		} catch (Exception e) {
			logger.error("getBestSellers DAO error" + e);
		}
		return pList;
	}

	public List<ProductHdrDtl> getPSByVendor(String vendorId) {
		List<ProductHdrDtl> pList = new ArrayList<ProductHdrDtl>();
		try {
			String getQ = "select * from product_sub_hdr where VENDOR_ID= ? order by DTL_DESC asc";
			pList = this.jdbc.query(getQ, new Object[] { vendorId }, new PSRowMapper());

		} catch (Exception e) {
			logger.error("getPSByVendor DAO error" + e);
		}
		return pList;
	}

	public ProductDetailsByName ProductDetailsByName(String name, String vendorId) {
		ProductDetailsByName pObj = new ProductDetailsByName();
		logger.info("ProductDetailsByName method started");
		try {
			String pCode = "%" + name + "%";
			List<ProductComboHdr> pcList = new ArrayList<ProductComboHdr>();
			List<ProductHdrDtl> pmList = new ArrayList<ProductHdrDtl>();
			String getBestSellerQry = "SELECT \r\n" + "   distinct  pd.*, psh.*,bm.*,pc.*,COUNT(pd.PROD_ID) AS pCnt\r\n"
					+ "FROM\r\n" + "   brand_mst bm,  product_category_hdr pc,\r\n" + "    product_details pd,\r\n"
					+ "    product_sub_hdr psh\r\n" + "WHERE\r\n"
					+ "   pd.PROD_ID = psh.PROD_ID and pd.VENDOR_ID=psh.VENDOR_ID and pd.BM_ID = bm.BM_ID  AND pd.PARENT_CATE_HDR_ID = pc.PARENT_CATE_HDR_ID and pd.VENDOR_ID=?\r\n"
					+ " and (pd.PROD_NAME like ? or pd.PROD_DESC like ?) GROUP BY pd.PROD_ID\r\n";
			pmList = this.jdbc.query(getBestSellerQry, new Object[] { vendorId, pCode, pCode },
					new ProductHdrDtlRowMapper());
			String getProductComboHdrNew = "select  ph.*,pd.PCD_ID,pd.PROD_ID,pd.PS_ID,pd.QTY from product_combo_hdr ph,product_combo_dtl pd where ph.PCH_ID=pd.PCH_ID and ph.VENDOR_ID=?  and ph.IS_ACTIVE=1 and ph.TAGS like ?  group by ph.PCH_ID order by ph.PC_TYPE,ph.LAST_UPDATED_DATETIME desc  ";
			pcList = this.jdbc.query(getProductComboHdrNew, new Object[] { vendorId, pCode },
					new ProductComboHdrRowMapper());
			pObj.setPcList(pcList);
			pObj.setPmList(pmList);
		} catch (Exception e) {
			logger.error("ProductDetailsByName DAO error-->" + e);
		}
		logger.info("ProductDetailsByName method started");
		return pObj;
	}

	public List<ProductHdrDtl> getNewArrivals(String vendorId, String pageLimit) {
		List<ProductHdrDtl> pList = new ArrayList<ProductHdrDtl>();
		try {
			String getBestSellerQry = "SELECT \r\n" + "   distinct  pd.*, psh.*,bm.*,pc.*,COUNT(pd.PROD_ID) AS pCnt\r\n"
					+ "FROM\r\n" + "  brand_mst bm,  product_category_hdr pc,\r\n" + "    product_details pd,\r\n"
					+ "    product_sub_hdr psh\r\n" + "WHERE\r\n"
					+ "         pd.PROD_ID = psh.PROD_ID and pd.VENDOR_ID=psh.VENDOR_ID and pd.BM_ID = bm.BM_ID  AND pd.PARENT_CATE_HDR_ID = pc.PARENT_CATE_HDR_ID and pd.VENDOR_ID=?\r\n"
					+ "GROUP BY pd.PROD_ID\r\n" + "ORDER BY pd.CREATED_DATETIME DESC limit ?";
			pList = this.jdbc.query(getBestSellerQry, new Object[] { vendorId, Integer.parseInt(pageLimit) },
					new ProductHdrDtlRowMapper());
		} catch (Exception e) {
			logger.error("getNewArrivals DAO error-->" + e);
		}
		return pList;
	}

	public List<AdminIndProdMst> getProdByProdId(String prodId, String vendorId) {
		List<AdminIndProdMst> pList = null;
		List<AdminIndProdVar> sList = null;
		try {
			String getQ = "select * from product_details where PROD_ID=? and VENDOR_ID=?";
			pList = this.jdbc.query(getQ, new Object[] { prodId, vendorId }, new AdminProdMstRowMapper());

			for (int h = 0; h < pList.size(); h++) {

				String getSQ = "select hdr.* ,sm.* from product_sub_hdr hdr , status_mst sm where hdr.PROD_ID=? and hdr.STATUS = sm.STATUS_CODE order by STATUS_CODE";
				sList = this.jdbc.query(getSQ, new Object[] { prodId }, new AdminProdSubRowMapper());
				pList.get(h).setProdVar(sList);
				List<AdminIndProdVarId> vList = null;
				List<AdminIndProdExtra> eList = null;
				for (int j = 0; j < sList.size(); j++) {

					String getDV = "SELECT \r\n" + "     group_concat(VARIENT_TYPE_DESC)\r\n" + "FROM\r\n"
							+ "    product_details pd,\r\n" + "    product_sub_hdr shdr,\r\n"
							+ "    product_sub_dtl sdtl,\r\n" + "    product_varient_type pv\r\n" + "WHERE\r\n"
							+ "    pd.PROD_ID = shdr.PROD_ID\r\n" + "        AND sdtl.PS_ID = shdr.PS_ID\r\n"
							+ "        AND sdtl.VARIENT_TYPE_CODE = pv.VARIENT_TYPE_CODE\r\n"
							+ "        and pd.PROD_ID = ?\r\n" + "        order by sdtl.VARIENT_TYPE_CODE asc;";
					String arr[] = this.jdbc.queryForObject(getDV, new Object[] { prodId }, String.class).split("\\,");

					int end = arr.length;
					Set<String> set = new HashSet<String>();

					for (int i = 0; i < end; i++) {
						set.add(arr[i]);
					}
					String pl = "";
					Iterator<String> it = set.iterator();
					while (it.hasNext()) {
						if (pl.equalsIgnoreCase("")) {
							pl = it.next();
						} else {
							pl = pl + "," + it.next();
						}

					}

					pList.get(h).setDistinctVarDesc(pl);

					getDV = "SELECT \r\n" + "     group_concat(distinct sdtl.VARIENT_TYPE_CODE)\r\n" + "FROM\r\n"
							+ "    product_details pd,\r\n" + "    product_sub_hdr shdr,\r\n"
							+ "    product_sub_dtl sdtl,\r\n" + "    product_varient_type pv\r\n" + "WHERE\r\n"
							+ "    pd.PROD_ID = shdr.PROD_ID\r\n" + "        AND sdtl.PS_ID = shdr.PS_ID\r\n"
							+ "        AND sdtl.VARIENT_TYPE_CODE = pv.VARIENT_TYPE_CODE\r\n"
							+ "        and pd.PROD_ID = ?\r\n" + "        order by sdtl.VARIENT_TYPE_CODE asc;";
					pList.get(h)
							.setDistinctVarCode(this.jdbc.queryForObject(getDV, new Object[] { prodId }, String.class));

					String getDQ = "SELECT * FROM product_sub_dtl where PS_ID =? order by VARIENT_TYPE_CODE asc";
					vList = this.jdbc.query(getDQ, new Object[] { sList.get(j).getPsId() }, new AdminProdVarMapper());
					sList.get(j).setVarId(vList);

					String getsubQ = "SELECT PE_ID,PROD_ID as PS_ID,PROD_TITLE,PROD_KEY,PROD_VALUE,IS_ACTIVE FROM product_extra_dtl where PROD_ID =?";
					eList = this.jdbc.query(getsubQ, new Object[] { prodId }, new AdminProdExtraRowMapper());
					pList.get(h).setExtraDet(eList);
				}

			}

		} catch (Exception e) {
			logger.error("getProdByProdId DAO error-->" + e);
		}
		return pList;
	}

	public void updateProd(String bM_ID, String pARENT_CATE_DTL_ID, String pROD_CODE, String pROD_NAME,
			String pROD_DESC, String tAGS, String iS_ACTIVE, String vENDOR_ID, String pROD_ID) {
		try {
			String upH = "UPDATE product_details SET BM_ID=?, PARENT_CATE_DTL_ID=?, PROD_CODE=?, PROD_NAME=?, PROD_DESC=?, TAGS=?, IS_ACTIVE=?, VENDOR_ID=?, LAST_UPDATED_DATETIME=? WHERE PROD_ID=?;";
			this.jdbc.update(upH, bM_ID, pARENT_CATE_DTL_ID, pROD_CODE, pROD_NAME, pROD_DESC, tAGS, iS_ACTIVE,
					vENDOR_ID, CommonFunctions.getCurrentDateTime(), pROD_ID);
		} catch (Exception e) {
			logger.error("updateProd DAO error-->" + e);
		}

	}

	public void insertExtraDtl(String pROD_TITLE, String pROD_KEY, String pROD_VALUE, String iS_ACTIVE, String prodId) {
		try {
			String inE = "INSERT INTO product_extra_dtl (PROD_ID, PROD_TITLE, PROD_KEY, PROD_VALUE, IS_ACTIVE) VALUES (?, ?, ?, ?, ?)";
			this.jdbc.update(inE, prodId, pROD_TITLE, pROD_KEY, pROD_VALUE, iS_ACTIVE);
		} catch (Exception e) {
			logger.error("updateProd DAO error-->" + e);
		}
	}

	public void updateExtraDtl(String pROD_TITLE, String pS_ID, String pROD_KEY, String pROD_VALUE, String iS_ACTIVE) {
		try {
			String inE = "UPDATE product_extra_dtl SET PROD_TITLE=?, PROD_KEY=?, PROD_VALUE=?,IS_ACTIVE=? WHERE PE_ID=?";
			this.jdbc.update(inE, pROD_TITLE, pROD_KEY, pROD_VALUE, iS_ACTIVE, pS_ID);
		} catch (Exception e) {
			logger.error("updateProd DAO error-->" + e);
		}
	}

	public void insertvarDtl(String dTL_DESC, String pRICE, String iS_ACTIVE, String ProdId, JSONArray varients,
			String vendorId) {
		try {

			String subQ = "INSERT INTO product_sub_hdr (PROD_ID, STATUS, PRICE, VENDOR_ID,DTL_DESC,CREATED_DATETIME,LAST_UPDATED_DATETIME) VALUES (?,?,?,?, ?, ?, ?)";

			KeyHolder holder = new GeneratedKeyHolder();
			jdbc.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(subQ, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, ProdId);
					ps.setString(2, "ST008");
					ps.setString(3, pRICE);
					ps.setObject(4, vendorId);
					ps.setString(5, dTL_DESC);
					ps.setString(6, CommonFunctions.getCurrentDateTime());
					ps.setString(7, CommonFunctions.getCurrentDateTime());
					return ps;
				}
			}, holder);
			int subHdrId = holder.getKey().intValue();

			if (subHdrId > 0) {
				for (int i = 0; i < varients.length(); i++) {
					JSONObject objects = varients.getJSONObject(i);
					JSONArray keys = objects.names();
					String REFERENCE_ID = "", VALUE = "";
					for (int j = 0; j < keys.length(); ++j) {

						String key = keys.getString(j);
						String value = objects.getString(key);

						if (key.equalsIgnoreCase("VALUE")) {
							VALUE = value;
						} else if (key.equalsIgnoreCase("REFERENCE_ID")) {
							REFERENCE_ID = value;
						}
					}

					String insertDtl = "INSERT INTO product_sub_dtl (PS_ID, VARIENT_TYPE_CODE, VALUE) VALUES (?, ?, ?)";
					this.jdbc.update(insertDtl, subHdrId, REFERENCE_ID, VALUE);
				}
			}

		} catch (Exception e) {
			logger.error("updateProd DAO error-->" + e);
		}

	}

	public void updatevarDtl(String dTL_DESC, String pRICE, String iS_ACTIVE, String prodId, JSONArray varients,
			String vendorId, String psId) {
		try {

			String upQ = "UPDATE product_sub_hdr SET DTL_DESC=?,PRICE=?, CREATED_DATETIME=?, LAST_UPDATED_DATETIME=? WHERE PS_ID=?;";
			this.jdbc.update(upQ, dTL_DESC, pRICE, CommonFunctions.getCurrentDateTime(),
					CommonFunctions.getCurrentDateTime(), psId);

			for (int i = 0; i < varients.length(); i++) {
				JSONObject objects = varients.getJSONObject(i);
				JSONArray keys = objects.names();
				String REFERENCE_ID = "", VALUE = "";
				for (int j = 0; j < keys.length(); ++j) {

					String key = keys.getString(j);
					String value = objects.getString(key);

					if (key.equalsIgnoreCase("VALUE")) {
						VALUE = value;
					} else if (key.equalsIgnoreCase("REFERENCE_ID")) {
						REFERENCE_ID = value;
					}
				}

				String insertDtl = "UPDATE product_sub_dtl SET VALUE=? WHERE PSD_ID=?";
				this.jdbc.update(insertDtl, VALUE, REFERENCE_ID);
			}

		} catch (Exception e) {
			logger.error("updatevarDtl DAO error-->" + e);
		}

	}

	public String getProdImgCnt(int vendorId, int psid, String refCode) {
		String returnMsg = "";
		try {
			String getQ = "select count(*) from file_manager where REFERENCE_CODE = ? and REFERENCE_ID = ? and VENDOR_ID=?";
			returnMsg = this.jdbc.queryForObject(getQ, new Object[] { refCode, psid, vendorId }, String.class);
		} catch (Exception e) {
			logger.error("getProdImgCnt DAO error-->" + e);
		}
		return returnMsg;
	}

	public String updateProdStatus(int vendorId, int psid) {
		String returnMsg = "";
		try {
			String getQ = "select STATUS from product_sub_hdr where PS_ID =? and VENDOR_ID =?";
			String currStatus = this.jdbc.queryForObject(getQ, new Object[] { psid, vendorId }, String.class);
			if (currStatus.equalsIgnoreCase("ST008")) {

				getQ = "SELECT STATUS_DESC FROM status_mst where STATUS_CODE = \"ST009\"";
				returnMsg = this.jdbc.queryForObject(getQ, String.class);

				String upq = "UPDATE product_sub_hdr SET STATUS='ST009' WHERE PS_ID=?";
				this.jdbc.update(upq, psid);

			} else {
				getQ = "SELECT STATUS_DESC FROM status_mst where STATUS_CODE = \"ST008\"";
				returnMsg = this.jdbc.queryForObject(getQ, String.class);

				String upq = "UPDATE product_sub_hdr SET STATUS='ST008' WHERE PS_ID =?";
				this.jdbc.update(upq, psid);
			}
		} catch (Exception e) {
			logger.error("updateProdStatus DAO error-->" + e);
		}
		return returnMsg;
	}

}
