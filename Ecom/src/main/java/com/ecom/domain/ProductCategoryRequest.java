package com.ecom.domain;

public class ProductCategoryRequest {
   
	private String categoryCode;
	private String isPrimmary;
	private String categoryName;
	private String primaryCategory;
	private String secondaryCategory;
	private String vendorId;
	
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getIsPrimmary() {
		return isPrimmary;
	}
	public void setIsPrimmary(String isPrimmary) {
		this.isPrimmary = isPrimmary;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getPrimaryCategory() {
		return primaryCategory;
	}
	public void setPrimaryCategory(String primaryCategory) {
		this.primaryCategory = primaryCategory;
	}
	public String getSecondaryCategory() {
		return secondaryCategory;
	}
	public void setSecondaryCategory(String secondaryCategory) {
		this.secondaryCategory = secondaryCategory;
	}
	
	
    
}