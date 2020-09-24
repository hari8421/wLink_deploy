package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.domain.WIshListRequest;
import com.ecom.domain.WishListProdSummary;
import com.ecom.requestEntity.customerOrderCombo;
import com.ecom.service.WishListService;

@Controller
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishListController {
	@Autowired
	WishListService wishListService;

	@RequestMapping(value = "/getWishListItems")
	@PostMapping
	public ResponseEntity<List<WishListProdSummary>> getWishListItems(@RequestBody customerOrderCombo orderDetails) {
		return new ResponseEntity<List<WishListProdSummary>>(
				wishListService.getWishListItems(orderDetails.getVendorId(), orderDetails.getCustomerId()),
				HttpStatus.OK);

	}
	
	@RequestMapping(value = "/insertWishList")
	@PostMapping
	public ResponseEntity<String> insertWishList(@RequestBody WIshListRequest wishListReq) {
		return new ResponseEntity<String>(
				wishListService.insertWishList(wishListReq),
				HttpStatus.OK);

	}

}