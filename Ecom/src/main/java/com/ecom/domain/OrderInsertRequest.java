package com.ecom.domain;

import java.io.Serializable;



public class OrderInsertRequest implements Serializable{

	private static final long serialVersionUID = -1L;
	
	private String cdId;
	private String vendorId;
	private String psId;
	private String isCombo;
	private String pchId;
	private String qty;
	
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getCdId() {
		return cdId;
	}
	public void setCdId(String cdId) {
		this.cdId = cdId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getPsId() {
		return psId;
	}
	public void setPsId(String psId) {
		this.psId = psId;
	}
	public String getIsCombo() {
		return isCombo;
	}
	public void setIsCombo(String isCombo) {
		this.isCombo = isCombo;
	}
	public String getPchId() {
		return pchId;
	}
	public void setPchId(String pchId) {
		this.pchId = pchId;
	}
	
	
	
}
