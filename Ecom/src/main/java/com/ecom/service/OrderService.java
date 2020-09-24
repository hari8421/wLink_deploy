package com.ecom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecom.dao.OrderDAO;
import com.ecom.domain.OrderInCartProdSummary;
import com.ecom.domain.OrderInCartSummary;
import com.ecom.domain.OrderInsertRequest;
import com.ecom.jpaEntity.OrderDtlJpaEntity;
import com.ecom.jpaEntity.OrderHdrJpaEntity;
import com.ecom.repository.OrderHdrJpaRepository;
import com.ecom.repository.PcHdrJpaRepository;
import com.ecom.repository.ProductSubHdrJpaRepository;

@Service
public class OrderService {

	@Autowired
	OrderDAO orderDao;
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	@Autowired OrderHdrJpaRepository orderHdrJpaRepository;
	
	@Autowired PcHdrJpaRepository pcHdrJpaRepository;
	
	@Autowired ProductSubHdrJpaRepository productsubRepository;

	public String getInCartOrderCount(String vendorId, String customerId) {
		return orderDao.getInCartOrderCount(vendorId, customerId);
	}

	public List<OrderInCartSummary> getInCartProduct(String vendorId, String customerId) {
		return orderDao.getInCartProduct(vendorId, customerId);
	}

	public String updateCartDelAddress(String vendorId, String customerId, String addressId) {
		return orderDao.updateCartDelAddress(vendorId, customerId, addressId);
	}

	public String confirmCart(String vendorId, String customerId) {
		return orderDao.confirmCart(vendorId, customerId);
	}

	public String deleteCartItem(String vendorId, String customerId, String itemId) {
		return orderDao.deleteCartItem(vendorId, customerId, itemId);
	}

	public List<OrderInCartProdSummary> getAccInCartProduct(String vendorId, String customerId) {
		return orderDao.getAccInCartProduct(vendorId, customerId);
	}

	

	public String addToCart(OrderInsertRequest orderInsertRequest) {
		return orderDao.addToCart(orderInsertRequest);
	}

	public Page<OrderHdrJpaEntity> getAllOrderDtls(String vendorId, Pageable paging) {
		
		return orderHdrJpaRepository.findByOrderStatusNotAndVendorIdAndCdeIdNotNull("ST001",vendorId,paging);
	}

	public Page<OrderHdrJpaEntity> getAllOrderDtlsByStatus(String vendorId, String status, Pageable paging) {
		Page<OrderHdrJpaEntity> ohData=null;
		if(status.equalsIgnoreCase("3")) {
			ohData=orderHdrJpaRepository.findByVendorIdAndOrderStatus(vendorId,"SC001",paging);
		}else if(status.equalsIgnoreCase("1")) {
			ohData=orderHdrJpaRepository.findByOrderStatusOrOrderStatusOrOrderStatusOrOrderStatusAndVendorIdAndCdeIdNotNull(vendorId,"ST002","ST003","ST004","ST005",paging);
		}else {
			ohData=orderHdrJpaRepository.findByVendorIdAndOrderStatus(vendorId,"ST006",paging);
		}
		return ohData;
	}

	public OrderHdrJpaEntity getOrderDtlsById(String vendorId, String oiD) {
		OrderHdrJpaEntity oEntity= orderHdrJpaRepository.findByVendorIdAndOiD(vendorId,Long.parseLong(oiD));
		
		try {
			for(OrderDtlJpaEntity o:oEntity.getOdDtlEntity()) {
				if(o.getPchId()!=null) {
					o.setPcEntity(pcHdrJpaRepository.findById(Long.parseLong(o.getPchId())));
				}
                if(o.getPsId()!=null) {
                	o.setPshEntity(productsubRepository.findById(Long.parseLong(o.getPsId())));
				}
				
				
			}
		} catch (Exception e) {
			logger.error("getOrderDtlsById error");
		}
		return oEntity;
	}

	public OrderHdrJpaEntity updateOrder(OrderHdrJpaEntity orderHdrJpaEntity) {
		return orderHdrJpaRepository.save(orderHdrJpaEntity);
	}

	

}
