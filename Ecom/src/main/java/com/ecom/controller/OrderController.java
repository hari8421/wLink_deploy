package com.ecom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.domain.OrderInCartProdSummary;
import com.ecom.domain.OrderInCartSummary;
import com.ecom.domain.OrderInsertRequest;
import com.ecom.jpaEntity.OrderHdrJpaEntity;
import com.ecom.requestEntity.customerOrderAddConf;
import com.ecom.requestEntity.customerOrderCombo;
import com.ecom.requestEntity.customerOrderItem;
import com.ecom.service.OrderService;


@Controller
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
	@Autowired
	OrderService orderService;


	@RequestMapping(value = "/getInCartOrderCount")
	@PostMapping
	public ResponseEntity<String> getInCartOrderCount(@RequestBody customerOrderCombo orderDetails) {
		return new ResponseEntity<String>(
				orderService.getInCartOrderCount(orderDetails.getVendorId(), orderDetails.getCustomerId()),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/getInCartProduct")
	@PostMapping
	public ResponseEntity<List<OrderInCartSummary>> getInCartProduct(@RequestBody customerOrderCombo orderDetails) {
		return new ResponseEntity<List<OrderInCartSummary>>(
				orderService.getInCartProduct(orderDetails.getVendorId(), orderDetails.getCustomerId()), HttpStatus.OK);

	}

	@RequestMapping(value = "/updateCartDelAddress")
	@PostMapping
	public ResponseEntity<String> updateCartDelAddress(@RequestBody customerOrderAddConf orderDetails) {
		return new ResponseEntity<String>(orderService.updateCartDelAddress(orderDetails.getVendorId(),
				orderDetails.getCustomerId(), orderDetails.getAddressId()), HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/confirmCart")
	@PostMapping
	public ResponseEntity<String> confirmCart(@RequestBody customerOrderCombo orderDetails) {
		return new ResponseEntity<String>(orderService.confirmCart(orderDetails.getVendorId(),
				orderDetails.getCustomerId()), HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/deleteCartItem")
	@PostMapping
	public ResponseEntity<String> deleteCartItem(@RequestBody customerOrderItem customerOrderItem) {
		return new ResponseEntity<String>(orderService.deleteCartItem(customerOrderItem.getVendorId(),
				customerOrderItem.getCustomerId(),customerOrderItem.getItemId()), HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/getAccInCartProduct")
	@PostMapping
	public ResponseEntity<List<OrderInCartProdSummary>> getAccInCartProduct(@RequestBody customerOrderCombo orderDetails) {
		return new ResponseEntity<List<OrderInCartProdSummary>>(
				orderService.getAccInCartProduct(orderDetails.getVendorId(), orderDetails.getCustomerId()), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/addToCart")
	@PostMapping
	public ResponseEntity<String> addToCart(@RequestBody OrderInsertRequest orderInsertRequest) {
		String retMsg="";
		try {
			retMsg=orderService.addToCart(orderInsertRequest);
		} catch (Exception e) {
	        logger.error("addToCart error-->"+e);
		}
		 return ResponseEntity.ok(retMsg);
				

	}
//Admin panel services
	@RequestMapping("/getAllOrderDtls/{vendorId}/{pageNo}/{size}")
	@GetMapping
	public ResponseEntity<Page<OrderHdrJpaEntity>> getAllOrderDtls(
			@PathVariable(value = "vendorId", required = true) String vendorId,@PathVariable(value = "pageNo", required = true) String pageNo,
			@PathVariable(value = "size", required = true) String size) {
		Page<OrderHdrJpaEntity> pcList = null;
		try {
			Pageable paging = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(size));
			pcList = orderService.getAllOrderDtls(vendorId,paging);
		} catch (Exception e) {
			logger.error("getAllOrderDtls error-->" + e);
		}

		return ResponseEntity.ok(pcList);
	}
	
	@RequestMapping("/getAllOrderDtlsByStatus/{vendorId}/{status}/{pageNo}/{size}")
	@GetMapping
	public ResponseEntity<Page<OrderHdrJpaEntity>> getAllOrderDtlsByStatus(
			@PathVariable(value = "vendorId", required = true) String vendorId,@PathVariable(value = "status", required = true) String status,@PathVariable(value = "pageNo", required = true) String pageNo,
			@PathVariable(value = "size", required = true) String size) {
		Page<OrderHdrJpaEntity> pcList = null;
		try {
			Pageable paging = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(size));
			pcList = orderService.getAllOrderDtlsByStatus(vendorId,status,paging);
		} catch (Exception e) {
			logger.error("getAllOrderDtlsByStatus error-->" + e);
		}

		return ResponseEntity.ok(pcList);
	}
	
	@RequestMapping("/getOrderDtlsById/{vendorId}/{oiD}")
	@GetMapping
	public ResponseEntity<OrderHdrJpaEntity> getOrderDtlsById(
			@PathVariable(value = "vendorId", required = true) String vendorId,@PathVariable(value = "oiD", required = true) String oiD) {
		OrderHdrJpaEntity pcList = null;
		try {
			pcList = orderService.getOrderDtlsById(vendorId,oiD);
		} catch (Exception e) {
			logger.error("getOrderDtlsById error-->" + e);
		}

		return ResponseEntity.ok(pcList);
	}
	
	@RequestMapping(value = "/updateOrder")
	@PostMapping
	public ResponseEntity<OrderHdrJpaEntity> updateOrder(@RequestBody OrderHdrJpaEntity orderHdrJpaEntity) {
		 OrderHdrJpaEntity ohEntity=null;
		try {
			ohEntity=orderService.updateOrder(orderHdrJpaEntity);
		} catch (Exception e) {
	        logger.error("updateOrder error-->"+e);
		}
		 return ResponseEntity.ok(ohEntity);
				

	}
}