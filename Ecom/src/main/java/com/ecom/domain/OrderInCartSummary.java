package com.ecom.domain;

import java.util.List;

public class OrderInCartSummary {
	private String orderCode;
	private String totalPrice;
	private String discountPrice;
	private String subTotal;
	private String comboPrice;
	private String oId;
	private String cdeId;
	
	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}

	public String getCdeId() {
		return cdeId;
	}

	public void setCdeId(String cdeId) {
		this.cdeId = cdeId;
	}

	List<OrderInCartProdSummary> ProdDet;

	public String getComboPrice() {
		return comboPrice;
	}

	public void setComboPrice(String comboPrice) {
		this.comboPrice = comboPrice;
	}

	public List<OrderInCartProdSummary> getProdDet() {
		return ProdDet;
	}

	public void setProdDet(List<OrderInCartProdSummary> prodDet) {
		ProdDet = prodDet;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

}