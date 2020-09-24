package com.ecom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ecom.domain.CustomerMasterDtl;
import com.ecom.rowmapper.CustomerDtlRowMapper;

@Repository
@Transactional
public class CustomerMasterDAO {

	private static final Logger logger = LoggerFactory.getLogger(CustomerMasterDAO.class);
	@Autowired
	JdbcTemplate jdbc;

	public List<CustomerMasterDtl> getAllCustomer(String vendorId) {
		List<CustomerMasterDtl> returnList = null;
		try {
			String getQ = "SELECT \r\n" + "    DELIVERY_ADDRESS_LINE_I AS PRIMARY_ADDRESS_LINE_I,\r\n"
					+ "    DELIVERY_ADDRESS_LINE_II AS PRIMARY_ADDRESS_LINE_II,\r\n" + "    cm.REF_DESC AS COUNTRY,\r\n"
					+ "    sm.REF_DESC AS STATE,\r\n" + "    cd.NAME AS NAME,\r\n" + "    pd.REF_DESC AS DISTRICT,\r\n"
					+ "    DELIVERY_CONTACT_NO AS PRIMARY_CONTACT_NO,\r\n" + "    cd.CD_ID,\r\n" + "    CDE_ID,\r\n"
					+ "    IS_PRIMARY\r\n" + "FROM\r\n" + "    customer_details cd\r\n" + "        LEFT JOIN\r\n"
					+ "    customer_details_extn extn ON cd.CD_ID = extn.CD_ID\r\n" + "        AND IS_PRIMARY = ?\r\n"
					+ "        LEFT JOIN\r\n" + "    predefined_country_mst cm ON extn.PDC_ID = cm.PDC_ID\r\n"
					+ "        LEFT JOIN\r\n" + "    predefined_district_mst pd ON extn.PDM_ID = pd.PDM_ID\r\n"
					+ "        LEFT JOIN\r\n" + "    predefined_state_mst sm ON sm.PDS_ID = extn.PDS_ID\r\n"
					+ "WHERE\r\n" + "    cd.VENDOR_ID = ?";
			RowMapper<CustomerMasterDtl> rowMapper = new CustomerDtlRowMapper();
			returnList = this.jdbc.query(getQ, rowMapper, 1, vendorId);

		} catch (Exception ex) {
			logger.error("getImageSrc method exception" + ex);
		}
		return returnList;
	}

	public List<CustomerMasterDtl> getAllTopCustomer(String vendorId) {
		List<CustomerMasterDtl> returnList = null;
		try {
			String getQ = "SELECT \r\n" + "    cm.NAME,\r\n" + "    cm.CD_ID,\r\n"
					+ "    cm.PRIMARY_ADDRESS_LINE_I,\r\n" + "    cm.PRIMARY_ADDRESS_LINE_II,\r\n"
					+ "    cm.PRIMARY_CONTACT_NO,\r\n" + "    pc.REF_DESC AS COUNTRY,\r\n"
					+ "    pd.REF_DESC AS DISTRICT,\r\n" + "    ps.REF_DESC AS STATE\r\n" + "FROM\r\n"
					+ "    customer_details cm,\r\n" + "    predefined_country_mst pc,\r\n"
					+ "    predefined_district_mst pd,\r\n" + "    predefined_state_mst ps\r\n" + "WHERE\r\n"
					+ "    ps.PDS_ID = cm.PDS_ID\r\n" + "        AND pc.PDC_ID = cm.PDC_ID\r\n"
					+ "        AND cm.PDM_ID = pd.PDM_ID\r\n" + "        and pc.PDC_ID = ps.PDC_ID\r\n"
					+ "        and pd.PDS_ID = ps.PDS_ID\r\n" + "        and cm.VENDOR_ID = ?";
			RowMapper<CustomerMasterDtl> rowMapper = new CustomerDtlRowMapper();
			returnList = this.jdbc.query(getQ, rowMapper, vendorId);

		} catch (Exception ex) {
			logger.error("getImageSrc method exception" + ex);
		}
		return returnList;
	}

}
