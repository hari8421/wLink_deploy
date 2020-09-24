package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.UomRequest;

public class UomRowMapper implements RowMapper<UomRequest>{
	
	private static final Logger logger = LoggerFactory.getLogger(UomRowMapper.class);
	public UomRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		UomRequest u = new UomRequest();
		try {
			
			u.setIsActive(rs.getString("IS_ACTIVE"));
			u.setUomDesc(rs.getString("UOM_DESC"));
			u.setUomId(rs.getString("UOM_ID"));
		      
		} catch (Exception e) {
			logger.error("UomRowMapper error-->"+e);
		}
		
	      return u;
	   }

}
