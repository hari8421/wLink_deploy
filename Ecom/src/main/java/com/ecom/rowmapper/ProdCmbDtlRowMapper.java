package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.ProdSingleCmbDtl;

public class ProdCmbDtlRowMapper implements RowMapper<ProdSingleCmbDtl> {

	private static final Logger logger = LoggerFactory.getLogger(ProdCmbDtlRowMapper.class);

	public ProdSingleCmbDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProdSingleCmbDtl ps = new ProdSingleCmbDtl();
		try {
			ps.setActRate(rs.getString("ACTUAL_RATE"));
			ps.setCmbDesc(rs.getString("COM_DESC"));
			ps.setDiscPerc(rs.getString("DISCOUNT_PERCENTAGE"));
			ps.setDiscRate(rs.getString("DISCOUNT_RATE"));
			ps.setDtlDesc(rs.getString("DTL_DESC"));
			ps.setOriginalRate(rs.getString("ORIGINAL_RATE"));
			ps.setPchid(rs.getString("PCH_ID"));

		} catch (Exception e) {
			logger.error("ProdSingleCmbDtl error-->" + e);
		}

		return ps;
	}

}
