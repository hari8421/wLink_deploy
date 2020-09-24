package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.jpaEntity.AdminJpaEntity;

public interface AdminJpaRepository extends JpaRepository<AdminJpaEntity, Long>{

	Optional<AdminJpaEntity> findByContactNoAndVendorIdAndIsActive(String string, long string2, int i);
}
