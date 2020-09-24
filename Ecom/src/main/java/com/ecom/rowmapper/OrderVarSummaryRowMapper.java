package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.OrderInCartVarSummary;

public class OrderVarSummaryRowMapper implements RowMapper<OrderInCartVarSummary> {

	private static final Logger logger = LoggerFactory.getLogger(OrderVarSummaryRowMapper.class);

	public OrderInCartVarSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderInCartVarSummary u = new OrderInCartVarSummary();
		try {

			u.setType(rs.getString("VARIENT_TYPE_DESC"));
			u.setValue(rs.getString("VALUE"));

		} catch (Exception e) {
			logger.error("OrderInCartVarSummary error-->" + e);
		}

		return u;
	}

}
