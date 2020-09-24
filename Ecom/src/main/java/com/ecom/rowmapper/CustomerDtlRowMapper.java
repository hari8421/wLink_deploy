package com.ecom.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ecom.domain.CustomerMasterDtl;

public class CustomerDtlRowMapper implements RowMapper<CustomerMasterDtl> {

	private static final Logger logger = LoggerFactory.getLogger(CustomerDtlRowMapper.class);

	public CustomerMasterDtl mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerMasterDtl cm = new CustomerMasterDtl();
		try {

			cm.setAddresslineI(rs.getString("PRIMARY_ADDRESS_LINE_I"));
			cm.setAddresslineII(rs.getString("PRIMARY_ADDRESS_LINE_II"));
			cm.setCountry(rs.getString("COUNTRY"));
			cm.setCustomerID(rs.getString("CD_ID"));
			cm.setCustomerName(rs.getString("NAME"));
			cm.setDistrict(rs.getString("DISTRICT"));
			cm.setPhNo(rs.getString("PRIMARY_CONTACT_NO"));
			cm.setState(rs.getString("STATE"));
			cm.setRefId(rs.getString("CDE_ID"));
			cm.setIsPrimary(rs.getString("IS_PRIMARY"));

		} catch (Exception e) {
			logger.error("FileManager row mapper exception error-->" + e);
		}

		return cm;
	}

}
