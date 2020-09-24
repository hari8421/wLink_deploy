package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.OrderInfoDtl;

public class OrderInfoDtlRowMapper implements RowMapper<OrderInfoDtl> {

	private static final Logger logger = LoggerFactory.getLogger(OrderInfoDtlRowMapper.class);

	public OrderInfoDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderInfoDtl oD = new OrderInfoDtl();
		try {

			oD.setOid(rs.getString("OI_ID"));
			oD.setOidDtlId(rs.getString("OID_DTL"));
			oD.setPchId(rs.getString("PCH_ID"));
			oD.setPrice(rs.getString("PRICE"));
			oD.setProdCode(rs.getString("PROD_CODE"));
			oD.setProdDesc(rs.getString("PROD_DESC"));
			oD.setQty(rs.getString("QUANTITY"));
			oD.setSubDesc(rs.getString("SUB_DESC"));
		} catch (Exception e) {
			logger.error("OrderInfoDtlRowMapper error-->" + e);
		}

		return oD;
	}

}
