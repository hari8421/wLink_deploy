package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProductHdrDtl;

public class PSRowMapper implements RowMapper<ProductHdrDtl> {

	private static final Logger logger = LoggerFactory.getLogger(PSRowMapper.class);

	public ProductHdrDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductHdrDtl ph = new ProductHdrDtl();
		try {
			ph.setPsid(rs.getString("PS_ID"));
			ph.setDtlDesc(rs.getString("DTL_DESC"));

		} catch (Exception e) {
			logger.error("PSRowMapper error-->" + e);
		}

		return ph;
	}

}
