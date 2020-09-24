package com.ecom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.jpaEntity.OrderHdrJpaEntity;

public interface OrderHdrJpaRepository extends JpaRepository<OrderHdrJpaEntity, Long>{
	Page<OrderHdrJpaEntity> findByVendorIdAndOrderStatus(String vendorId,String oStatus, Pageable paging);
	Page<OrderHdrJpaEntity> findByOrderStatusNotAndVendorIdAndCdeIdNotNull(String status,String vendorId, Pageable paging);
   OrderHdrJpaEntity findByVendorIdAndOiD(String vendorId,Long oiD);
   
   Page<OrderHdrJpaEntity> findByOrderStatusOrOrderStatusOrOrderStatusOrOrderStatusAndVendorIdAndCdeIdNotNull(String status1,String status2,String status3,String status4,String vendorId, Pageable paging);
}
