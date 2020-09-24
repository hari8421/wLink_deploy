package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import com.ecom.domain.VarientDtlForGrid;


public class VarientDtlForGridRowMapper implements RowMapper<VarientDtlForGrid> {

	private static final Logger logger = LoggerFactory.getLogger(VarientDtlForGridRowMapper.class);

	public VarientDtlForGrid mapRow(ResultSet rs, int rowNum) throws SQLException {
		VarientDtlForGrid vl = new VarientDtlForGrid();
		try {

			vl.setVarientTypeCode(rs.getString("VARIENT_TYPE_CODE"));
			vl.setVarientTypeDesc(rs.getString("VARIENT_TYPE_DESC"));

		} catch (Exception e) {
			logger.error("vendorPrefLocationDtl row mapper exception error-->" + e);
		}

		return vl;
	}

}
