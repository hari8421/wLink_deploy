package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.AdminIndProdExtra;

public class AdminProdExtraRowMapper implements RowMapper<AdminIndProdExtra> {

	private static final Logger logger = LoggerFactory.getLogger(AdminProdExtraRowMapper.class);

	public AdminIndProdExtra mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminIndProdExtra bh = new AdminIndProdExtra();
		try {
			bh.setPeId(rs.getString("PE_ID"));
			bh.setProdKey(rs.getString("PROD_KEY"));
			bh.setProdTitle(rs.getString("PROD_TITLE"));
			bh.setProdValue(rs.getString("PROD_VALUE"));
			bh.setIsActive(rs.getString("IS_ACTIVE"));

		} catch (Exception e) {
			logger.error("AdminIndProdExtra row mapper exception error-->" + e);
		}

		return bh;
	}

}
