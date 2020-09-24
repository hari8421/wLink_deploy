package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ReasonCategoryDtl;

public class ReasonCategoryDtlRowMapper implements RowMapper<ReasonCategoryDtl>{
	
	private static final Logger logger = LoggerFactory.getLogger(ReasonCategoryDtlRowMapper.class);
	public ReasonCategoryDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReasonCategoryDtl rc = new ReasonCategoryDtl();
		try {
			
			rc.setCategoryDesc(rs.getString("REASON_CATEGORY_DESC"));
			rc.setReasonCategoryCode(rs.getString("REASON_CATEGORY_CODE"));
		      
		} catch (Exception e) {
			logger.error("ReasonCategoryDtlRowMapper error-->"+e);
		}
		
	      return rc;
	   }

}
