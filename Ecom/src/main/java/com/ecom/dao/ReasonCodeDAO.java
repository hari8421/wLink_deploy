package com.ecom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ecom.config.Messages;
import com.ecom.domain.ReasonCategoryDtl;
import com.ecom.rowmapper.ReasonCategoryDtlRowMapper;

@Repository
@Transactional
public class ReasonCodeDAO {

	private static final Logger logger = LoggerFactory.getLogger(ReasonCodeDAO.class);
	@Autowired
	JdbcTemplate jdbc;

	public String insertReasonCodeMaster(String reasonCategory, String reasonDesc, String vendorId) {
		String retMsg="";
		try {
			String insertReasonQry="INSERT INTO reason_mst (REASON_CATEGORY_CODE, REASON_DESC,IS_ACTIVE,VENDOR_ID) VALUES (?, ?, ?,?)";
			String reasonDupCheck="select count(*) from reason_mst where  REASON_DESC=?";
			
				int rCount=this.jdbc.queryForObject(reasonDupCheck, new Object[] {reasonDesc}, Integer.class);
				if(rCount>0) {
					return Messages.dupReason;
				}else {
					int insReasonCnt=this.jdbc.update(insertReasonQry, new Object[] {reasonCategory,reasonDesc,1,vendorId});
					if(insReasonCnt>0) {
						retMsg= Messages.reasonCreated;
					}else {
						retMsg= Messages.reasonCodeCreationFailed;
					}
				}										
			
		}
	 catch (Exception e) {
		logger.error("insertUomDAO error-->"+e);
	}
	return retMsg;
		

	}

	public List<ReasonCategoryDtl> getReasonCategory(String vendorId) {
		List<ReasonCategoryDtl> rcList = null;
		try {
			String getProdCatQry = "select * from reason_category_mst where  VENDOR_ID=?";
			rcList = this.jdbc.query(getProdCatQry, new Object[] { vendorId }, new ReasonCategoryDtlRowMapper());
		} catch (Exception e) {
			logger.error("getReasonCategory DAO error-->" + e);
		}
		return rcList;
	}
	
	
}