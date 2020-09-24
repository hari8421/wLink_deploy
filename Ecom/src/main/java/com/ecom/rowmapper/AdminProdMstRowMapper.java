package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.AdminIndProdMst;

public class AdminProdMstRowMapper implements RowMapper<AdminIndProdMst> {

	private static final Logger logger = LoggerFactory.getLogger(AdminProdMstRowMapper.class);

	public AdminIndProdMst mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminIndProdMst bh = new AdminIndProdMst();
		try {
			bh.setProdId(rs.getString("PROD_ID"));
			bh.setCategoryId(rs.getString("PARENT_CATE_DTL_ID"));
			bh.setIsActive(rs.getString("IS_ACTIVE"));
			bh.setProdCode(rs.getString("PROD_CODE"));
			bh.setProdDesc(rs.getString("PROD_DESC"));
			bh.setProdname(rs.getString("PROD_NAME"));
			bh.setBrand(rs.getString("BM_ID"));
			bh.setTags(rs.getString("TAGS"));

		} catch (Exception e) {
			logger.error("AdminIndProdMst row mapper exception error-->" + e);
		}

		return bh;
	}

}
