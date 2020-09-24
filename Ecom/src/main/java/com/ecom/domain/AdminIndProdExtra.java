package com.ecom.domain;

import java.io.Serializable;

public class AdminIndProdExtra implements Serializable {

	private static final long serialVersionUID = -1L;

	private String peId;
	private String prodTitle;
	private String prodKey;
	private String prodValue;
	private String isActive;

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getPeId() {
		return peId;
	}

	public void setPeId(String peId) {
		this.peId = peId;
	}

	public String getProdTitle() {
		return prodTitle;
	}

	public void setProdTitle(String prodTitle) {
		this.prodTitle = prodTitle;
	}

	public String getProdKey() {
		return prodKey;
	}

	public void setProdKey(String prodKey) {
		this.prodKey = prodKey;
	}

	public String getProdValue() {
		return prodValue;
	}

	public void setProdValue(String prodValue) {
		this.prodValue = prodValue;
	}

}
