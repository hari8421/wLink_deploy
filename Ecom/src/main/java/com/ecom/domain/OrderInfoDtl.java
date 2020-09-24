package com.ecom.domain;

import java.io.Serializable;



public class OrderInfoDtl implements Serializable{

	private static final long serialVersionUID = -1L;
	
	private String oidDtlId;
	private String oid;
	private String pchId;
	private String prodCode;
	private String prodDesc;
	private String subDesc;
	private String price;
	private String qty;
	
	public String getOidDtlId() {
		return oidDtlId;
	}
	public void setOidDtlId(String oidDtlId) {
		this.oidDtlId = oidDtlId;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getPchId() {
		return pchId;
	}
	public void setPchId(String pchId) {
		this.pchId = pchId;
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
	public String getSubDesc() {
		return subDesc;
	}
	public void setSubDesc(String subDesc) {
		this.subDesc = subDesc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	
	
	
	
}
