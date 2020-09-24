package com.ecom.controller;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.config.Messages;
import com.ecom.domain.SignUpRequest;
import com.ecom.error.CustomException;
import com.ecom.jpaEntity.AdminJpaEntity;
import com.ecom.service.UserService;


@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService uService;
	@RequestMapping(value="/registerUser",consumes = "application/json")
	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody @Valid SignUpRequest SignUpRequest, Errors errors) {
		String responseMsg = "";
		 HttpHeaders headers = new HttpHeaders();
		   
		try {
			
			 responseMsg = uService.registerUser(SignUpRequest);}
		 catch (Exception e) {
			logger.error("registerUser error-->" + e);
		}
			 if(responseMsg.equalsIgnoreCase(Messages.dupUser)) {
				 throw new CustomException(Messages.dupUser);
	        }else {
	        	 headers.add("Success","1");
				 headers.add("message",responseMsg);
	        }
		

		return new ResponseEntity<>(responseMsg,headers, HttpStatus.OK);
	}
	
	@RequestMapping("/insertCustomer")
	@PostMapping
	public ResponseEntity<?> insertCustomer(@RequestBody SignUpRequest SignUpRequest) {
		String responseMsg = "";
		try {
			responseMsg = uService.registerUser(SignUpRequest);
		} catch (Exception e) {
			logger.error("registerUser error-->" + e);
		}

		return ResponseEntity.ok(responseMsg);
	}
	
	@RequestMapping(value = "/insertAdmin")
	@PostMapping
	public ResponseEntity<AdminJpaEntity> insertAdmin(@RequestBody AdminJpaEntity adminJpaEntity) {
		
		AdminJpaEntity adEntity= uService.insertAdmin(adminJpaEntity);
		return new ResponseEntity<AdminJpaEntity>(
				adEntity,
				HttpStatus.OK);

	}
	
	@RequestMapping(value = "/updateAdmin")
	@PutMapping
	public ResponseEntity<AdminJpaEntity> updateAdmin(@RequestBody AdminJpaEntity adminJpaEntity) {
		
		AdminJpaEntity adEntity= uService.insertAdmin(adminJpaEntity);
		return new ResponseEntity<AdminJpaEntity>(
				adEntity,
				HttpStatus.OK);

	}
	
	@RequestMapping(value = "/adminLogin")
	@PostMapping
	public ResponseEntity<AdminJpaEntity> adminLogin(@RequestBody @Valid SignUpRequest SignUpRequest) {
		logger.info("admin login");
		AdminJpaEntity adEntity= uService.adminLogin(SignUpRequest);
		return new ResponseEntity<AdminJpaEntity>(
				adEntity,
				HttpStatus.OK);

	}
}