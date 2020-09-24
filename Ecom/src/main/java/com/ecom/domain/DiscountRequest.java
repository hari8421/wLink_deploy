package com.ecom.domain;

public class DiscountRequest {
   
	private String desc;
	private String discountId;
	private String tag;
    private String effectiveStart;
    private String effectiveEnd;
    private String isActive;
    private String percentage;
    private String vendorId;
    private String createdDatetime;
    private String lastUpdatedDatetime;
	
	public String getDiscountId() {
		return discountId;
	}
	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
	
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getDiscountDesc() {
		return desc;
	}
	public void setDiscountDesc(String desc) {
		this.desc = desc;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
    }
    public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
    }
    public String getEffectiveStart() {
		return effectiveStart;
	}
	public void setEffectiveStart(String effectiveStart) {
		this.effectiveStart = effectiveStart;
	}
	public String getEffectiveEnd() {
		return effectiveEnd;
	}
	public void setEffectiveEnd(String effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
    }
    public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
    }
    public String getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
    }
    public String getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}
	public void setLastUpdatedDatetime(String lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}
	
	
    
}