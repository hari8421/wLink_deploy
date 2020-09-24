package com.ecom.service;

import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.ProductCategoryDAO;
//import com.ecom.domain.BrandDtl;
import com.ecom.domain.GridViewResponse;
import com.ecom.domain.ProductCategoryHdr;
import com.ecom.domain.ProductCategoryRequest;
import com.ecom.domain.SecondCateResp;
import com.ecom.jpaEntity.VarientProd;
import com.ecom.domain.ProdByProdCat;

@Service
public class ProductCategoryService {

	private static final Logger logger = LoggerFactory.getLogger(ProductCategoryService.class);
	@Autowired
	ProductCategoryDAO productCategoryDAO;

	public String insertProductCategory(ProductCategoryRequest productCategoryRequest) {
		String retMsg = "";
		try {

			retMsg = productCategoryDAO.insertProductCategory(productCategoryRequest.getCategoryCode(),
					productCategoryRequest.getCategoryName(), productCategoryRequest.getIsPrimmary(),
					productCategoryRequest.getPrimaryCategory(), productCategoryRequest.getSecondaryCategory(),
					productCategoryRequest.getVendorId());
		} catch (Exception e) {
			logger.error("insertProductCategory service error-->" + e);
		}
		return retMsg;
	}

	public List<ProductCategoryHdr> getPrimaryProdCategory(String vendorId) {
		List<ProductCategoryHdr> pcList = null;
		try {
			pcList = productCategoryDAO.getPrimaryProdCategory(vendorId);
		} catch (Exception e) {
			logger.error("getPrimaryProdCategory service error-->" + e);
		}
		return pcList;
	}

	public JSONArray getProdCategoryByPrimary(String vendorId) {
		JSONArray pcJson = null;
		try {
			pcJson = productCategoryDAO.getProdCategoryByPrimary(vendorId);
		} catch (Exception e) {
			logger.error("getProdCategoryByPrimary service error-->" + e);
		}
		return pcJson;
	}

	public JSONArray getTrendingCategories(String vendorId) {
		JSONArray pcJson = null;
		try {
			pcJson = productCategoryDAO.getTrendingCategories(vendorId);
		} catch (Exception e) {
			logger.error("getTrendingCategories service error-->" + e);
		}
		return pcJson;
	}

	public GridViewResponse getProductByCategories(String vendorId, String catCode, String catId) {
		GridViewResponse gList = null;
		try {
			gList = productCategoryDAO.getProductByCategories(vendorId, catCode, catId);
		} catch (Exception e) {
			logger.error("getProductByCategories service error-->" + e);
		}
		return gList;
	}

	public List<ProdByProdCat> getProdByCat(String vendorId, String catId) {
		return productCategoryDAO.getProdByCat(vendorId, catId);
	}

	public List<VarientProd> getVarient(String vendorId) {
		return productCategoryDAO.getVarient(vendorId);
	}

	public List<SecondCateResp> getSecCate(String vendorId, String cateId) {
		return productCategoryDAO.getSecCate(vendorId,cateId);
	}

	

}
