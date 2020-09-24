package com.ecom.domain;

public class ReasonCodeRequest {
   
	private String reasonCategory;
	private String reasonDesc;
	private String vendorId;
	public String getReasonCategory() {
		return reasonCategory;
	}
	public void setReasonCategory(String reasonCategory) {
		this.reasonCategory = reasonCategory;
	}
	public String getReasonDesc() {
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
    
}