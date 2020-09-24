package com.ecom.domain;

import java.io.Serializable;

public class FileMangerRequestDtl implements Serializable {

	private static final long serialVersionUID = -1L;
	private String referenceId;
	private String referenceCode;
	private String vendorId;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

}
