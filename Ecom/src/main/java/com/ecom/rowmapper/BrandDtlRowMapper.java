package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.BrandDtl;


public class BrandDtlRowMapper implements RowMapper<BrandDtl> {

	private static final Logger logger = LoggerFactory.getLogger(BrandDtlRowMapper.class);

	public BrandDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		BrandDtl bd = new BrandDtl();
		try {

			bd.setBmCode(rs.getString("BM_CODE"));
			bd.setBmDesc(rs.getString("BM_DESC"));
            bd.setBmId(rs.getString("BM_ID"));
            bd.setIsOwnBrand(rs.getString("IS_OWN_BRAND"));
		} catch (Exception e) {
			logger.error("BrandDtlRowMapper row mapper exception error-->" + e);
		}

		return bd;
	}

}
