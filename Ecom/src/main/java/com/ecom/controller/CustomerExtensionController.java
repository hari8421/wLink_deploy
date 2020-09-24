package com.ecom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.domain.CustomerExtensionRequest;
import com.ecom.domain.CustomerMasterDtl;
import com.ecom.domain.vendorPrefLocationDtl;
import com.ecom.requestEntity.customerDeviceDtl;
import com.ecom.requestEntity.customerOrderCombo;
import com.ecom.requestEntity.vendorDelReq;
import com.ecom.service.CustomerExtensionService;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerExtensionController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerExtensionController.class);
	@Autowired
	CustomerExtensionService customerExtensionService;

	@RequestMapping("/insertCustomerExtn")
	@PostMapping
	public ResponseEntity<String> insertCustomerExtn(@RequestBody CustomerExtensionRequest customerExtensionRequest) {
		String responseMsg = "";
		try {
			responseMsg = customerExtensionService.insertCustomerExtn(customerExtensionRequest);
		} catch (final Exception e) {
			logger.error("insertCustomerExtension error-->" + e);
		}

		return ResponseEntity.ok(responseMsg);
	}

	@RequestMapping("/getCustomerAddrDtl")
	@PostMapping
	public ResponseEntity<List<CustomerMasterDtl>> getCustomerExtnDtl(@RequestBody customerOrderCombo orderDetails) {
		return new ResponseEntity<List<CustomerMasterDtl>>(
				customerExtensionService.getCustomerExtnDtl(orderDetails.getVendorId(), orderDetails.getCustomerId()),
				HttpStatus.OK);
	}

	@RequestMapping("/getVendorMapp")
	@PostMapping
	public ResponseEntity<List<vendorPrefLocationDtl>> getVendorAddMap(@RequestBody vendorDelReq vendorDelReq) {
		return new ResponseEntity<List<vendorPrefLocationDtl>>(
				customerExtensionService.getVendorAddMap(vendorDelReq.getLevel(), vendorDelReq.getStateId(),
						vendorDelReq.getCountryId(), vendorDelReq.getVendorId()),
				HttpStatus.OK);
	}

	@RequestMapping("/getCartConfirmAdd")
	@PostMapping
	public ResponseEntity<List<CustomerMasterDtl>> getCartConfirmAdd(@RequestBody customerOrderCombo orderDetails) {
		return new ResponseEntity<List<CustomerMasterDtl>>(
				customerExtensionService.getCartConfirmAdd(orderDetails.getVendorId(), orderDetails.getCustomerId()),
				HttpStatus.OK);
	}

	@RequestMapping("/registerDeviceForVendor")
	@PostMapping
	public ResponseEntity<String> registerDeviceForVendor(@RequestBody customerDeviceDtl deviceDtl) {
		return new ResponseEntity<String>(customerExtensionService.registerDeviceForVendor(
				deviceDtl.getDeviceId(), deviceDtl.getDeviceName(), deviceDtl.getVendorId()), HttpStatus.OK);
	}

}