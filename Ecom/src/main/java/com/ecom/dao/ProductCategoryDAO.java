package com.ecom.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecom.config.Messages;
import com.ecom.domain.BrandDtl;
import com.ecom.domain.GridViewResponse;
import com.ecom.domain.ProdByProdCat;
import com.ecom.domain.ProductCategoryDtl;
import com.ecom.domain.ProductCategoryHdr;
import com.ecom.domain.ProductHdrDtl;
import com.ecom.domain.SecondCateResp;
import com.ecom.domain.VarientDtlForGrid;
import com.ecom.domain.VarientValueDtl;
import com.ecom.jpaEntity.VarientProd;
import com.ecom.rowmapper.BrandDtlRowMapper;
import com.ecom.rowmapper.ProdByCatRowMapper;
import com.ecom.rowmapper.ProductCategoryDtlRowMapper;
import com.ecom.rowmapper.ProductCategoryHdrRowMapper;
import com.ecom.rowmapper.ProductHdrDtlRowMapper;
import com.ecom.rowmapper.SecListRowMapper;
import com.ecom.rowmapper.VarientDtlForGridRowMapper;
import com.ecom.rowmapper.VarientProdRowMapper;
import com.ecom.rowmapper.VarientValueDtlRowMapper;

@Repository
@Transactional
public class ProductCategoryDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProductCategoryDAO.class);
	@Autowired
	JdbcTemplate jdbc;
	
	public String insertProductCategory(String categoryCode, String categoryName, String isPrimary,
			String primaryCategory, String secondaryCategory, String vendorId) {
		String retMsg = "";
		try {
			int sucCnt = 0;
			String insertHdrQry = "INSERT INTO `product_category_hdr` (`PCM_CODE`, `PCM_DESC`, `IS_PRIMARY`, `IS_ACTIVE`, `VENDOR_ID`) VALUES (?, ?, ?, ?, ?)";
			String insertDtlQry = "INSERT INTO `product_category_dtl` (`PARENT_CATE_HDR_ID`, `PCM_CODE`, `PCM_DESC`) VALUES (?,?,?)";
			String categoryCodeCheck = "select count(*) from product_category_hdr where PCM_CODE=? or PCM_DESC=?";
			if (!isPrimary.equalsIgnoreCase("1")) {
				int catCount = this.jdbc.queryForObject(categoryCodeCheck, new Object[] { categoryCode, categoryName },
						Integer.class);
				if (catCount > 0) {
					return Messages.ProductCatDuplicate;
				} else {
					int insPc = this.jdbc.update(insertHdrQry,
							new Object[] { categoryCode, categoryName, isPrimary, 1, vendorId });
					if (insPc > 0) {
						sucCnt++;
					}
				}

			} else if (secondaryCategory.equalsIgnoreCase("") && !primaryCategory.equalsIgnoreCase("")) {
				int insPc = this.jdbc.update(insertDtlQry,
						new Object[] { primaryCategory, categoryCode, categoryName });
				if (insPc > 0) {
					sucCnt++;
				}
			} else {
				int insPc = this.jdbc.update(insertDtlQry,
						new Object[] { secondaryCategory, categoryCode, categoryName });
				if (insPc > 0) {
					sucCnt++;
				}
			}

			if (sucCnt > 0) {
				retMsg = Messages.ProductCatCreated;
			} else {
				retMsg = Messages.ProductCatFailed;
			}

		} catch (Exception e) {
			logger.error("insertProductCategory DAO error-->" + e);
		}
		return retMsg;

	}

	public List<ProductCategoryHdr> getPrimaryProdCategory(String vendorId) {
		List<ProductCategoryHdr> pcList = null;
		try {
			String getProdCatQry = "select * from product_category_hdr where IS_PRIMARY=1 and IS_ACTIVE=1 and VENDOR_ID=?";
			pcList = this.jdbc.query(getProdCatQry, new Object[] { vendorId }, new ProductCategoryHdrRowMapper());
		} catch (Exception e) {
			logger.error("getPrimaryProdCategory DAO error-->" + e);
		}
		return pcList;
	}

	// Getting sub categories from a category
	public String getCategoriesFromCatId(String catId, String catCode, JdbcTemplate jdbc2) {
		String catHdr = catId+",";
		try {
			int pExist = 1;

			List<ProductCategoryDtl> pcdList = new ArrayList<ProductCategoryDtl>();
			String getProdCatQry = "select * from product_category_dtl where  IS_ACTIVE=1  and PARENT_CATE_HDR_ID in (select PARENT_CATE_HDR_ID from product_category_hdr where PCM_CODE in (?))";
			pcdList = jdbc2.query(getProdCatQry, new Object[] { catCode }, new ProductCategoryDtlRowMapper());
			while (pExist == 1) {

				if (pcdList.size() > 0) {
					String dtlString = "";
					String codeString = "";
					for (int pl = 0; pl < pcdList.size(); pl++) {
						if (pl == pcdList.size() - 1) {
							dtlString = dtlString + pcdList.get(pl).getProdcatDtlId();
							codeString = codeString + pcdList.get(pl).getPcmCode();
						} else {
							dtlString = dtlString + "," + pcdList.get(pl).getProdcatDtlId();
							codeString = codeString + "," + pcdList.get(pl).getPcmCode();
						}

					}
					pcdList = null;
					pcdList = jdbc2.query(getProdCatQry, new Object[] { codeString },
							new ProductCategoryDtlRowMapper());
					catHdr = catHdr + dtlString;
				} else {
					pExist = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoriesFromCatId error-->" + e);
		}
		return catHdr;

	}

	public JSONArray getProdCategoryByPrimary(String vendorId) {
		JSONArray pcObj = new JSONArray();
		try {
			pcObj = getJsonForCategory("getAll", null, null, null, vendorId);
		} catch (Exception e) {
			logger.error("getProdCategoryByPrimary DAO error-->" + e);
		}
		return pcObj;
	}

	public JSONArray getSubcategoryFromChild(String pcmCode) {
		JSONArray subArray = new JSONArray();
		try {
			String getProdCatQry = "select * from product_category_hdr where  IS_ACTIVE=1 and PCM_CODE=?";
			List<ProductCategoryHdr> pList = this.jdbc.query(getProdCatQry, new Object[] { pcmCode },
					new ProductCategoryHdrRowMapper());
			if (pList.size() > 0) {
				String hdrId = pList.get(0).getParentCatHdr();
				String getProdSubQry = "select * from product_category_dtl where  IS_ACTIVE=1  and PARENT_CATE_HDR_ID=?";
				List<ProductCategoryDtl> pcdList = this.jdbc.query(getProdSubQry, new Object[] { hdrId },
						new ProductCategoryDtlRowMapper());
				for (int i = 0; i < pcdList.size(); i++) {
					JSONObject pmObj = new JSONObject();
					pmObj.put("productCategoryId", pcdList.get(i).getProdcatDtlId());
					pmObj.put("prodCatDesc", pcdList.get(i).getPcmDesc());
					pmObj.put("catCode", pcdList.get(i).getPcmCode());
					subArray.put(pmObj);
				}
			}
		} catch (Exception e) {
			logger.error("getSubcategoryFromChild method error-->" + e);
		}
		return subArray;
	}

	public JSONArray getTrendingCategories(String vendorId) {
		JSONArray pcObj = new JSONArray();
		try {
			String getTopCategories = "SELECT DISTINCT\r\n"
					+ "    ph.PARENT_CATE_HDR_ID, ph.*, COUNT(ph.PARENT_CATE_HDR_ID) as pcCnt\r\n" + "FROM\r\n"
					+ "    product_category_hdr ph,\r\n" + "    product_details pd,\r\n"
					+ "    order_information_dtl od\r\n" + "WHERE\r\n" + "    od.PROD_ID = pd.PROD_ID\r\n"
					+ "        AND pd.PARENT_CATE_HDR_ID = ph.PARENT_CATE_HDR_ID\r\n"
					+ "        AND pd.VENDOR_ID = ?\r\n" + "GROUP BY ph.PARENT_CATE_HDR_ID order by pcCnt desc limit 5";
			List<ProductCategoryHdr> pcList = this.jdbc.query(getTopCategories, new Object[] { vendorId },
					new ProductCategoryHdrRowMapper());
			if (pcList.size() > 0) {
				for (int i = 0; i < pcList.size(); i++) {
					List<ProductCategoryDtl> pcdList = null;
					JSONObject pmObj = new JSONObject();
					pmObj.put("productCategoryId", pcList.get(i).getPcmCode());
					pmObj.put("prodCatDesc", pcList.get(i).getPcmDesc());
					pmObj.put("catCode", pcList.get(i).getPcmCode());
					String getProdCatQry = "select * from product_category_dtl where  IS_ACTIVE=1  and PARENT_CATE_HDR_ID=?";
					pcdList = this.jdbc.query(getProdCatQry, new Object[] { pcList.get(i).getParentCatHdr() },
							new ProductCategoryDtlRowMapper());
					JSONArray dtlArray = new JSONArray();
					for (int j = 0; j < pcdList.size(); j++) {
						JSONObject pcSubList = new JSONObject();
						pcSubList.put("productCategoryId", pcdList.get(j).getPcmCode());
						pcSubList.put("prodCatDesc", pcdList.get(j).getPcmDesc());
						JSONArray subArray = getSubcategoryFromChild(pcdList.get(j).getPcmCode());
						pcSubList.put("child", subArray);
						dtlArray.put(pcSubList);
					}
					pmObj.put("child", dtlArray);
					pcObj.put(pmObj);
				}

			} else {
				return getProdCategoryByPrimary(vendorId);
			}
		} catch (Exception e) {
			logger.error("getTrendingCategories DAO error-->" + e);
		}
		return pcObj;
	}

	public GridViewResponse getProductByCategories(String vendorId, String catCode, String catId) {
		GridViewResponse gList = new GridViewResponse();
		try {
			String catIdString = catId;
			List<ProductHdrDtl> pList = null;
			String checkIfPrimary = "select count(*) from product_category_hdr where PCM_CODE=? and IS_PRIMARY=1";
			int pCnt = this.jdbc.queryForObject(checkIfPrimary, new Object[] { catCode }, Integer.class);
			String getProdList = "select * from product_details pd,product_sub_hdr ps,brand_mst bm where pd.PROD_ID=ps.PROD_ID and pd.BM_ID=bm.BM_ID and pd.PARENT_CATE_DTL_ID in (?)";
			if (pCnt == 0) {
				catIdString = getCategoriesFromCatId(catId, catCode,this.jdbc);

				pList = this.jdbc.query(getProdList, new Object[] { catIdString }, new ProductHdrDtlRowMapper());
			} else {
				getProdList = "select * from product_details pd,product_sub_hdr ps,brand_mst bm where pd.PROD_ID=ps.PROD_ID and pd.BM_ID=bm.BM_ID and pd.PARENT_CATE_HDR_ID in (?)";
				pList = this.jdbc.query(getProdList, new Object[] { catIdString }, new ProductHdrDtlRowMapper());
			}

			gList.setProdList(pList);

			if (pList.size() > 0) {

				String catHdrId = pList.get(0).getPcHdrId();
				JSONArray catJson = getJsonForCategory("byId", catHdrId, null, null, vendorId);
				gList.setCatString(catJson.toString());
				String getBrandQry = "select distinct bm.* from brand_mst bm,product_details pd where pd.BM_ID=bm.BM_ID and pd.PARENT_CATE_DTL_ID in (?)";
				List<BrandDtl> bList = this.jdbc.query(getBrandQry, new Object[] { catIdString },
						new BrandDtlRowMapper());
				gList.setbList(bList);
				String getMinMaxPrice = "SELECT \r\n" + "    CONCAT(MAX(ps.PRICE), '|', MIN(ps.PRICE))\r\n" + "FROM\r\n"
						+ "    product_details pd,\r\n" + "    product_sub_hdr ps,\r\n" + "    brand_mst bm\r\n"
						+ "WHERE\r\n" + "    pd.PROD_ID = ps.PROD_ID\r\n" + "        AND pd.BM_ID = bm.BM_ID\r\n"
						+ "        AND pd.PARENT_CATE_DTL_ID IN (?)";
				String minMaxPrice = this.jdbc.queryForObject(getMinMaxPrice, new Object[] { catIdString },
						String.class);
				gList.setMinMaxPrice(minMaxPrice);
				String getVraientList = "SELECT DISTINCT\r\n" + "    pv.*\r\n" + "FROM\r\n"
						+ "    product_details pd,\r\n" + "    product_sub_hdr ps,\r\n" + "    product_sub_dtl psd,\r\n"
						+ "    product_varient_type pv\r\n" + "WHERE\r\n" + "    pd.PROD_ID = ps.PROD_ID\r\n"
						+ "        AND ps.PS_ID = psd.PS_ID\r\n"
						+ "        AND psd.VARIENT_TYPE_CODE = pv.VARIENT_TYPE_CODE\r\n"
						+ "        AND pd.PARENT_CATE_DTL_ID IN (?)";
				List<VarientDtlForGrid> vlList = this.jdbc.query(getVraientList, new Object[] { catIdString },
						new VarientDtlForGridRowMapper());
				for (int i = 0; i < vlList.size(); i++) {
					String vtCode = vlList.get(i).getVarientTypeCode();
					String getValuesQry = "SELECT DISTINCT\r\n" + "    psd.VALUE\r\n" + "FROM\r\n"
							+ "    product_details pd,\r\n" + "    product_sub_hdr ps,\r\n"
							+ "    product_sub_dtl psd,\r\n" + "    product_varient_type pv\r\n" + "WHERE\r\n"
							+ "    pd.PROD_ID = ps.PROD_ID\r\n" + "        AND ps.PS_ID = psd.PS_ID\r\n"
							+ "        AND psd.VARIENT_TYPE_CODE = pv.VARIENT_TYPE_CODE\r\n"
							+ "        AND pd.PARENT_CATE_DTL_ID IN (?) and pv.VARIENT_TYPE_CODE=?";
					List<VarientValueDtl> valueList = this.jdbc.query(getValuesQry,
							new Object[] { catIdString, vtCode }, new VarientValueDtlRowMapper());
					vlList.get(i).setVlList(valueList);

				}
				gList.setVlList(vlList);
			}
		} catch (Exception e) {
			logger.error("getProductByCategories DAO error-->" + e);
		}
		return gList;
	}

	public JSONArray getJsonForCategory(String type, String productCategoryId, String prodCatDesc, String catCode,
			String vendorId) {
		JSONArray pcObj = new JSONArray();
		try {
			List<ProductCategoryHdr> pcList = new ArrayList<ProductCategoryHdr>();
			if (type.equalsIgnoreCase("getAll")) {
				pcList = getPrimaryProdCategory(vendorId);
			} else {
				String getPrimaryList = "select * from product_category_hdr where IS_PRIMARY=1 and IS_ACTIVE=1 and VENDOR_ID=? and PARENT_CATE_HDR_ID=?";
				pcList = this.jdbc.query(getPrimaryList, new Object[] { vendorId, productCategoryId },
						new ProductCategoryHdrRowMapper());
			}

			for (int i = 0; i < pcList.size(); i++) {
				List<ProductCategoryDtl> pcdList = null;
				JSONObject pmObj = new JSONObject();
				pmObj.put("productCategoryId", pcList.get(i).getParentCatHdr());
				pmObj.put("prodCatDesc", pcList.get(i).getPcmDesc());
				pmObj.put("catCode", pcList.get(i).getPcmCode());
				String getProdCatQry = "select * from product_category_dtl where  IS_ACTIVE=1  and PARENT_CATE_HDR_ID=?";
				pcdList = this.jdbc.query(getProdCatQry, new Object[] { pcList.get(i).getParentCatHdr() },
						new ProductCategoryDtlRowMapper());
				JSONArray dtlArray = new JSONArray();
				for (int j = 0; j < pcdList.size(); j++) {
					JSONObject pcSubList = new JSONObject();
					pcSubList.put("productCategoryId", pcdList.get(j).getProdcatDtlId());
					pcSubList.put("catCode", pcdList.get(j).getPcmCode());
					pcSubList.put("prodCatDesc", pcdList.get(j).getPcmDesc());
					JSONArray subArray = getSubcategoryFromChild(pcdList.get(j).getPcmCode());
					pcSubList.put("child", subArray);
					dtlArray.put(pcSubList);
				}
				pmObj.put("child", dtlArray);
				pcObj.put(pmObj);
			}
		} catch (Exception e) {
			logger.error("getJsonForCategory error-->" + e);
		}
		return pcObj;
	}

	public List<ProdByProdCat> getProdByCat(String vendorId, String catId) {
		List<ProdByProdCat> prodCat = null;
		try {

			String getQ = "SELECT \r\n" + "    pd.PROD_CODE,\r\n" + "    pd.PROD_DESC,\r\n" + "    pd.PROD_ID,\r\n"
					+ "    pd.PROD_NAME,\r\n" + "    pd.TAGS,\r\n" + "    phdr.PCM_DESC as PrimartDesc,\r\n"
					+ "    pdtl.PCM_DESC as SecDesc\r\n" + "FROM\r\n" + "    product_details pd,\r\n"
					+ "    product_category_hdr phdr,\r\n" + "    product_category_dtl pdtl\r\n" + "WHERE\r\n"
					+ "   phdr.PARENT_CATE_HDR_ID = pd.PARENT_CATE_HDR_ID\r\n"
					+ "        and pdtl.PARENT_CAT_DTL_ID = pd.PARENT_CATE_DTL_ID\r\n"
					+ "        AND pd.PARENT_CATE_HDR_ID = ?\r\n" + "        AND pd.VENDOR_ID = ?";
			prodCat = this.jdbc.query(getQ, new Object[] { catId, vendorId }, new ProdByCatRowMapper());

		} catch (Exception e) {
			logger.error("getProdByCat error-->" + e);
		}
		return prodCat;
	}

	public List<VarientProd> getVarient(String vendorId) {
		List<VarientProd> varpro = null;
		try {

			String getQ = "SELECT VARIENT_TYPE_CODE,VARIENT_TYPE_DESC FROM product_varient_type where VENDOR_ID=? order by VARIENT_TYPE_DESC";
			varpro = this.jdbc.query(getQ, new Object[] { vendorId }, new VarientProdRowMapper());

		} catch (Exception e) {
			logger.error("getProdByCat error-->" + e);
		}
		return varpro;
	}

	public List<SecondCateResp> getSecCate(String vendorId, String cateId) {
		List<SecondCateResp> secList = null;
		try {

			String getQ = "select PARENT_CAT_DTL_ID, PCM_CODE,PCM_DESC from product_category_dtl where PARENT_CATE_HDR_ID =? and IS_ACTIVE =?";
			secList = this.jdbc.query(getQ, new Object[] { cateId, 1 }, new SecListRowMapper());

			for (int h = 0; h < secList.size(); h++) {

				List<SecondCateResp> subList = null;
				getQ = "SELECT \r\n" + "  dtl.PARENT_CAT_DTL_ID,  dtl.PCM_CODE , dtl.PCM_DESC\r\n" + "FROM\r\n"
						+ "    product_category_dtl dtl,\r\n" + "    product_category_hdr hdr\r\n" + "WHERE\r\n"
						+ "    hdr.PARENT_CATE_HDR_ID = dtl.PARENT_CATE_HDR_ID\r\n"
						+ "    and hdr.PCM_CODE = ? and hdr.VENDOR_ID =?";
				subList = this.jdbc.query(getQ, new Object[] { secList.get(h).getProdCatCode(), vendorId },
						new SecListRowMapper());
				secList.get(h).setSubList(subList);
			}

		} catch (Exception e) {
			logger.error("getSecCate error-->" + e);
		}
		return secList;
	}

	

}