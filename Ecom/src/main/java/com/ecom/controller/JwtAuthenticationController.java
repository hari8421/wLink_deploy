package com.ecom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.config.JwtTokenUtil;
import com.ecom.config.Messages;
import com.ecom.dao.UserDAO;
import com.ecom.dao.UserMstDAO;
import com.ecom.domain.JwtRequest;
import com.ecom.domain.JwtResponse;
import com.ecom.domain.UserDtls;
import com.ecom.error.CustomException;
import com.ecom.otp.OTPHandler;
import com.ecom.service.JwtUserDetailsService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired JdbcTemplate jdbc;
	
	@Autowired UserDAO ud;
	

	@Autowired
	private JwtUserDetailsService userDetailsService;
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid JwtRequest authenticationRequest) throws Exception {

		UserMstDAO um = new UserMstDAO();
		List<UserDtls> ul=new ArrayList<UserDtls>();
		JwtResponse lrObj=new JwtResponse();
		String retMsg="";
		//Check if a registerd user
		if(ud.findByMobileNumber(authenticationRequest.getUsername())==null) {
			throw new CustomException("Please register your mobile number");
		}else {
			//For OTP login
			if(authenticationRequest.getIsOtp().equalsIgnoreCase("true")) {
			    int otpVal=OTPHandler.latestOTPValidator(authenticationRequest.getUsername(),authenticationRequest.getOtp(),authenticationRequest.getVendorId(), jdbc);
			    if(otpVal==1) {
			    	retMsg=Messages.LoginSuccess;
			    	final UserDetails userDetails = userDetailsService
							.loadUserByMobile(authenticationRequest.getUsername());
			    	//retMsg=authenticate(userDetails.getUsername(), userDetails.getPassword());
					String token = jwtTokenUtil.generateToken(userDetails);
					ul=um.getUserData(authenticationRequest.getUsername(),authenticationRequest.getVendorId(), jdbc);
					lrObj.setJwttoken(token);
					lrObj.setUl(ul);
					lrObj.setReturnMsg(retMsg);
			    }else {
			    	throw new CustomException("Incorrect OTP");
			    	//lrObj.setReturnMsg("Incorrect OTP");
			    }
				
				
			}else {//For normal login using mobile number and password
				retMsg=authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
				lrObj.setReturnMsg(retMsg);
				if(!retMsg.equalsIgnoreCase(Messages.LoginFail)) {
					final UserDetails userDetails = userDetailsService
							.loadUserByMobile(authenticationRequest.getUsername());

					String token = jwtTokenUtil.generateToken(userDetails);
					ul=um.getUserData(authenticationRequest.getUsername(),authenticationRequest.getVendorId(), jdbc);
					lrObj.setJwttoken(token);
					lrObj.setUl(ul);
					
				}else {
					throw new CustomException(Messages.LoginFail);
				}
				
			}
		}
		
		

		return ResponseEntity.ok(lrObj);
	}
	
	

	@SuppressWarnings("finally")
	private String authenticate(String username, String password) throws Exception {
		String retMsg="";
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			retMsg=Messages.LoginFail;
			
			
		} catch (BadCredentialsException e) {
			retMsg=Messages.LoginFail;
			
		}finally {
			return retMsg;
		}
	}
	
	@CrossOrigin(maxAge = 3600)
	@RequestMapping(value = "/generateOTP/{vendorId}/{mobNo}")
	@GetMapping
	public ResponseEntity<String> genOTP(
			@PathVariable(value = "vendorId", required = true) String vendorId,
			@PathVariable(value = "mobNo", required = true) String mobNo) {
		String retMsg = "OTP sent successfully";
		if(ud.findByMobileNumber(mobNo)==null) {
			throw new CustomException("Please register your mobile number");
		}
		try {
			OTPHandler.OTPMessager(mobNo, vendorId, "login", jdbc);
		} catch (Exception e) {
			logger.error("getSingleProdDet controller error-->" + e);
		}

		return ResponseEntity.ok(retMsg);
	}
	
	
	

}