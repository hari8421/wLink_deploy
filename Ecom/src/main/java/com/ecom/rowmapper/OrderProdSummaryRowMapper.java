package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.common.CommonFunctions;
import com.ecom.domain.OrderInCartProdSummary;

public class OrderProdSummaryRowMapper implements RowMapper<OrderInCartProdSummary> {

	private static final Logger logger = LoggerFactory.getLogger(OrderProdSummaryRowMapper.class);

	public OrderInCartProdSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderInCartProdSummary u = new OrderInCartProdSummary();
		try {

			u.setComboDesc(rs.getString("COM_DESC"));
			u.setProdCode(rs.getString("PROD_CODE"));
			u.setProdDesc(rs.getString("PROD_DESC"));
			u.setQty(rs.getString("QUANTITY"));
			u.setSubDesc(rs.getString("SUB_DESC"));
			u.setOrderDtlId(rs.getString("OID_DTL"));
			u.setPrice(rs.getString("PRICE"));
			if(rs.getString("ORDER_DATETIME")!=null) {
				u.setOrderDatetime(CommonFunctions.getDBDateToViewByMonthTwo(rs.getString("ORDER_DATETIME")));
			}else {
				u.setOrderDatetime("Not placed");
			}
			if(rs.getString("ACTUAL_DELIVERY_DATE")!=null) {
				u.setOrderdeliveryDate(CommonFunctions.getDBDateToViewByMonthTwo(rs.getString("ACTUAL_DELIVERY_DATE")));
			}else {
				u.setOrderdeliveryDate("Yet to be delivered");
			}
			
			u.setOrderStatus(rs.getString("STATUS_DESC"));
			u.setPsId(rs.getString("PS_ID"));
		} catch (Exception e) {
			logger.error("OrderInCartProdSummary error-->" + e);
		}

		return u;
	}

}
