package com.ecom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.jpaEntity.ProductJpaEntity;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long>{

	Page<ProductJpaEntity> findByvId(String vendorId,Pageable pageable);
	
	

}
