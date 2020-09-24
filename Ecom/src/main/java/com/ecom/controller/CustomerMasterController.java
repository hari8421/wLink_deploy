package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.domain.CustomerMasterDtl;
import com.ecom.requestEntity.customerOrderCombo;
import com.ecom.service.CustomerMasterService;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerMasterController {
	@Autowired
	CustomerMasterService CMService;
	@CrossOrigin(maxAge = 3600)
	@RequestMapping(value = "/GetCustomerDtl")
	@PostMapping
	public List<CustomerMasterDtl> getCustomerDtl(@RequestBody customerOrderCombo cust) {

		return CMService.getCustomerDtl(cust.getVendorId(), "false");
	}

}