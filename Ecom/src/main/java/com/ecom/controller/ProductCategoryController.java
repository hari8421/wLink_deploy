package com.ecom.controller;

import java.util.List;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.ecom.domain.BrandDtl;
import com.ecom.domain.GridViewResponse;
import com.ecom.domain.ProdByProdCat;
import com.ecom.domain.ProductCategoryHdr;
import com.ecom.domain.ProductCategoryRequest;
import com.ecom.domain.SecondCateResp;
import com.ecom.jpaEntity.VarientProd;
import com.ecom.service.ProductCategoryService;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductCategoryController {
	private static final Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
	@Autowired
	ProductCategoryService productCategoryService;

	@RequestMapping("/insertProductCategory")
	@PostMapping
	public ResponseEntity<String> insertProductCategory(@RequestBody ProductCategoryRequest productCategoryRequest) {
		String responseMsg = "";
		try {
			responseMsg = productCategoryService.insertProductCategory(productCategoryRequest);
		} catch (Exception e) {
			logger.error("insertProductCategory error-->" + e);
		}

		return ResponseEntity.ok(responseMsg);
	}

	// Getting primary category list
	@RequestMapping("/getPrimaryProdCategory/{vendorId}")
	@GetMapping
	public ResponseEntity<List<ProductCategoryHdr>> getPrimaryProdCategory(
			@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<ProductCategoryHdr> pcList = null;
		try {
			pcList = productCategoryService.getPrimaryProdCategory(vendorId);
		} catch (Exception e) {
			logger.error("getPrimaryProdCategory error-->" + e);
		}

		return ResponseEntity.ok(pcList);
	}

	// Getting all categories and their children
	@RequestMapping("/getProdCategoryByPrimary/{vendorId}")
	@GetMapping
	public ResponseEntity<String> getProdCategoryByPrimary(
			@PathVariable(value = "vendorId", required = true) String vendorId) {
		JSONArray pcJson = null;
		try {
			pcJson = productCategoryService.getProdCategoryByPrimary(vendorId);
		} catch (Exception e) {
			logger.error("getProdCategoryByPrimary error-->" + e);
		}

		return ResponseEntity.ok(pcJson.toString());
	}

	// Getting trending categories based on order
	@RequestMapping("/getTrendingCategories/{vendorId}")
	@GetMapping
	public ResponseEntity<String> getTrendingCategories(
			@PathVariable(value = "vendorId", required = true) String vendorId) {
		JSONArray pcJson = null;
		try {
			pcJson = productCategoryService.getTrendingCategories(vendorId);
		} catch (Exception e) {
			logger.error("getTrendingCategories error-->" + e);
		}

		return ResponseEntity.ok(pcJson.toString());
	}

	// Getting products based on category
	@RequestMapping("/getProductByCategories/{catId}/{catCode}/{vendorId}")
	@GetMapping
	public ResponseEntity<GridViewResponse> getProductByCategories(
			@PathVariable(value = "vendorId", required = true) String vendorId,
			@PathVariable(value = "catId", required = true) String catId,
			@PathVariable(value = "catCode", required = true) String catCode) {
		GridViewResponse gList = null;
		try {
			gList = productCategoryService.getProductByCategories(vendorId, catCode, catId);
		} catch (Exception e) {
			logger.error("getProductByCategories error-->" + e);
		}

		return ResponseEntity.ok(gList);
	}

	@RequestMapping("/getProdByCat/{catId}/{vendorId}")
	@GetMapping
	public ResponseEntity<List<ProdByProdCat>> getProdByCat(
			@PathVariable(value = "vendorId", required = true) String vendorId,
			@PathVariable(value = "catId", required = true) String catId) {
		List<ProdByProdCat> returnList = null;
		try {
			returnList = productCategoryService.getProdByCat(vendorId, catId);
		} catch (Exception e) {
			logger.error("getProdByCat error-->" + e);
		}

		return ResponseEntity.ok(returnList);
	}

	@RequestMapping("/getVarient/{vendorId}")
	@GetMapping
	public ResponseEntity<List<VarientProd>> getVarient(
			@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<VarientProd> returnList = null;
		try {
			returnList = productCategoryService.getVarient(vendorId);
		} catch (Exception e) {
			logger.error("getProdByCat error-->" + e);
		}

		return ResponseEntity.ok(returnList);
	}

	@RequestMapping("/getSecCate/{vendorId}/{CatId}")
	@GetMapping
	public ResponseEntity<List<SecondCateResp>> getSecCate(
			@PathVariable(value = "vendorId", required = true) String vendorId,
			@PathVariable(value = "CatId", required = true) String cateId) {
		List<SecondCateResp> returnList = null;
		try {
			returnList = productCategoryService.getSecCate(vendorId, cateId);
		} catch (Exception e) {
			logger.error("getProdByCat error-->" + e);
		}

		return ResponseEntity.ok(returnList);
	}
	
	

}