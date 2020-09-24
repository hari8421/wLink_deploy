package com.ecom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.jpaEntity.PcHdrJpaEntity;

public interface PcHdrJpaRepository extends JpaRepository<PcHdrJpaEntity, Long>{

	Page<PcHdrJpaEntity> findByvendorIdAndPcType(String vendorId,String pcType,Pageable pageable);
}
