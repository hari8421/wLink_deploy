package com.ecom.service;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecom.dao.UserMstDAO;
import com.ecom.domain.SignUpRequest;
import com.ecom.jpaEntity.AdminJpaEntity;

@Service
public class UserService {

	@Autowired UserMstDAO ud;
	@Autowired JdbcTemplate jdbc;
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	public String registerUser(SignUpRequest signUpRequest) {
		String retMsg="";
		
		try {
			retMsg=ud.registerUser(signUpRequest.getPassword(),signUpRequest.getContactNo(),signUpRequest.getVendorId(),signUpRequest.getName());
			
	           
		} catch (Exception e) {
			logger.error("registerUser error-->"+e);
		}
		return retMsg;
	}
	public AdminJpaEntity insertAdmin(AdminJpaEntity adminJpaEntity) {
		
		return ud.insertAdmin(adminJpaEntity);
	}
	public AdminJpaEntity adminLogin(@Valid SignUpRequest signUpRequest) {
		return ud.adminLogin(signUpRequest);
	}
	
	
	

}
