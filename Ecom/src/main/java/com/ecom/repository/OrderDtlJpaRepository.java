package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.jpaEntity.OrderDtlJpaEntity;

public interface OrderDtlJpaRepository extends JpaRepository<OrderDtlJpaEntity, Long>{

}
