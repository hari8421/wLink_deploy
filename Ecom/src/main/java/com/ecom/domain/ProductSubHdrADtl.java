package com.ecom.domain;

import java.util.List;

public class ProductSubHdrADtl {

	private String prodId;
	private String dtlDesc;
	private String statusDesp;
	private String price;
	private String psId;
	private List<ProductSubHdrADtlII> varList;
	private String prodCode;
	private String prodDesc;
	private String prodName;

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
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

	public List<ProductSubHdrADtlII> getVarList() {
		return varList;
	}

	public void setVarList(List<ProductSubHdrADtlII> varList) {
		this.varList = varList;
	}

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getDtlDesc() {
		return dtlDesc;
	}

	public void setDtlDesc(String dtlDesc) {
		this.dtlDesc = dtlDesc;
	}

	public String getStatusDesp() {
		return statusDesp;
	}

	public void setStatusDesp(String statusDesp) {
		this.statusDesp = statusDesp;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}