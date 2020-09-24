package com.ecom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.DiscountDAO;
import com.ecom.domain.DiscountRequest;


@Service
public class DiscountService {

	private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);
	@Autowired DiscountDAO discountDAO;

	public String insertDiscount(DiscountRequest discountRequest) {
		String retMsg="";
		try {
			
			retMsg=discountDAO.insertDiscount(discountRequest.getDiscountDesc(),discountRequest.getIsActive(),discountRequest.getVendorId(),discountRequest.getPercentage(),discountRequest.getCreatedDatetime(),discountRequest.getEffectiveStart(),discountRequest.getEffectiveEnd(),discountRequest.getTag(),discountRequest.getLastUpdatedDatetime());
		} catch (Exception e) {
			logger.error("insertDiscountService error-->"+e);
		}
		return retMsg;
	}
	
	public List<DiscountRequest> getDiscount(String vendorId) {
		List<DiscountRequest> uList=null;
		try {
			
			uList=discountDAO.getDiscount(vendorId);
		} catch (Exception e) {
			logger.error("getDiscount error-->"+e);
		}
		return uList;
	}

}
