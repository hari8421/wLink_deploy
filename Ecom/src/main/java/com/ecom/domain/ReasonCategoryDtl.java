package com.ecom.domain;

import java.io.Serializable;



public class ReasonCategoryDtl implements Serializable{

	private static final long serialVersionUID = -1L;
	
	private String reasonCategoryCode;
	private String categoryDesc;
	public String getReasonCategoryCode() {
		return reasonCategoryCode;
	}
	public void setReasonCategoryCode(String reasonCategoryCode) {
		this.reasonCategoryCode = reasonCategoryCode;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	
}
