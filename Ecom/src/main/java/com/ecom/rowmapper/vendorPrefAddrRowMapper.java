package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.vendorPrefLocationDtl;


public class vendorPrefAddrRowMapper implements RowMapper<vendorPrefLocationDtl> {

	private static final Logger logger = LoggerFactory.getLogger(vendorPrefAddrRowMapper.class);

	public vendorPrefLocationDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		vendorPrefLocationDtl vl = new vendorPrefLocationDtl();
		try {

			vl.setRefCode(rs.getString("REF_CODE"));
			vl.setRefDesc(rs.getString("REF_DESC"));
			vl.setRefId(rs.getString("REF_ID"));

		} catch (Exception e) {
			logger.error("vendorPrefLocationDtl row mapper exception error-->" + e);
		}

		return vl;
	}

}
