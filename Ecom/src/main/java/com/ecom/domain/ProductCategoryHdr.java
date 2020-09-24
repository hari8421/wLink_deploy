package com.ecom.domain;

import java.io.Serializable;



public class ProductCategoryHdr implements Serializable{

	private static final long serialVersionUID = -1L;
	
	private String parentCatHdr;
	private String pcmCode;
	private String pcmDesc;
	private String isPrimary;
	private String isActive;
	public String getParentCatHdr() {
		return parentCatHdr;
	}
	public void setParentCatHdr(String parentCatHdr) {
		this.parentCatHdr = parentCatHdr;
	}
	public String getPcmCode() {
		return pcmCode;
	}
	public void setPcmCode(String pcmCode) {
		this.pcmCode = pcmCode;
	}
	public String getPcmDesc() {
		return pcmDesc;
	}
	public void setPcmDesc(String pcmDesc) {
		this.pcmDesc = pcmDesc;
	}
	public String getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
}
