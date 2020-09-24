package com.ecom.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.domain.BannerHdrDtl;
import com.ecom.domain.ProductComboHdr;
import com.ecom.domain.ProductComboResponse;
import com.ecom.jpaEntity.PcHdrJpaEntity;
import com.ecom.service.ProductComboService;


@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductComboCntroller {
	private static final Logger logger = LoggerFactory.getLogger(ProductComboCntroller.class);
	@Autowired ProductComboService productComboService;
	
	@RequestMapping("/getProductComboDetailsBiID/{vendorId}/{pchId}")
	@GetMapping
	public ResponseEntity<List<ProductComboResponse>> getProductComboDetails(@PathVariable(value = "vendorId", required = true) String vendorId,@PathVariable(value = "pchId", required = true) String pchId) {
		List<ProductComboResponse> pcList = null;
		logger.info("getProductComboDetailsBiID started");
		try {
			pcList = productComboService.getProductComboDetailsBiID(vendorId,pchId);
		} catch (Exception e) {
			logger.error("getProductComboDetailsBiID error-->" + e);
		}

		return ResponseEntity.ok(pcList);
	}
	@RequestMapping("/getProductBanerDetails/{vendorId}/{bType}")
	@GetMapping
	public ResponseEntity<List<BannerHdrDtl>> getProductBanerDetails(@PathVariable(value = "vendorId", required = true) String vendorId,@PathVariable(value = "bType", required = true) String bType) {
		List<BannerHdrDtl> pcList = null;
		logger.info("getProductBanerDetails started");
		try {
			pcList = productComboService.getProductBanerDetails(vendorId,bType);
		} catch (Exception e) {
			logger.error("getProductBanerDetails error-->" + e);
		}

		return ResponseEntity.ok(pcList);
	}
	
	@RequestMapping("/getTrendingProductDetails/{vendorId}/{type}")
	@GetMapping
	public ResponseEntity<List<List<ProductComboHdr>>> getTrendingProductDetails(@PathVariable(value = "vendorId", required = true) String vendorId,@PathVariable(value = "type", required = true) String type) {
		List<List<ProductComboHdr>> pcList = null;
		logger.info("getTrendingProductDetails started");
		try {
			pcList = productComboService.getTrendingProductDetails(vendorId,type);
		} catch (Exception e) {
			logger.error("getTrendingProductDetails error-->" + e);
		}

		return ResponseEntity.ok(pcList);
	}
	
	@RequestMapping("/getAllTrendingProductDetails/{vendorId}/{type}")
	@GetMapping
	public ResponseEntity<List<ProductComboHdr>> getAllTrendingProductDetails(@PathVariable(value = "vendorId", required = true) String vendorId,@PathVariable(value = "type", required = true) String type) {
		List<ProductComboHdr> pcList = null;
		logger.info("getAllTrendingProductDetails started");
		try {
			pcList = productComboService.getAllTrendingProductDetails(vendorId,type);
		} catch (Exception e) {
			logger.error("getAllTrendingProductDetails error-->" + e);
		}

		return ResponseEntity.ok(pcList);
	}
	
	//Admin services for combo
	@GetMapping("/getAllComboDetails/{vendorId}/{pageNo}/{size}/{pcType}")
	public ResponseEntity<Page<PcHdrJpaEntity>> getAllComboDetails(@PathVariable(value = "vendorId", required = true) String vendorId,@PathVariable(value = "pageNo", required = true) String pageNo,
			@PathVariable(value = "size", required = true) String size,@PathVariable(value = "pcType", required = true) String pcType) {
		Page<PcHdrJpaEntity> pcList = null;
		logger.info("getAllComboDetails started");
		try {
			Pageable paging = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(size));
			pcList = productComboService.getAllComboDetails(vendorId,pcType, paging);
		} catch (Exception e) {
			logger.error("getAllComboDetails error-->" + e);
		}

		return ResponseEntity.ok(pcList);
	}
	
	
	@PostMapping("/insertCombo")
	public ResponseEntity<String> insertCombo(@RequestBody PcHdrJpaEntity pcHdrJpaEntity) {
		String comboCode=productComboService.insertCombo(pcHdrJpaEntity);
		return new ResponseEntity<String>(comboCode, HttpStatus.CREATED);
	}
	@PutMapping("/updateCombo")
	public ResponseEntity<String> updateCombo(@RequestBody PcHdrJpaEntity pcHdrJpaEntity) {
		String comboCode=productComboService.updateCombo(pcHdrJpaEntity);
		return new ResponseEntity<String>(comboCode, HttpStatus.OK);
	}
	
}