package com.ecom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.UomDAO;
import com.ecom.domain.UomRequest;


@Service
public class UomService {

	private static final Logger logger = LoggerFactory.getLogger(UomService.class);
	@Autowired UomDAO uomDAO;

	public String insertUom(UomRequest uomRequest) {
		String retMsg="";
		try {
			
			retMsg=uomDAO.insertUom(uomRequest.getUomDesc(),uomRequest.getIsActive(),uomRequest.getVendorId());
		} catch (Exception e) {
			logger.error("insertUomService error-->"+e);
		}
		return retMsg;
	}

	public List<UomRequest> getUom(String vendorId) {
		List<UomRequest> uList=null;
		try {
			
			uList=uomDAO.getUom(vendorId);
		} catch (Exception e) {
			logger.error("getUom error-->"+e);
		}
		return uList;
	}
	

}
