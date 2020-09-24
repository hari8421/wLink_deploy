package com.ecom.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecom.domain.DAOUser;





@Repository
public interface UserDAO extends CrudRepository<DAOUser, Integer> {
	
	@Query(value="select * from customer_login_dtl l inner join vendor_branch_details vb on vb.VENDOR_ID=l.VENDOR_ID where vb.IS_ACTIVE=1 and l.CONTACT_NO=:username ",nativeQuery=true)
	DAOUser findByLOGIN_USERNAME(String username);
	
	@Query(value="select * from customer_login_dtl l inner join vendor_branch_details vb on vb.VENDOR_ID=l.VENDOR_ID where vb.IS_ACTIVE=1 and l.CONTACT_NO=:username ",nativeQuery=true)
	DAOUser findByMobileNumber(String username);
	
	
	
}