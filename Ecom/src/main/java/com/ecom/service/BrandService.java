package com.ecom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.BrandDAO;
import com.ecom.domain.BrandDtl;


@Service
public class BrandService {

	private static final Logger logger = LoggerFactory.getLogger(BrandService.class);
	@Autowired BrandDAO brandDAO;

	public String insertBrand(BrandDtl brandDtl) {
		String retMsg="";
		try {
			
            retMsg=brandDAO.insertBrand(brandDtl.getBmId(), brandDtl.getBmCode(),brandDtl.getBmDesc(),brandDtl.getIsOwnBrand(),brandDtl.getIsActive(),brandDtl.getVendorId());
		} catch (Exception e) {
			logger.error("insertBrandService error-->"+e);
		}
		return retMsg;
	}

	public List<BrandDtl> getBrand(String vendorId) {
		List<BrandDtl> uList=null;
		try {
			
			uList=brandDAO.getBrand(vendorId);
		} catch (Exception e) {
			logger.error("getBrand error-->"+e);
		}
		return uList;
	}
	

}
