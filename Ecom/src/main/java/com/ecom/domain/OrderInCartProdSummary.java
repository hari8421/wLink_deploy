package com.ecom.domain;

import java.util.List;

public class OrderInCartProdSummary {

	private String prodId;
	private String prodCode;
	private String prodDesc;
	private String qty;
	private String subDesc;
	private String comboDesc;
	private String orderDtlId;
	private String price;
	private String orderStatus;
	private String orderdeliveryDate;
	private String orderDatetime;
	private String psId;

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	List<OrderInCartVarSummary> orderVarSumm;

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderdeliveryDate() {
		return orderdeliveryDate;
	}

	public void setOrderdeliveryDate(String orderdeliveryDate) {
		this.orderdeliveryDate = orderdeliveryDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOrderDtlId() {
		return orderDtlId;
	}

	public void setOrderDtlId(String orderDtlId) {
		this.orderDtlId = orderDtlId;
	}

	public String getSubDesc() {
		return subDesc;
	}

	public void setSubDesc(String subDesc) {
		this.subDesc = subDesc;
	}

	public String getComboDesc() {
		return comboDesc;
	}

	public void setComboDesc(String comboDesc) {
		this.comboDesc = comboDesc;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public List<OrderInCartVarSummary> getOrderVarSumm() {
		return orderVarSumm;
	}

	public void setOrderVarSumm(List<OrderInCartVarSummary> orderVarSumm) {
		this.orderVarSumm = orderVarSumm;
	}

}