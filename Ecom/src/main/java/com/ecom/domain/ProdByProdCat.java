package com.ecom.domain;

import java.io.Serializable;

public class ProdByProdCat implements Serializable {

	private static final long serialVersionUID = -1L;
	private String prodCode;
	private String prodId;
	private String prodDesc;
	private String prodName;
	private String tags;
	private String primaryDesc;
	private String secDesc;

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPrimaryDesc() {
		return primaryDesc;
	}

	public void setPrimaryDesc(String primaryDesc) {
		this.primaryDesc = primaryDesc;
	}

	public String getSecDesc() {
		return secDesc;
	}

	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

}
