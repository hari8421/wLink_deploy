package com.ecom.domain;

import java.io.Serializable;

public class vendorPrefLocationDtl implements Serializable {

	private static final long serialVersionUID = -1L;
	private String refId;
	private String refCode;
	private String refDesc;

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public String getRefDesc() {
		return refDesc;
	}

	public void setRefDesc(String refDesc) {
		this.refDesc = refDesc;
	}

}
