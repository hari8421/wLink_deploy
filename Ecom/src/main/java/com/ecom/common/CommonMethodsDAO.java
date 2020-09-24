package com.ecom.common;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ecom.domain.UserDtls;
import com.ecom.rowmapper.UserDtlsRowMapper;


public class CommonMethodsDAO {
	private static final Logger logger = LoggerFactory.getLogger(CommonMethodsDAO.class);

	public static List<String> getCategoriesByPrimary(String primaryCategory, String vendorId, JdbcTemplate jdbcObj) {
		List<String> catList=null;
		try {
			//int hdrCnt=0;

			//String getHdrId="select PARENT_CATE_HDR_ID from product_category_hdr where ";

			

		} catch (Exception Ex) {
			logger.error("Error with getCategoriesByPrimary function" + Ex);
		}
		return catList;
	}
	
	public static int getImageCount(String catCode, String vendorId,String refId, JdbcTemplate jdbcObj) {
		int iCnt=0;
		try {
			String getICntQry="select count(REFERENCE_ID) from file_manager where REFERENCE_ID=? and REFERENCE_CODE=? and VENDOR_ID=?";
			iCnt=jdbcObj.queryForObject(getICntQry, new Object[] {refId,catCode, vendorId}, Integer.class);
		} catch (Exception Ex) {
			logger.error("Error with getImageCount function" + Ex);
		}
		return iCnt;
	}
	
	public static String generateOrderId(String cdId, String vendorId, JdbcTemplate jdbcObj) {
		String orderId="";
		try {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			String getUserQry="select cm.* from customer_details cm ,customer_login_dtl cl where cm.CL_ID=cl.CL_ID  and cm.CD_ID=?";
			List<UserDtls> uList=jdbcObj.query(getUserQry, new Object[] {cdId}, new UserDtlsRowMapper());
			String getVendorName="select VENDOR_NAME from vendor_branch_details where VENDOR_ID=?";
			String vendorName=jdbcObj.queryForObject(getVendorName, new Object[] { vendorId }, String.class);
			String getLastOrderCnt="select count(*)  from order_information_hdr where SUBSTRING(ORDER_CODE,5,4)=? and CD_ID=? and VENDOR_ID=?";
			int oCnt=jdbcObj.queryForObject(getLastOrderCnt, new Object[] {year,cdId, vendorId}, Integer.class);
			if(oCnt>0) {
				String getLastNo="select SUBSTRING(ORDER_CODE,9,1)  from order_information_hdr where SUBSTRING(ORDER_CODE,5,4)=? and CD_ID=? and VENDOR_ID=? order by OI_ID desc limit 1";
				int oNo=jdbcObj.queryForObject(getLastNo, new Object[] {year,cdId, vendorId}, Integer.class);
				int nextOno=oNo+1;
				orderId= uList.get(0).getName().substring(0, 2)+vendorName.substring(0, 2)+year+nextOno;
			}else {
				orderId= uList.get(0).getName().substring(0, 2)+vendorName.substring(0, 2)+year+"1";
			}
		} catch (Exception Ex) {
			logger.error("Error with generateOrderId function" + Ex);
		}
		return orderId;
	}

	public static String generateComCode(String vendorId, JdbcTemplate jdbcObj) {
		String newComCode="";
		try {
			String lastComCode="C00001";
			
			String getLastComCodeCnt="select count(COM_CODE) from product_combo_hdr  where VENDOR_ID=? order by PCH_ID desc limit 1";
			
			int pcCnt=jdbcObj.queryForObject(getLastComCodeCnt, new Object[] {vendorId}, Integer.class);
			if(pcCnt>0) {
				String getLastComCode="select COM_CODE from product_combo_hdr  where VENDOR_ID=? order by PCH_ID desc limit 1";
				lastComCode=jdbcObj.queryForObject(getLastComCode, new Object[] {vendorId}, String.class);
				int nextNumber=Integer.parseInt(lastComCode.substring(1))+1;
				if(nextNumber<10) {
					newComCode=newComCode+"C0000"+nextNumber;
				}else if (nextNumber<100) {
					newComCode=newComCode+"C000"+nextNumber;
				}else if (nextNumber<1000) {
					newComCode=newComCode+"C00"+nextNumber;
				}else if (nextNumber<10000) {
					newComCode=newComCode+"C0"+nextNumber;
				}else {
					newComCode=newComCode+"C"+nextNumber;
				}
			}else {
				newComCode=lastComCode;
			}
			
		} catch (Exception Ex) {
			logger.error("Error with generateComCode function" + Ex);
		}
		return newComCode;
	}


}
