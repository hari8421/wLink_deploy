package com.ecom.controller;
import java.util.List;

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

import com.ecom.domain.BrandDtl;
import com.ecom.service.BrandService;


@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BrandController {
	private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
	@Autowired BrandService brandService;
	
	@RequestMapping("/insertBrand")
	@PostMapping
	public ResponseEntity<String> insertBrand(@RequestBody BrandDtl brandDtl) {
		String responseMsg = "";
		try {
			responseMsg = brandService.insertBrand(brandDtl);
		} catch (Exception e) {
			logger.error("insertBrand error-->" + e);
		}

		return ResponseEntity.ok(responseMsg);
    }
    
    @RequestMapping("/getBrand/{vendorId}")
	@GetMapping
	public ResponseEntity<List<BrandDtl>> getBrand(
			@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<BrandDtl> returnList = null;
		try {
			returnList = brandService.getBrand(vendorId);
		} catch (Exception e) {
			logger.error("getBrand error-->" + e);
		}

		return ResponseEntity.ok(returnList);
	}
	

}