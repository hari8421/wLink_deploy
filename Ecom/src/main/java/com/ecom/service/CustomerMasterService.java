package com.ecom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.CustomerMasterDAO;
import com.ecom.domain.CustomerMasterDtl;

@Service
public class CustomerMasterService {

	@Autowired
	CustomerMasterDAO cm;

	private static final Logger logger = LoggerFactory.getLogger(CustomerMasterService.class);

	public List<CustomerMasterDtl> getCustomerDtl(String vendorId, String top) {
		List<CustomerMasterDtl> retList = null;
		
		try {
			if (top.equalsIgnoreCase("true")) {
				retList = cm.getAllTopCustomer(vendorId);
			} else {
				retList = cm.getAllCustomer(vendorId);
			}

		} catch (Exception e) {
			logger.error("getCustomerDtl error-->" + e);
		}
		return retList;
	}

}
