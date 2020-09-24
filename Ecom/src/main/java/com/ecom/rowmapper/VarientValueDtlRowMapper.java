package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.VarientValueDtl;

public class VarientValueDtlRowMapper implements RowMapper<VarientValueDtl> {

	private static final Logger logger = LoggerFactory.getLogger(VarientValueDtlRowMapper.class);

	public VarientValueDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		VarientValueDtl vl = new VarientValueDtl();
		try {

			vl.setVarientValue(rs.getString("VALUE"));

		} catch (Exception e) {
			logger.error("vendorPrefLocationDtl row mapper exception error-->" + e);
		}

		return vl;
	}

}
