package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.UserDtls;

public class UserDtlsRowMapper implements RowMapper<UserDtls>{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDtlsRowMapper.class);
	public UserDtls mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDtls ud = new UserDtls();
		try {
			
			ud.setCdId(rs.getString("CD_ID"));
			ud.setName(rs.getString("NAME"));
			//ud.setPrimaryAddress1(rs.getString("PRIMARY_ADDRESS_LINE_I"));
			//ud.setPrimaryAddress2(rs.getString("PRIMARY_ADDRESS_LINE_II"));
			//ud.setPrimaryContact(rs.getString("PRIMARY_CONTACT_NO"));
			ud.setClId(rs.getString("CL_ID"));
		      
		} catch (Exception e) {
			logger.error("UserDtlsRowMapper error-->"+e);
		}
		
	      return ud;
	   }

}
