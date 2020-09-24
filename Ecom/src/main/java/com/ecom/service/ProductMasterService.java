package com.ecom.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.ProductMasterDAO;
import com.ecom.domain.AdminIndProdMst;
import com.ecom.domain.ProductDetailsByName;
import com.ecom.domain.ProductHdrDtl;
import com.ecom.domain.ProductSubHdrADtl;
import com.ecom.domain.SingleProdDetails;
import com.ecom.requestEntity.insertProductMstRequest;

@Service
public class ProductMasterService {

	@Autowired
	ProductMasterDAO pDao;

	private static final Logger logger = LoggerFactory.getLogger(ProductMasterService.class);

	public List<ProductHdrDtl> getProductDetailsByPrimaryCategory(String vendorId, String pCategory) {
		List<ProductHdrDtl> pList = null;
		try {
			pList = pDao.getProductDetailsByPrimaryCategory(vendorId, pCategory);
		} catch (Exception e) {
			logger.error("get products by primary category service error-->" + e);
		}
		return pList;
	}

	public List<SingleProdDetails> getSingleProdDet(String vendorId, String psId) {
		// TODO Auto-generated method stub
		return pDao.getSingleProdDet(vendorId, psId);
	}

	public List<ProductHdrDtl> getProductByFilter(JSONArray filterArray, JSONArray varientArray,
			JSONArray categoryArray) {
		List<ProductHdrDtl> pList = null;
		try {
			String catId = "";
			String catCode = "";
			String priceRange = "";
			String brand = "";
			String vendorId = "";
			for (int i = 0; i < filterArray.length(); i++) {
				JSONObject objects = filterArray.getJSONObject(i);
				JSONArray keys = objects.names();

				for (int j = 0; j < keys.length(); ++j) {

					String key = keys.getString(j);
					String value = objects.getString(key);

					if (key.equalsIgnoreCase("catId")) {
						catId = value;
					} else if (key.equalsIgnoreCase("catCode")) {
						catCode = value;
					} else if (key.equalsIgnoreCase("priceRange")) {
						priceRange = value;
					} else if (key.equalsIgnoreCase("brand")) {
						brand = value;
					} else if (key.equalsIgnoreCase("vendorId")) {
						vendorId = value;
					}
				}
			}

			if (catCode.equalsIgnoreCase("")) {
				for (int i = 0; i < categoryArray.length(); i++) {
					JSONObject objects = categoryArray.getJSONObject(i);
					JSONArray keys = objects.names();

					for (int j = 0; j < keys.length(); ++j) {

						String key = keys.getString(j);
						String value = objects.getString(key);

						if (key.equalsIgnoreCase("catid")) {
							catId = value;
						} else if (key.equalsIgnoreCase("catCode")) {
							catCode = value;
						}
					}
				}
			}
			String minPrice = "0";
			String maxPrice = "1000000";
			String[] priceArray = priceRange.split("|");
			if (priceArray.length > 1) {
				maxPrice = priceArray[1];
				minPrice = priceArray[0];
			}

			String vCodeQry = "";

			for (int j = 0; j < varientArray.length(); j++) {
				JSONObject objects1 = varientArray.getJSONObject(j);
				JSONArray keys = objects1.names();
				String vCode = "";
				String valueString = "";
				for (int k = 0; k < keys.length(); k++) {

					String key = keys.getString(k);
					String value = objects1.getString(key);

					if (key.equalsIgnoreCase("vCode")) {
						vCode = value;
					} else if (key.equalsIgnoreCase("valueString")) {
						valueString = value;
					}

				}
				if (!vCode.equalsIgnoreCase("") && !valueString.equalsIgnoreCase("")) {
					if (j == varientArray.length() - 1) {
						vCodeQry = vCodeQry + " psd.VARIENT_TYPE_CODE=" + "'" + vCode + "'" + " AND VALUE IN("
								+ valueString + ")";
					} else {
						vCodeQry = vCodeQry + " psd.VARIENT_TYPE_CODE=" + "'" + vCode + "'" + " AND VALUE IN("
								+ valueString + ") OR ";
					}
				}
			}

			pList = pDao.getProductByFilter(catId, catCode, minPrice, maxPrice, brand, vendorId, vCodeQry);

		} catch (Exception e) {
			logger.error("getProductByFilter error-->" + e);
		}
		return pList;
	}

	public String insertProductMst(insertProductMstRequest insertProd) {
		return pDao.insertProductMst(insertProd);
	}

	public List<ProductSubHdrADtl> getProdExtnDtl(String prodId, String vendorId) {
		return pDao.getProdExtnDtl(prodId, vendorId);
	}

	public List<ProductHdrDtl> getBestSellers(String vendorId) {
		return pDao.getBestSellers(vendorId);
	}

	public List<ProductHdrDtl> getPSByVendor(String vendorId) {
		return pDao.getPSByVendor(vendorId);
	}

	public ProductDetailsByName getProductsByName(JSONArray filterArray) {
		String name = "";
		String vendorId = "";
		for (int i = 0; i < filterArray.length(); i++) {
			JSONObject objects = filterArray.getJSONObject(i);
			JSONArray keys = objects.names();

			for (int j = 0; j < keys.length(); ++j) {

				String key = keys.getString(j);
				String value = objects.getString(key);

				if (key.equalsIgnoreCase("name")) {
					name = value;
				} else if (key.equalsIgnoreCase("vendorId")) {
					vendorId = value;
				}
			}
		}
		return pDao.ProductDetailsByName(name, vendorId);
	}

	public List<ProductHdrDtl> getNewArrivals(String vendorId, String pageLimit) {
		return pDao.getNewArrivals(vendorId, pageLimit);
	}

	public List<AdminIndProdMst> getProdByProdId(String prodId, String vendorId) {
		return pDao.getProdByProdId(prodId, vendorId);
	}

	public String updateProdMst(JSONObject jsonObj) {

		JSONArray headArr = jsonObj.getJSONArray("headerObj");
		JSONArray proVup = jsonObj.getJSONArray("updateProVarObj");
		JSONArray proVin = jsonObj.getJSONArray("insertProVarObj");
		JSONArray proEin = jsonObj.getJSONArray("insertProExtObj");
		JSONArray proEup = jsonObj.getJSONArray("updateProExtObj");

		try {

			String BM_ID = "", PARENT_CATE_DTL_ID = "", PROD_CODE = "", PROD_NAME = "", PROD_DESC = "", TAGS = "",
					IS_ACTIVE = "", VENDOR_ID = "", PROD_ID = "";
			for (int i = 0; i < headArr.length(); i++) {
				JSONObject objects = headArr.getJSONObject(i);
				JSONArray keys = objects.names();

				for (int j = 0; j < keys.length(); ++j) {

					String key = keys.getString(j);
					String value = objects.getString(key);

					if (key.equalsIgnoreCase("BM_ID")) {
						BM_ID = value;
					} else if (key.equalsIgnoreCase("PARENT_CATE_DTL_ID")) {
						PARENT_CATE_DTL_ID = value;
					} else if (key.equalsIgnoreCase("PROD_CODE")) {
						PROD_CODE = value;
					} else if (key.equalsIgnoreCase("PROD_NAME")) {
						PROD_NAME = value;
					} else if (key.equalsIgnoreCase("PROD_DESC")) {
						PROD_DESC = value;
					} else if (key.equalsIgnoreCase("TAGS")) {
						TAGS = value;
					} else if (key.equalsIgnoreCase("IS_ACTIVE")) {
						IS_ACTIVE = value;
					} else if (key.equalsIgnoreCase("VENDOR_ID")) {
						VENDOR_ID = value;
					} else if (key.equalsIgnoreCase("PROD_ID")) {
						PROD_ID = value;
					}
				}
			}

			pDao.updateProd(BM_ID, PARENT_CATE_DTL_ID, PROD_CODE, PROD_NAME, PROD_DESC, TAGS, IS_ACTIVE, VENDOR_ID,
					PROD_ID);
			logger.info("header update completed.");
			setprodExt(proEin, "insert", PROD_ID);
			setprodExt(proEup, "update", PROD_ID);
			setprodVar(proVin, "insert", PROD_ID, VENDOR_ID);
			setprodVar(proVup, "update", PROD_ID, VENDOR_ID);
		} catch (Exception ex) {
			logger.error("update Prod mst error" + ex);
		}
		return "";
	}

	public void setprodExt(JSONArray proEin, String type, String prodId) {
		try {
			for (int i = 0; i < proEin.length(); i++) {
				JSONObject objects = proEin.getJSONObject(i);
				JSONArray keys = objects.names();
				String PROD_TITLE = "", PS_ID = "", PROD_KEY = "", PROD_VALUE = "", IS_ACTIVE = "";
				for (int j = 0; j < keys.length(); ++j) {

					String key = keys.getString(j);
					String value = objects.getString(key);

					if (key.equalsIgnoreCase("PROD_TITLE")) {
						PROD_TITLE = value;
					} else if (key.equalsIgnoreCase("PS_ID")) {
						PS_ID = value;
					} else if (key.equalsIgnoreCase("PROD_KEY")) {
						PROD_KEY = value;
					} else if (key.equalsIgnoreCase("PROD_VALUE")) {
						PROD_VALUE = value;
					} else if (key.equalsIgnoreCase("IS_ACTIVE")) {

						if (value.equalsIgnoreCase("True")) {
							IS_ACTIVE = "1";
						} else {
							IS_ACTIVE = "0";
						}
					}
				}

				if (type.equalsIgnoreCase("insert")) {
					pDao.insertExtraDtl(PROD_TITLE, PROD_KEY, PROD_VALUE, IS_ACTIVE, prodId);
				} else {
					pDao.updateExtraDtl(PROD_TITLE, PS_ID, PROD_KEY, PROD_VALUE, IS_ACTIVE);
				}
			}
		} catch (Exception ex) {
			logger.error("setprodExt method extn exp" + ex);
		}
	}

	public void setprodVar(JSONArray proVin, String type, String prodId, String vendorId) {
		try {
			for (int i = 0; i < proVin.length(); i++) {
				JSONObject objects = proVin.getJSONObject(i);
				JSONArray keys = objects.names();
				String DTL_DESC = "", PRICE = "", IS_ACTIVE = "", PS_ID = "";
				JSONArray VARIENTS = new JSONArray();
				for (int j = 0; j < keys.length(); ++j) {

					String key = keys.getString(j);
					String value = "";
					JSONArray subArr = new JSONArray();
					if (key.equalsIgnoreCase("VARIENTS")) {
						subArr = objects.getJSONArray(key);
					} else {
						value = objects.getString(key);
					}

					if (key.equalsIgnoreCase("DTL_DESC")) {
						DTL_DESC = value;
					} else if (key.equalsIgnoreCase("PRICE")) {
						PRICE = value;
					} else if (key.equalsIgnoreCase("PS_ID")) {
						PS_ID = value;
					} else if (key.equalsIgnoreCase("VARIENTS")) {
						VARIENTS = subArr;
					} else if (key.equalsIgnoreCase("IS_ACTIVE")) {

						if (value.equalsIgnoreCase("True")) {
							IS_ACTIVE = "1";
						} else {
							IS_ACTIVE = "0";
						}
					}
				}

				if (type.equalsIgnoreCase("insert")) {
					pDao.insertvarDtl(DTL_DESC, PRICE, IS_ACTIVE, prodId, VARIENTS, vendorId);
				} else {
					pDao.updatevarDtl(DTL_DESC, PRICE, IS_ACTIVE, prodId, VARIENTS, vendorId, PS_ID);
				}
			}
		} catch (Exception ex) {
			logger.error("setprodExt method extn exp" + ex);
		}
	}

	public String getProdImgCnt(int vendorId, int psid,String refCode) {
		// TODO Auto-generated method stub
		return pDao.getProdImgCnt(vendorId,psid,refCode);
	}

	public String updateProdStatus(int vendorId, int psid) {
		return pDao.updateProdStatus(vendorId,psid);
	}

}
