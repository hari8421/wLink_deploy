package com.ecom.domain;

import java.io.Serializable;

public class AdminIndProdVarId implements Serializable {

	private static final long serialVersionUID = -1L;

	private String psId;
	private String varCode;
	private String varDesc;
	private String varId;

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public String getVarCode() {
		return varCode;
	}

	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}

	public String getVarDesc() {
		return varDesc;
	}

	public void setVarDesc(String varDesc) {
		this.varDesc = varDesc;
	}

	public String getVarId() {
		return varId;
	}

	public void setVarId(String varId) {
		this.varId = varId;
	}

}
