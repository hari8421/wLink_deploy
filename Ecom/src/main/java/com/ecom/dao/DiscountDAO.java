package com.ecom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ecom.config.Messages;
import com.ecom.domain.DiscountRequest;
import com.ecom.rowmapper.DiscountRowMapper;


@Repository
@Transactional
public class DiscountDAO {

	private static final Logger logger = LoggerFactory.getLogger(DiscountDAO.class);
	@Autowired JdbcTemplate jdbc;
	public String insertDiscount(String desc, String isActive, String vendorId, String percentage, String lastUpdatedDatetime, String effectiveStart, String effectiveEnd, String createdDatetime, String tag) {
		String retMsg="";
		try {
			String insertDiscountQry="INSERT INTO `discount_coupon_details` (`DIS_DESC`, `IS_ACTIVE`, `VENDOR_ID`, `TAG`, `EFFECTIVE_START_DATE`, `EFFECTIVE_END_DATE`, `DIS_PERCENTAGE`, `CREATED_DATETIME`, `LAST_UPDATED_DATETIME` ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String discountDupCheck="select count(*) from discount_coupon_details where  DIS_DESC=?";
			
				int catCount=this.jdbc.queryForObject(discountDupCheck, new Object[] {desc}, Integer.class);
				if(catCount>0) {
					return Messages.DiscountDuplicate;
				}else {
					int insUc=this.jdbc.update(insertDiscountQry, new Object[] {desc,tag,percentage,createdDatetime,lastUpdatedDatetime,effectiveStart,effectiveEnd,1,vendorId});
					if(insUc>0) {
						retMsg= Messages.DiscountCreated;
					}
				}										
			
		}
	 catch (Exception e) {
		logger.error("insertUomDAO error-->"+e);
	}
	return retMsg;

}

public List<DiscountRequest> getDiscount(String vendorId) {
	List<DiscountRequest> uList = null;
	try {
		String getDiscountQry = "select * from discount_coupon_details where  VENDOR_ID=?";
		uList = this.jdbc.query(getDiscountQry, new Object[] { vendorId }, new DiscountRowMapper());
	} catch (Exception e) {
		logger.error("getDiscount DAO error-->" + e);
	}
	return uList;
}

}