package com.ecom.domain;

import java.io.Serializable;



public class BrandDtl implements Serializable{

	private static final long serialVersionUID = -1L;
	
	private String bmId;
	private String bmCode;
	private String bmDesc;
	private String isActive;
	private String vendorId;
	private String isOwnBrand;
	public String getBmId() {
		return bmId;
	}
	public void setBmId(String bmId) {
		this.bmId = bmId;
	}
	public String getBmCode() {
		return bmCode;
	}
	public void setBmCode(String bmCode) {
		this.bmCode = bmCode;
	}
	public String getBmDesc() {
		return bmDesc;
	}
	public void setBmDesc(String bmDesc) {
		this.bmDesc = bmDesc;
	}
	public String getIsOwnBrand() {
		return isOwnBrand;
	}
	public void setIsOwnBrand(String isOwnBrand) {
		this.isOwnBrand = isOwnBrand;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
	
	
}
