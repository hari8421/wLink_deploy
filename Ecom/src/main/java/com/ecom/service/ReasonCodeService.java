package com.ecom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.ReasonCodeDAO;
import com.ecom.domain.ReasonCategoryDtl;
import com.ecom.domain.ReasonCodeRequest;

@Service
public class ReasonCodeService {

	@Autowired ReasonCodeDAO reasonCodeDAO;
	private static final Logger logger = LoggerFactory.getLogger(UomService.class);
	public String insertReasonCodeMaster(ReasonCodeRequest reasonCodeRequest) {
		String retMsg="";
		try {
			retMsg= reasonCodeDAO.insertReasonCodeMaster(reasonCodeRequest.getReasonCategory(),reasonCodeRequest.getReasonDesc(),reasonCodeRequest.getVendorId());
		} catch (Exception e) {
			logger.error("ReasonCodeService error-->"+e);
		}
		return retMsg;
	}
	
	public List<ReasonCategoryDtl> getReasonCategory(String vendorId) {
		List<ReasonCategoryDtl> rcList=null;
		try {
			rcList=reasonCodeDAO.getReasonCategory(vendorId);
		} catch (Exception e) {
			logger.error("getReasonCategory service error-->"+e);
		}
		return rcList;
	}
}
