package com.ecom.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.domain.AdminIndProdMst;
import com.ecom.domain.ProductDetailsByName;
import com.ecom.domain.ProductHdrDtl;
import com.ecom.domain.ProductSubHdrADtl;
import com.ecom.domain.SingleProdDetails;
import com.ecom.jpaEntity.ProductJpaEntity;
import com.ecom.repository.ProductJpaRepository;
import com.ecom.requestEntity.insertProductMstRequest;
import com.ecom.service.ProductMasterService;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductMasterController {
	@Autowired
	ProductMasterService productMasterService;
	@Autowired
	ProductJpaRepository productJpaRepository;

	private static final Logger logger = LoggerFactory.getLogger(ReasonCodeController.class);

	@CrossOrigin(maxAge = 3600)
	@RequestMapping(value = "/getProductDetailsByPrimaryCategory")
	@PostMapping
	public List<ProductHdrDtl> getProductDetailsByPrimaryCategory(@RequestParam("vendorId") String vendorId,
			@RequestParam("primaryCategory") String pCategory) {
		List<ProductHdrDtl> pList = null;
		try {
			pList = productMasterService.getProductDetailsByPrimaryCategory(vendorId, pCategory);
		} catch (Exception e) {
			logger.error("getProductDetailsByPrimaryCategory controller error-->" + e);
		}

		return pList;
	}

	@CrossOrigin(maxAge = 3600)
	@RequestMapping(value = "/getSingleProdDet/{vendorId}/{psId}")
	@GetMapping
	public List<SingleProdDetails> getSingleProdDet(@PathVariable(value = "vendorId", required = true) String vendorId,
			@PathVariable(value = "psId", required = true) String psId) {
		List<SingleProdDetails> pList = null;
		try {
			pList = productMasterService.getSingleProdDet(vendorId, psId);
		} catch (Exception e) {
			logger.error("getSingleProdDet controller error-->" + e);
		}

		return pList;
	}

	@CrossOrigin(maxAge = 3600)
	@PostMapping("getProductByFilter")
	public ResponseEntity<List<ProductHdrDtl>> getProductByFilter(@RequestParam("filterObj") String filterObj) {
		List<ProductHdrDtl> plist = null;
		try {

			JSONObject jsonObj = new JSONObject(filterObj);
			JSONArray filterArray = jsonObj.getJSONArray("filterItem");
			JSONArray varientArray = jsonObj.getJSONArray("varientItem");
			JSONArray categoryArray = jsonObj.getJSONArray("parentItem");
			plist = productMasterService.getProductByFilter(filterArray, varientArray, categoryArray);

		} catch (Exception ex) {
			logger.error("getProductByFilter  method exception" + ex);
		}
		return ResponseEntity.ok(plist);
	}

	@RequestMapping("/insertProductMst")
	@PostMapping
	public ResponseEntity<String> insertProductMst(@RequestBody insertProductMstRequest insertProd) {
		return new ResponseEntity<String>(productMasterService.insertProductMst(insertProd), HttpStatus.OK);
	}

	@RequestMapping("/getProdVDtl/{prodId}/{vendorId}")
	@GetMapping
	public ResponseEntity<List<ProductSubHdrADtl>> getProdExtnDtl(
			@PathVariable(value = "prodId", required = true) String prodId,
			@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<ProductSubHdrADtl> returnList = null;
		try {
			returnList = productMasterService.getProdExtnDtl(prodId, vendorId);
		} catch (Exception e) {
			logger.error("getProdByCat error-->" + e);
		}

		return ResponseEntity.ok(returnList);
	}

	@GetMapping("getBestSellers/{vendorId}")
	public ResponseEntity<List<ProductHdrDtl>> getBestSellers(
			@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<ProductHdrDtl> plist = null;
		try {

			plist = productMasterService.getBestSellers(vendorId);

		} catch (Exception ex) {
			logger.error("getBestSellers  method exception" + ex);
		}
		return ResponseEntity.ok(plist);
	}

	@GetMapping("getPSByVendor/{vendorId}")
	public ResponseEntity<List<ProductHdrDtl>> getPSByVendor(
			@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<ProductHdrDtl> plist = null;
		try {

			plist = productMasterService.getPSByVendor(vendorId);

		} catch (Exception ex) {
			logger.error("getBestSellers  method exception" + ex);
		}
		return ResponseEntity.ok(plist);
	}

	@PostMapping("getProductsByName")
	public ResponseEntity<ProductDetailsByName> getProductsByName(@RequestParam("psNobj") String psNobj) {
		ProductDetailsByName plist = new ProductDetailsByName();
		try {

			JSONObject jsonObj = new JSONObject(psNobj);
			JSONArray filterArray = jsonObj.getJSONArray("psnItem");

			plist = productMasterService.getProductsByName(filterArray);

		} catch (Exception ex) {
			logger.error("getProductsByName  method exception" + ex);
		}
		return ResponseEntity.ok(plist);
	}

	@GetMapping("getNewArrivals/{vendorId}/{pageLimit}")
	public ResponseEntity<List<ProductHdrDtl>> getNewArrivals(
			@PathVariable(value = "vendorId", required = true) String vendorId,
			@PathVariable(value = "pageLimit", required = true) String pageLimit) {
		List<ProductHdrDtl> plist = null;
		try {

			plist = productMasterService.getNewArrivals(vendorId, pageLimit);

		} catch (Exception ex) {
			logger.error("getNewArrivals  method exception" + ex);
		}
		return ResponseEntity.ok(plist);
	}

	@CrossOrigin(maxAge = 3600)
	@RequestMapping(value = "/getAllProducts/{vendorId}/{pageNo}/{size}")
	@GetMapping
	public Page<ProductJpaEntity> getAllProducts(@PathVariable(value = "vendorId", required = true) String vendorId,
			@PathVariable(value = "pageNo", required = true) String pageNo,
			@PathVariable(value = "size", required = true) String size) {

		Page<ProductJpaEntity> pList = null;
		try {
			Pageable paging = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(size), Sort.by("prodId"));
			pList = productJpaRepository.findByvId(vendorId, paging);
		} catch (Exception e) {
			logger.error("getSingleProdDet controller error-->" + e);
		}

		return pList;
	}

	@RequestMapping("/getProdByProdId/{prodId}/{vendorId}")
	@GetMapping
	public ResponseEntity<List<AdminIndProdMst>> getProdByProdId(
			@PathVariable(value = "prodId", required = true) String prodId,
			@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<AdminIndProdMst> returnList = null;
		try {
			returnList = productMasterService.getProdByProdId(prodId, vendorId);
		} catch (Exception e) {
			logger.error("getProdByCat error-->" + e);
		}

		return ResponseEntity.ok(returnList);
	}

	@PostMapping("updateProdMst")
	public ResponseEntity<String> updateProdMst(@RequestParam("updateParam") String updateParam) {
		logger.info(" getScheduleDtlInfo method Start");
		String returnMsg = "";

		JSONObject jsonObj = new JSONObject(updateParam);
		returnMsg = productMasterService.updateProdMst(jsonObj);
		logger.info(" updateProdMst method End");

		return new ResponseEntity<String>(returnMsg, HttpStatus.OK);
	}
	
	
	@GetMapping("getProdImgCnt/{vendorId}/{psid}/{refCode}")
	public ResponseEntity<String> getProdImgCnt(@PathVariable(value = "vendorId", required = true) int vendorId,
			@PathVariable(value = "psid", required = true) int psid,@PathVariable(value = "refCode", required = true) String refCode) {
		logger.info(" getImgCnt method Start");
		String returnMsg = "";
		returnMsg = productMasterService.getProdImgCnt(vendorId,psid,refCode);
		logger.info(" getImgCnt method End");

		return new ResponseEntity<String>(returnMsg, HttpStatus.OK);
	}
	
	@GetMapping("updateProdStatus/{vendorId}/{psid}")
	public ResponseEntity<String> updateProdStatus(@PathVariable(value = "vendorId", required = true) int vendorId,
			@PathVariable(value = "psid", required = true) int psid) {
		logger.info(" updateProdStatus method Start");
		String returnMsg = "";
		returnMsg = productMasterService.updateProdStatus(vendorId,psid);
		logger.info(" updateProdStatus method End");

		return new ResponseEntity<String>(returnMsg, HttpStatus.OK);
	}
	
}
