package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.SingleProdDetails;

public class SingleProdDtlRowMapper implements RowMapper<SingleProdDetails> {

	private static final Logger logger = LoggerFactory.getLogger(SingleProdDtlRowMapper.class);

	public SingleProdDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		SingleProdDetails sp = new SingleProdDetails();
		try {
			sp.setBmDesc(rs.getString("BM_DESC"));
			sp.setDtlDesc(rs.getString("DTL_DESC"));
			sp.setPrice(rs.getString("PRICE"));
			sp.setPrimaryuom(rs.getString("PRIMARY_UOM"));
			sp.setProdCode(rs.getString("PROD_CODE"));
			sp.setProdDesc(rs.getString("PROD_DESC"));
			sp.setProdId(rs.getString("PROD_ID"));
			sp.setProdName(rs.getString("PROD_NAME"));
			sp.setStatusCode(rs.getString("STATUS"));
			sp.setStatusDesc(rs.getString("STATUS_DESC"));
			sp.setTags(rs.getString("TAGS"));
			sp.setUomDesc(rs.getString("UOM_DESC"));
			sp.setPsId(rs.getString("PS_ID"));
			sp.setComDesc(rs.getString("COM_DESC"));
			sp.setComLongDesc(rs.getString("COM_LONG_DESC"));
			sp.setDiscountPercent(rs.getString("DISCOUNT_PERCENTAGE"));
			sp.setDiscountRate(rs.getString("DISCOUNT_RATE"));
			sp.setOriginalRate(rs.getString("ORIGINAL_RATE"));
			sp.setActualRate(rs.getString("ACTUAL_RATE"));
			sp.setRating(rs.getString("RATING"));
		} catch (Exception e) {
			logger.error("SingleProdDetails error-->" + e);
		}

		return sp;
	}

}
