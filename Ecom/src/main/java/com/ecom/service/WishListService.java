package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dao.WishListDAO;
import com.ecom.domain.WIshListRequest;
import com.ecom.domain.WishListProdSummary;

@Service
public class WishListService {

	@Autowired
	WishListDAO wishListDAO;

	public List<WishListProdSummary> getWishListItems(String vendorId, String customerId) {
		
		return wishListDAO.getWishListItems(vendorId, customerId);
	}

	public String insertWishList(WIshListRequest wishListReq) {
		
		return wishListDAO.insertWishList(wishListReq);
	}

}
