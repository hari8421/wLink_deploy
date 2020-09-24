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

import com.ecom.domain.DiscountRequest;
import com.ecom.service.DiscountService;


@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DiscountController {
	private static final Logger logger = LoggerFactory.getLogger(DiscountController.class);
	@Autowired DiscountService discountService;
	
	@RequestMapping("/insertDiscount")
	@PostMapping
	public ResponseEntity<String> insertDiscount(@RequestBody final DiscountRequest discountRequest) {
		String responseMsg = "";
		try {
			responseMsg = discountService.insertDiscount(discountRequest);
		} catch (final Exception e) {
			logger.error("insertDiscount error-->" + e);
		}

		return ResponseEntity.ok(responseMsg);
	}

	@RequestMapping("/getDiscount/{vendorId}")
	@GetMapping
	public ResponseEntity<List<DiscountRequest>> getDiscount(@PathVariable(value = "vendorId", required = true) final String vendorId) {
		List<DiscountRequest> uList = null;
		try {
			uList = discountService.getDiscount(vendorId);
		} catch (final Exception e) {
			logger.error("getDiscountCategory error-->" + e);
		}

		return ResponseEntity.ok(uList);
	}
	
	
}