package com.ecom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.CustomerExtensionDAO;
import com.ecom.domain.CustomerExtensionRequest;
import com.ecom.domain.CustomerMasterDtl;
import com.ecom.domain.vendorPrefLocationDtl;

@Service
public class CustomerExtensionService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerExtensionService.class);
	@Autowired
	CustomerExtensionDAO customerExtensionDAO;

	public String insertCustomerExtn(CustomerExtensionRequest customerExtensionRequest) {
		String retMsg = "";
		try {

			retMsg = customerExtensionDAO.insertCustomerExtn(customerExtensionRequest);
		} catch (Exception e) {
			logger.error("insertCustomerExtensionService error-->" + e);
		}
		return retMsg;
	}

	public List<CustomerMasterDtl> getCustomerExtnDtl(String vendorId, String customerId) {
		return customerExtensionDAO.getCustomerExtnDtl(vendorId, customerId);
	}

	public List<vendorPrefLocationDtl> getVendorAddMap(String level, String stateId, String countryId,
			String vendorId) {
		return customerExtensionDAO.getVendorAddMap(level, stateId, countryId, vendorId);
	}

	public List<CustomerMasterDtl> getCartConfirmAdd(String vendorId, String customerId) {
		return customerExtensionDAO.getCartConfirmAdd(vendorId, customerId);
	}

	public String registerDeviceForVendor(String deviceId, String deviceName, String vendorId) {
		return customerExtensionDAO.registerDeviceForVendor(deviceId, deviceName, vendorId);
	}

}
