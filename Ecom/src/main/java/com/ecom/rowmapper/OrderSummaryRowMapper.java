package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.OrderInCartSummary;

public class OrderSummaryRowMapper implements RowMapper<OrderInCartSummary> {

	private static final Logger logger = LoggerFactory.getLogger(OrderSummaryRowMapper.class);

	public OrderInCartSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderInCartSummary u = new OrderInCartSummary();
		try {

			u.setComboPrice(rs.getString("COMBO_PRICE"));
			u.setDiscountPrice(rs.getString("DISCOUNTED_PRICE"));
			u.setOrderCode(rs.getString("ORDER_CODE"));
			u.setSubTotal(rs.getString("GROSS_PRICE"));
			u.setTotalPrice(rs.getString("GROSS_PRICE"));
			u.setCdeId(rs.getString("CDE_ID"));
			u.setoId(rs.getString("OI_ID"));

		} catch (Exception e) {
			logger.error("OrderInCartSummary error-->" + e);
		}

		return u;
	}

}
