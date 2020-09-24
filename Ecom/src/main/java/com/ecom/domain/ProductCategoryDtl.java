package com.ecom.domain;

import java.io.Serializable;



public class ProductCategoryDtl implements Serializable{

	private static final long serialVersionUID = -1L;
	
	private String prodcatDtlId;
	private String parentCatHdrid;
	private String pcmCode;
	private String pcmDesc;
	public String getProdcatDtlId() {
		return prodcatDtlId;
	}
	public void setProdcatDtlId(String prodcatDtlId) {
		this.prodcatDtlId = prodcatDtlId;
	}
	public String getParentCatHdrid() {
		return parentCatHdrid;
	}
	public void setParentCatHdrid(String parentCatHdrid) {
		this.parentCatHdrid = parentCatHdrid;
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
	
	
}
