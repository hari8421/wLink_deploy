package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.AdminIndProdVar;

public class AdminProdSubRowMapper implements RowMapper<AdminIndProdVar> {

	private static final Logger logger = LoggerFactory.getLogger(AdminProdSubRowMapper.class);

	public AdminIndProdVar mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminIndProdVar bh = new AdminIndProdVar();
		try {
			bh.setDtlDesc(rs.getString("DTL_DESC"));
			bh.setPrice(rs.getString("PRICE"));
			bh.setPsId(rs.getString("PS_ID"));
			bh.setStatus(rs.getString("STATUS_DESC"));

		} catch (Exception e) {
			logger.error("AdminIndProdMst row mapper exception error-->" + e);
		}

		return bh;
	}

}
