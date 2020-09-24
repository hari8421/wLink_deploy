package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.WishListProdSummary;

public class WishListProdRowMapper implements RowMapper<WishListProdSummary> {

	private static final Logger logger = LoggerFactory.getLogger(WishListProdRowMapper.class);

	public WishListProdSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
		WishListProdSummary u = new WishListProdSummary();
		try {

			u.setComboDesc(rs.getString("COM_DESC"));
			u.setProdCode(rs.getString("PROD_CODE"));
			u.setProdDesc(rs.getString("PROD_DESC"));
			u.setSubDesc(rs.getString("SUB_DESC"));
			u.setPrice(rs.getString("PRICE"));
			u.setProdId(rs.getString("PROD_ID"));
			u.setCwid(rs.getString("CW_ID"));
			u.setPsId(rs.getString("PS_ID"));
		} catch (Exception e) {
			logger.error("WishListProdSummary error-->" + e);
		}

		return u;
	}

}
