package com.ecom.controller;
import java.util.List;
import java.util.stream.Collectors;

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

import com.ecom.jpaEntity.VarientProd;
import com.ecom.repository.VarientJpaRepository;


@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VarientController {
	private static final Logger logger = LoggerFactory.getLogger(VarientController.class);
	@Autowired VarientJpaRepository varientJpaRepository;
	
	@RequestMapping("/insertVarient")
	@PostMapping
	public ResponseEntity<String> insertVarient(@RequestBody VarientProd varientProd) {
		String responseMsg = "";
		try {
			varientProd = varientJpaRepository.saveAndFlush(varientProd);
			responseMsg=varientProd.getDesc()+"";
		} catch (Exception e) {
			logger.error("insertVarient error-->" + e);
		}

		return ResponseEntity.ok(responseMsg);
    }
    
	@RequestMapping("/getAllVarient/{vendorId}")
	@GetMapping
	public ResponseEntity<List<VarientProd>> getAllVarient(@PathVariable(value = "vendorId", required = true) String vendorId) {
		List<VarientProd> rcList = null;
		try {
			rcList = varientJpaRepository.findAll().stream().filter(VarientProd->VarientProd.getVendorId().equals(vendorId)).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("getReasonCategory error-->" + e);
		}

		return ResponseEntity.ok(rcList);
	}
	

}