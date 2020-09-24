package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProductSubHdrADtl;

public class ProdSubHdtADtlRowMapper implements RowMapper<ProductSubHdrADtl> {

	private static final Logger logger = LoggerFactory.getLogger(ProdSubHdtADtlRowMapper.class);

	public ProductSubHdrADtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductSubHdrADtl u = new ProductSubHdrADtl();
		try {

			u.setDtlDesc(rs.getString("DTL_DESC"));
			u.setPrice(rs.getString("PRICE"));
			u.setProdId(rs.getString("PROD_ID"));
			u.setStatusDesp(rs.getString("STATUS_DESC"));
			u.setPsId(rs.getString("PS_ID"));
			u.setProdName(rs.getString("PROD_NAME"));
			u.setProdCode(rs.getString("PROD_CODE"));
			u.setProdDesc(rs.getString("PROD_DESC"));
		} catch (Exception e) {
			logger.error("ProductSubHdrADtl error-->" + e);
		}

		return u;
	}

}
