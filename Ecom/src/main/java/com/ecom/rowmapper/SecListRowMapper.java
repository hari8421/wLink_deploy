package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.SecondCateResp;

public class SecListRowMapper implements RowMapper<SecondCateResp> {

	private static final Logger logger = LoggerFactory.getLogger(SecListRowMapper.class);

	public SecondCateResp mapRow(ResultSet rs, int rowNum) throws SQLException {
		SecondCateResp sc = new SecondCateResp();
		try {
			sc.setProdCatCode(rs.getString("PCM_CODE"));
			sc.setProdCatDesc(rs.getString("PCM_DESC"));
			sc.setDtlId(rs.getString("PARENT_CAT_DTL_ID"));
		} catch (Exception e) {
			logger.error("SecondCate error-->" + e);
		}

		return sc;
	}

}
