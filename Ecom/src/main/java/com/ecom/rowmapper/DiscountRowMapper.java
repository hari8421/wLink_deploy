package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.DiscountRequest;

public class DiscountRowMapper implements RowMapper<DiscountRequest>{
	
	private static final Logger logger = LoggerFactory.getLogger(DiscountRowMapper.class);
	public DiscountRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		DiscountRequest u = new DiscountRequest();
		try {
			
			u.setIsActive(rs.getString("IS_ACTIVE"));
			u.setDiscountDesc(rs.getString("DIS_DESC"));
            u.setDiscountId(rs.getString("DC_ID"));
            u.setEffectiveStart(rs.getString("EFFECTIVE_START_DATE"));
            u.setEffectiveEnd(rs.getString("EFFECTIVE_END_DATE"));
            u.setPercentage(rs.getString("DIS_PERCENTAGE"));
            u.setTag(rs.getString("TAG"));
            u.setCreatedDatetime(rs.getString("CREATED_DATETIME"));
            u.setLastUpdatedDatetime(rs.getString("LAST_UPDATED_DATETIME"));
            

		} catch (Exception e) {
			logger.error("DiscountRowMapper error-->"+e);
		}
		
	      return u;
	   }

}
