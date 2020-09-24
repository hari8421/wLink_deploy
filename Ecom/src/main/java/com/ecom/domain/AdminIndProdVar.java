package com.ecom.domain;

import java.io.Serializable;
import java.util.List;

public class AdminIndProdVar implements Serializable {

	private static final long serialVersionUID = -1L;

	private String psId;
	private String dtlDesc;
	private String status;
	private String price;
	private List<AdminIndProdVarId> varId;

	public List<AdminIndProdVarId> getVarId() {
		return varId;
	}

	public void setVarId(List<AdminIndProdVarId> varId) {
		this.varId = varId;
	}

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public String getDtlDesc() {
		return dtlDesc;
	}

	public void setDtlDesc(String dtlDesc) {
		this.dtlDesc = dtlDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
