package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProdComHdr;

public class ProdComHdrRowMapper implements RowMapper<ProdComHdr> {

	private static final Logger logger = LoggerFactory.getLogger(ProdComHdrRowMapper.class);

	public ProdComHdr mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProdComHdr pr = new ProdComHdr();
		try {
			pr.setPchId(rs.getString("PCH_ID"));
		} catch (Exception e) {
			logger.error("ProdComHdr error-->" + e);
		}

		return pr;
	}

}
