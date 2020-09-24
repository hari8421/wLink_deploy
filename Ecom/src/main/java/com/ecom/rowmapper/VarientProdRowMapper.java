package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.jpaEntity.VarientProd;


public class VarientProdRowMapper implements RowMapper<VarientProd> {

	private static final Logger logger = LoggerFactory.getLogger(VarientProdRowMapper.class);

	public VarientProd mapRow(ResultSet rs, int rowNum) throws SQLException {
		VarientProd vp = new VarientProd();
		try {

			vp.setDesc(rs.getString("VARIENT_TYPE_CODE"));
			vp.setTypeCode(rs.getString("VARIENT_TYPE_DESC"));

		} catch (Exception e) {
			logger.error("VarientProd row mapper exception error-->" + e);
		}

		return vp;
	}

}
