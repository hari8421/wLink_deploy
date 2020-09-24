package com.ecom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ecom.config.Messages;
import com.ecom.domain.BrandDtl;
import com.ecom.rowmapper.BrandDtlRowMapper;


@Repository
@Transactional
public class BrandDAO {

	private static final Logger logger = LoggerFactory.getLogger(BrandDAO.class);
	@Autowired JdbcTemplate jdbc;
	public String insertBrand(String bmId, String isActive, String vendorId, String bmCode, String bmDesc, String isOwnBrand) {
		String retMsg="";
		try {
			String insertBrandQry="INSERT INTO `brand_mst` (`BM_CODE`,`BM_DESC`, `IS_ACTIVE`, `VENDOR_ID`, `IS_OWN_BRAND`) VALUES (?, ?, ?, ?, ?)";
			String brandDupCheck="select count(*) from brand_mst where  BM_DESC=?";
			
				int catCount=this.jdbc.queryForObject(brandDupCheck, new Object[] {bmDesc}, Integer.class);
				if(catCount>0) {
					return Messages.BrandDuplicate;
				}else {
					int insUc=this.jdbc.update(insertBrandQry, new Object[] {bmCode,bmDesc,1,vendorId,isOwnBrand});
					if(insUc>0) {
						retMsg= Messages.BrandCreated;
					}
				}										
			
		}
	 catch (Exception e) {
		logger.error("insertBrand DAO error-->"+e);
	}
	return retMsg;

}
	
	public List<BrandDtl> getBrand(String vendorId) {
		List<BrandDtl> uList = null;
		try {
			String getBrandQry = "select * from brand_mst where  VENDOR_ID=?";
			uList = this.jdbc.query(getBrandQry, new Object[] { vendorId }, new BrandDtlRowMapper());
		} catch (Exception e) {
			logger.error("getBrandDAO error-->" + e);
		}
		return uList;
	}
	
}