package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.AdminIndProdVarId;

public class AdminProdVarMapper implements RowMapper<AdminIndProdVarId> {

	private static final Logger logger = LoggerFactory.getLogger(AdminProdVarMapper.class);

	public AdminIndProdVarId mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminIndProdVarId bh = new AdminIndProdVarId();
		try {

			bh.setPsId(rs.getString("PS_ID"));
			bh.setVarCode(rs.getString("VARIENT_TYPE_CODE"));
			bh.setVarDesc(rs.getString("VALUE"));
			bh.setVarId(rs.getString("PSD_ID"));

		} catch (Exception e) {
			logger.error("AdminIndProdVarId row mapper exception error-->" + e);
		}

		return bh;
	}

}
