package com.ecom.requestEntity;

import java.io.Serializable;

public class customerOrderCombo implements Serializable {

	private static final long serialVersionUID = -1L;
	private String customerId;
	private String vendorId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

}
