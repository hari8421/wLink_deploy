package com.ecom.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.ecom.config.Messages;
import com.ecom.domain.SignUpRequest;
import com.ecom.domain.UserDtls;
import com.ecom.jpaEntity.AdminJpaEntity;
import com.ecom.repository.AdminJpaRepository;
import com.ecom.rowmapper.UserDtlsRowMapper;


@Repository
@Transactional
public class UserMstDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserMstDAO.class);
	@Autowired JdbcTemplate jdbc;
	@Autowired AdminJpaRepository adminJpaRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	public String registerUser( String password, String contactNo, String vendorCode, String name) {

		String retMsg="";
		
         try {
        	 
        	 String encPwd=bcryptEncoder.encode(password);
 			
        	 
        		 String getMobileCnt="select count(CONTACT_NO) from  customer_login_dtl where CONTACT_NO=?";
        			int mCnt=this.jdbc.queryForObject(getMobileCnt, new Object[] {contactNo}, Integer.class);
        			if(mCnt>0) {
        				return Messages.dupUser;
        			}
        		 
        	 
 			 

        	 String insertUser="INSERT INTO customer_login_dtl (CONTACT_NO,PASSWORD,VENDOR_ID) VALUES (?,?,?)\r\n" + 
        	 		"";
        	 KeyHolder userHolder = new GeneratedKeyHolder();
        	 jdbc.update(
                     connection -> {
                         PreparedStatement ps = connection.prepareStatement(insertUser,new String[]{"ID"});
                         ps.setString(1, contactNo);
                         ps.setString(2, encPwd);
                         ps.setString(3, vendorCode);
                         return ps;
                     }, userHolder);

             int customerId = userHolder.getKey().intValue();
             
            
            		 
             if(customerId>0) {
            	 String insertCustDtl="insert into customer_details(CL_ID,NAME,PRIMARY_CONTACT_NO,VENDOR_ID) values (?,?,?,?)";
            	 this.jdbc.update(insertCustDtl, new Object[] {customerId,name,contactNo,vendorCode});
            	 retMsg=Messages.SignUpSuccess; 
             }else {
            	 retMsg=Messages.SignUpFail;  
             }
			
		} catch (Exception e) {
			logger.error("registerUser DAO error-->"+e);
		}
		return retMsg;
	}

	public List<UserDtls> getUserData(String userName, String vendorId, JdbcTemplate jdbc2) {
		
		List<UserDtls> uList=new ArrayList<UserDtls>();
			try {
				
				if(userName!=null) {
					String getUserQry="select cm.* from customer_details cm ,customer_login_dtl cl where cm.CL_ID=cl.CL_ID and cl.CONTACT_NO=?";
					uList=jdbc2.query(getUserQry, new Object[] {userName}, new UserDtlsRowMapper());
					OrderDAO od=new OrderDAO();
					uList.get(0).setInCartCnt(od.getInCartOrderCount(vendorId, uList.get(0).getCdId()));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getUserData error-->"+e);
			}
			return uList;
		}

	public AdminJpaEntity insertAdmin(AdminJpaEntity adminJpaEntity) {
		adminJpaEntity.setPassWord(bcryptEncoder.encode(adminJpaEntity.getPassWord()));
		return adminJpaRepository.save(adminJpaEntity);
	}

	public AdminJpaEntity adminLogin(@Valid SignUpRequest signUpRequest) {
		
		Optional<AdminJpaEntity> adEntity=adminJpaRepository.findByContactNoAndVendorIdAndIsActive(signUpRequest.getContactNo(),Long.parseLong(signUpRequest.getVendorId()),1);
		if(!adEntity.isPresent()||!bcryptEncoder.matches(signUpRequest.getPassword(), adEntity.get().getPassWord())) {
			return null;
		}
		return adEntity.get();
	}
		

}
