package com.ecom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ecom.config.Messages;
import com.ecom.domain.UomRequest;
import com.ecom.rowmapper.UomRowMapper;


@Repository
@Transactional
public class UomDAO {

	private static final Logger logger = LoggerFactory.getLogger(UomDAO.class);
	@Autowired JdbcTemplate jdbc;
	public String insertUom(String uomDesc, String isActive, String vendorId) {
		String retMsg="";
		try {
			String insertUomQry="INSERT INTO `uom_mst` (`UOM_DESC`, `IS_ACTIVE`, `VENDOR_ID`) VALUES (?, ?, ?)";
			String uomDupCheck="select count(*) from uom_mst where  UOM_DESC=?";
			
				int catCount=this.jdbc.queryForObject(uomDupCheck, new Object[] {uomDesc}, Integer.class);
				if(catCount>0) {
					return Messages.UomDuplicate;
				}else {
					int insUc=this.jdbc.update(insertUomQry, new Object[] {uomDesc,1,vendorId});
					if(insUc>0) {
						retMsg= Messages.UomCreated;
					}
				}										
			
		}
	 catch (Exception e) {
		logger.error("insertUomDAO error-->"+e);
	}
	return retMsg;

}
	
	public List<UomRequest> getUom(String vendorId) {
		List<UomRequest> uList = null;
		try {
			String getUomQry = "select * from uom_mst where  VENDOR_ID=?";
			uList = this.jdbc.query(getUomQry, new Object[] { vendorId }, new UomRowMapper());
		} catch (Exception e) {
			logger.error("getUom DAO error-->" + e);
		}
		return uList;
	}
	
}