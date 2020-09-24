package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProdExtnDtl;

public class ProdExtnRowMapper implements RowMapper<ProdExtnDtl> {

	private static final Logger logger = LoggerFactory.getLogger(ProdExtnRowMapper.class);

	public ProdExtnDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProdExtnDtl ph = new ProdExtnDtl();
		try {

			ph.setProdKey(rs.getString("PROD_KEY"));
			ph.setProdTitle(rs.getString("PROD_TITLE"));
			ph.setProdValue(rs.getString("PROD_VALUE"));

		} catch (Exception e) {
			logger.error("ProductComboDtlRowMapper error-->" + e);
		}

		return ph;
	}

}
