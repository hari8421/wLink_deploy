package com.ecom.domain;

import java.io.Serializable;

public class ProdViewReq implements Serializable {

	private static final long serialVersionUID = -1L;
	private String vendorId;
	private String psname;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getPsname() {
		return psname;
	}

	public void setPsname(String psname) {
		this.psname = psname;
	}

}