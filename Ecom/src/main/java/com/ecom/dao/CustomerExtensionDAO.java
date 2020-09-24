package com.ecom.dao;

import java.sql.PreparedStatement;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ecom.common.CommonFunctions;
import com.ecom.config.Messages;
import com.ecom.domain.CustomerExtensionRequest;
import com.ecom.domain.CustomerMasterDtl;
import com.ecom.domain.vendorPrefLocationDtl;
import com.ecom.rowmapper.CustomerDtlRowMapper;
import com.ecom.rowmapper.vendorPrefAddrRowMapper;

@Repository
@Transactional
public class CustomerExtensionDAO {

	private static final Logger logger = LoggerFactory.getLogger(CustomerExtensionDAO.class);
	@Autowired
	JdbcTemplate jdbc;

	public String insertCustomerExtn(CustomerExtensionRequest customerExtensionRequest) {
		String retMsg = "";
		try {

			String isPrimary = "0";
			if (customerExtensionRequest.getPrimary().equalsIgnoreCase("true")) {
				String updQ = "update customer_details_extn set IS_PRIMARY =? where CD_ID=? and VENDOR_ID=?";
				this.jdbc.update(updQ, 0, customerExtensionRequest.getCdId(), customerExtensionRequest.getVendorId());
			}

			String insertCustomerExtnQry = "INSERT INTO `customer_details_extn` (`CD_ID`, `DELIVERY_CONTACT_NAME`, `DELIVERY_CONTACT_NO`, `DELIVERY_ADDRESS_LINE_I`, `DELIVERY_ADDRESS_LINE_II`, `PDC_ID`, `PDS_ID`, `PDM_ID`, `PINCODE`, `CREATED_DATETIME`, `LAST_UPDATED_DATETIME`,`IS_PRIMARY`,`VENDOR_ID` ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			int insUc = this.jdbc.update(insertCustomerExtnQry, new Object[] { customerExtensionRequest.getCdId(),
					customerExtensionRequest.getDeliveryContactName(), customerExtensionRequest.getDeliveryContactNo(),
					customerExtensionRequest.getDeliveryAddressLine1(),
					customerExtensionRequest.getDeliveryAddressLine2(), customerExtensionRequest.getPdcId(),
					customerExtensionRequest.getPdsId(), customerExtensionRequest.getPdmId(),
					customerExtensionRequest.getPincode(), CommonFunctions.getCurrentDateTime(),
					CommonFunctions.getCurrentDateTime(), isPrimary, customerExtensionRequest.getVendorId() });
			if (insUc > 0) {
				retMsg = Messages.CustomerExtensionCreated;
			}

		} catch (Exception e) {
			logger.error("insertCustomerExtnDAO error-->" + e);
		}
		return retMsg;

	}

	public List<CustomerMasterDtl> getCustomerExtnDtl(String vendorId, String customerId) {
		List<CustomerMasterDtl> customerExtnDtl = null;
		try {
			String getQ = "SELECT \r\n" + "    DELIVERY_ADDRESS_LINE_I AS PRIMARY_ADDRESS_LINE_I,\r\n"
					+ "    DELIVERY_ADDRESS_LINE_II AS PRIMARY_ADDRESS_LINE_II,\r\n" + "    cm.REF_DESC AS COUNTRY,\r\n"
					+ "    sm.REF_DESC AS STATE,\r\n" + "    DELIVERY_CONTACT_NAME AS NAME,\r\n"
					+ "    pd.REF_DESC AS DISTRICT,\r\n" + "    DELIVERY_CONTACT_NO AS PRIMARY_CONTACT_NO,\r\n"
					+ "    CD_ID,\r\n" + "    CDE_ID\r\n , IS_PRIMARY " + "FROM\r\n"
					+ "    customer_details_extn extn,\r\n" + "    predefined_country_mst cm,\r\n"
					+ "    predefined_district_mst pd,\r\n" + "    predefined_state_mst sm\r\n" + "WHERE\r\n"
					+ "    sm.PDS_ID = extn.PDS_ID\r\n" + "        AND extn.PDC_ID = cm.PDC_ID\r\n"
					+ "        AND extn.PDM_ID = pd.PDM_ID\r\n" + "        AND CD_ID = ?\r\n"
					+ "        AND VENDOR_ID = ?";
			RowMapper<CustomerMasterDtl> rowMapper = new CustomerDtlRowMapper();
			customerExtnDtl = this.jdbc.query(getQ, rowMapper, customerId, vendorId);

		} catch (Exception e) {
			logger.error("getCustomerExtnDtl error-->" + e);
		}
		return customerExtnDtl;
	}

	public List<vendorPrefLocationDtl> getVendorAddMap(String level, String stateId, String countryId,
			String vendorId) {
		List<vendorPrefLocationDtl> vendorPrefAdd = null;

		try {

			if (level.equalsIgnoreCase("1")) {
				String getQ = "SELECT DISTINCT\r\n"
						+ "    pcm.REF_CODE AS REF_CODE, pcm.REF_DESC as REF_DESC,  lm.PDC_ID as REF_ID\r\n"
						+ "FROM\r\n" + "    vendor_delivery_location_map lm,\r\n" + "    predefined_country_mst pcm\r\n"
						+ "WHERE\r\n" + "    pcm.PDC_ID = lm.PDC_ID AND VENDOR_ID = ?";
				RowMapper<vendorPrefLocationDtl> rowMapper = new vendorPrefAddrRowMapper();
				vendorPrefAdd = this.jdbc.query(getQ, rowMapper, vendorId);
			} else if (level.equalsIgnoreCase("2")) {
				String getQ = "SELECT DISTINCT\r\n" + "    ps.REF_DESC, ps.REF_CODE, ps.PDS_ID AS REF_ID\r\n"
						+ "FROM\r\n" + "    vendor_delivery_location_map lm,\r\n" + "    predefined_state_mst ps\r\n"
						+ "WHERE\r\n" + "    ps.PDS_ID = lm.PDS_ID\r\n" + "        AND lm.VENDOR_ID = ?\r\n"
						+ "        AND lm.PDC_ID = ?";
				RowMapper<vendorPrefLocationDtl> rowMapper = new vendorPrefAddrRowMapper();
				vendorPrefAdd = this.jdbc.query(getQ, rowMapper, vendorId, countryId);
			} else if (level.equalsIgnoreCase("3")) {
				String getQ = "SELECT \r\n" + "    pd.REF_CODE, pd.REF_DESC, lm.PDS_ID AS REF_ID\r\n" + "FROM\r\n"
						+ "    vendor_delivery_location_map lm,\r\n" + "    predefined_district_mst pd\r\n"
						+ "WHERE\r\n" + "    pd.PDM_ID = lm.PDM_ID\r\n" + "        AND lm.VENDOR_ID = ?\r\n"
						+ "        AND lm.PDS_ID = ?;";
				RowMapper<vendorPrefLocationDtl> rowMapper = new vendorPrefAddrRowMapper();
				vendorPrefAdd = this.jdbc.query(getQ, rowMapper, vendorId, stateId);
			}

		} catch (Exception e) {
			logger.error("getVendorAddMap error-->" + e);
		}
		return vendorPrefAdd;
	}

	public List<CustomerMasterDtl> getCartConfirmAdd(String vendorId, String customerId) {
		List<CustomerMasterDtl> customerExtnDtl = null;
		try {
			String getQ = "SELECT \r\n" + "    cm.DELIVERY_ADDRESS_LINE_I as PRIMARY_ADDRESS_LINE_I,\r\n"
					+ "    cm.DELIVERY_ADDRESS_LINE_II as PRIMARY_ADDRESS_LINE_II,\r\n"
					+ "    cm.DELIVERY_CONTACT_NAME as NAME,\r\n"
					+ "    cm.DELIVERY_CONTACT_NO as PRIMARY_CONTACT_NO,\r\n" + "    cm.CD_ID as CD_ID,\r\n"
					+ "    pc.REF_DESC as COUNTRY,\r\n" + "    pd.REF_DESC as DISTRICT,\r\n"
					+ "    ps.REF_DESC as STATE,\r\n" + "    cm.IS_PRIMARY,\r\n" + "    cm.CDE_ID\r\n" + "FROM\r\n"
					+ "    order_information_hdr hdr,\r\n" + "    predefined_country_mst pc,\r\n"
					+ "    predefined_district_mst pd,\r\n" + "    predefined_state_mst ps,\r\n"
					+ "    customer_details_extn cm\r\n" + "WHERE\r\n" + "    cm.CDE_ID = hdr.CDE_ID\r\n"
					+ "        AND pc.PDC_ID = cm.PDC_ID = pc.PDC_ID\r\n" + "        AND cm.PDM_ID = pd.PDM_ID\r\n"
					+ "        AND ps.PDS_ID = cm.PDS_ID\r\n" + "        AND hdr.VENDOR_ID = ?\r\n"
					+ "        AND hdr.CD_ID = ?\r\n" + "        AND ORDER_STATUS = ?";
			RowMapper<CustomerMasterDtl> rowMapper = new CustomerDtlRowMapper();
			customerExtnDtl = this.jdbc.query(getQ, rowMapper, vendorId, customerId, "ST001");

		} catch (Exception e) {
			logger.error("getCartConfirmAdd error-->" + e);
		}
		return customerExtnDtl;
	}

	public String registerDeviceForVendor(String deviceId, String deviceName, String vendorId) {
		String returnMsg = "";
		try {

			String insertQ = "INSERT INTO `vendor_device_dtl_log` ( `VENDOR_ID`, `CREATED_DATETIME`) VALUES ( ?, ?)";
			
			KeyHolder idHolder = new GeneratedKeyHolder();
       	 jdbc.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(insertQ,new String[]{"ID"});
                        ps.setString(1, vendorId);
                        ps.setString(2, CommonFunctions.getCurrentDateTime());
                        return ps;
                    }, idHolder);

            int uiD = idHolder.getKey().intValue();
            returnMsg=uiD+"";
		} catch (Exception e) {
			logger.error("getCartConfirmAdd error-->" + e);
		}
		return returnMsg;
	}

}