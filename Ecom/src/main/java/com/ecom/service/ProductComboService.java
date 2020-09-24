package com.ecom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecom.common.CommonMethodsDAO;
import com.ecom.dao.ProductComboDAO;
import com.ecom.domain.BannerHdrDtl;
import com.ecom.domain.ProductComboHdr;
import com.ecom.domain.ProductComboResponse;
import com.ecom.jpaEntity.PcDtlJpaEntity;
import com.ecom.jpaEntity.PcHdrJpaEntity;
import com.ecom.repository.PcDtlJpaRepository;
import com.ecom.repository.PcHdrJpaRepository;


@Service
public class ProductComboService {

	private static final Logger logger = LoggerFactory.getLogger(ProductComboService.class);
	@Autowired ProductComboDAO productComboDAO;
	@Autowired JdbcTemplate jdbc;
	@Autowired PcHdrJpaRepository pcHdrRepository;
	@Autowired PcDtlJpaRepository pcDtlRepository;


	public List<ProductComboResponse> getProductComboDetailsBiID(String vendorId, String pchId) {
		List<ProductComboResponse> pcList=null;
		try {
			pcList=productComboDAO.getProductComboDetailsBiID(vendorId,pchId);
		} catch (Exception e) {
			logger.error("getProductComboDetails service error-->"+e);
		}
		return pcList;
	}



	public List<BannerHdrDtl> getProductBanerDetails(String vendorId, String bType) {
		List<BannerHdrDtl> pcList=null;
		try {
			pcList=productComboDAO.getProductBanerDetails(vendorId,bType);
		} catch (Exception e) {
			logger.error("getProductBanerDetails service error-->"+e);
		}
		return pcList;
	}



	public List<List<ProductComboHdr>> getTrendingProductDetails(String vendorId, String type) {
		List<List<ProductComboHdr>> pcList=null;
		try {
			pcList=productComboDAO.getTrendingProductDetails(vendorId,type);
		} catch (Exception e) {
			logger.error("getTrendingProductDetails service error-->"+e);
		}
		return pcList;
	}

	public List<ProductComboHdr> getAllTrendingProductDetails(String vendorId, String type) {
		List<ProductComboHdr> pcList=null;
		try {
			pcList=productComboDAO.getAllTrendingProductDetails(vendorId,type);
		} catch (Exception e) {
			logger.error("getAllTrendingProductDetails service error-->"+e);
		}
		return pcList;
	}

//Services for admin panel

	public Page<PcHdrJpaEntity> getAllComboDetails(String vendorId,String pcType, Pageable paging) {
		
		return pcHdrRepository.findByvendorIdAndPcType(vendorId,pcType,paging);
	}



	public String insertCombo(PcHdrJpaEntity pcHdrJpaEntity) {
		// pcHdrRepository   pcDtlRepository
		pcHdrJpaEntity.setComCode(CommonMethodsDAO.generateComCode(pcHdrJpaEntity.getVendorId(),jdbc));
		PcHdrJpaEntity insPh=pcHdrRepository.save(pcHdrJpaEntity);
		for(PcDtlJpaEntity pd:pcHdrJpaEntity.getPcDtlEntity()) {
			pd.setPcHdrId(insPh.getPcHdrId());
			logger.info("Dtl Id-->"+pd.getPcdId());
			logger.info("Hdr Id-->"+insPh.getPcHdrId());
			logger.info("-->"+pcDtlRepository.save(pd).getPcdId());
		}
		return insPh.getPcHdrId()+"";
	}



	public String updateCombo(PcHdrJpaEntity pcHdrJpaEntity) {
		PcHdrJpaEntity insPh=pcHdrRepository.save(pcHdrJpaEntity);
		for(PcDtlJpaEntity pd:pcHdrJpaEntity.getPcDtlEntity()) {
			
			logger.info("Dtl Id-->"+pd.getPcdId());
			logger.info("Hdr Id-->"+insPh.getPcHdrId());
			logger.info("-->"+pcDtlRepository.save(pd).getPcdId());
		}
		return insPh.getPcHdrId()+"";
	}

	

}
