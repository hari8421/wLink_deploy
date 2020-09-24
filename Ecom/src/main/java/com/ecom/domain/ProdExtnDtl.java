package com.ecom.domain;

import java.io.Serializable;

public class ProdExtnDtl implements Serializable {

	private static final long serialVersionUID = -1L;

	private String prodTitle;
	private String prodKey;
	private String prodValue;

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
