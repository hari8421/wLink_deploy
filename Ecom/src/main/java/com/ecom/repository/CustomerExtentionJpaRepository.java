package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.jpaEntity.CustomerDtlExtentionJpaEntity;

public interface CustomerExtentionJpaRepository extends JpaRepository<CustomerDtlExtentionJpaEntity, Long>{

}
