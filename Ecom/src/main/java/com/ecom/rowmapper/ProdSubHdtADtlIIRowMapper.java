package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProductSubHdrADtlII;

public class ProdSubHdtADtlIIRowMapper implements RowMapper<ProductSubHdrADtlII> {

	private static final Logger logger = LoggerFactory.getLogger(ProdSubHdtADtlIIRowMapper.class);

	public ProductSubHdrADtlII mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductSubHdrADtlII u = new ProductSubHdrADtlII();
		try {

			u.setVarientCode(rs.getString("VARIENT_TYPE_CODE"));
			u.setVarientType(rs.getString("VARIENT_TYPE_DESC"));
		} catch (Exception e) {
			logger.error("ProductSubHdrADtl error-->" + e);
		}

		return u;
	}

}
