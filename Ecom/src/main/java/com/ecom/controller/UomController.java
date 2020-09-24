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

import com.ecom.domain.UomRequest;
import com.ecom.service.UomService;


@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UomController {
	private static final Logger logger = LoggerFactory.getLogger(UomController.class);
	@Autowired UomService uomService;
	
	@RequestMapping("/insertUom")
	@PostMapping
	public ResponseEntity<String> insertUom(@RequestBody UomRequest uomRequest) {
		String responseMsg = "";
		try {
			responseMsg = uomService.insertUom(uomRequest);
		} catch (Exception e) {
			logger.error("insertUom error-->" + e);
		}

		return ResponseEntity.ok(responseMsg);
	}
	
	@RequestMapping("/getUom/{vendorId}")
	@GetMapping
	public ResponseEntity<List<UomRequest>> getUom(@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<UomRequest> uList = null;
		try {
			uList = uomService.getUom(vendorId);
		} catch (Exception e) {
			logger.error("getReasonCategory error-->" + e);
		}

		return ResponseEntity.ok(uList);
	}
}