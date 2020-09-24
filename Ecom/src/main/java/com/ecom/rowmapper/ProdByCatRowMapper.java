package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProdByProdCat;

public class ProdByCatRowMapper implements RowMapper<ProdByProdCat> {

	private static final Logger logger = LoggerFactory.getLogger(ProdByCatRowMapper.class);

	public ProdByProdCat mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProdByProdCat pr = new ProdByProdCat();
		try {
			pr.setPrimaryDesc(rs.getString("PrimartDesc"));
			pr.setProdCode(rs.getString("PROD_CODE"));
			pr.setProdDesc(rs.getString("PROD_DESC"));
			pr.setProdId(rs.getString("PROD_ID"));
			pr.setProdName(rs.getString("PROD_NAME"));
			pr.setSecDesc(rs.getString("SecDesc"));
			pr.setTags(rs.getString("TAGS"));

		} catch (Exception e) {
			logger.error("prodByProdCat row mapper exception error-->" + e);
		}

		return pr;
	}

}
