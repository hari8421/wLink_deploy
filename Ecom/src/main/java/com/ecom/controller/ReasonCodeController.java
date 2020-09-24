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
import com.ecom.domain.ReasonCategoryDtl;
import com.ecom.domain.ReasonCodeRequest;
import com.ecom.service.ReasonCodeService;


@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReasonCodeController {
	private static final Logger logger = LoggerFactory.getLogger(ReasonCodeController.class);
	@Autowired ReasonCodeService reasonCodeService;
	
	@RequestMapping("/insertReasonCodeMaster")
	@PostMapping
	public ResponseEntity<String> insertReasonCodeMaster(@RequestBody ReasonCodeRequest reasonCodeRequest) {
		String responseMsg = "";
		try {
			responseMsg = reasonCodeService.insertReasonCodeMaster(reasonCodeRequest);
		} catch (Exception e) {
			logger.error("insertReasonCodeMaster error-->" + e);
		}

		return ResponseEntity.ok(responseMsg);
	}
	
	@RequestMapping("/getReasonCategory/{vendorId}")
	@GetMapping
	public ResponseEntity<List<ReasonCategoryDtl>> getReasonCategory(@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<ReasonCategoryDtl> rcList = null;
		try {
			rcList = reasonCodeService.getReasonCategory(vendorId);
		} catch (Exception e) {
			logger.error("getReasonCategory error-->" + e);
		}

		return ResponseEntity.ok(rcList);
	}
	
	
}