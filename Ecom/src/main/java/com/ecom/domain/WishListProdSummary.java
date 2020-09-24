package com.ecom.domain;

import java.util.List;

public class WishListProdSummary {

	private String prodId;
	private String prodCode;
	private String prodDesc;
	private String subDesc;
	private String comboDesc;
	private String price;
	private String cwid;
	private String psId;
	List<OrderInCartVarSummary> varSum;

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public List<OrderInCartVarSummary> getVarSum() {
		return varSum;
	}

	public void setVarSum(List<OrderInCartVarSummary> varSum) {
		this.varSum = varSum;
	}

	public String getCwid() {
		return cwid;
	}

	public void setCwid(String cwid) {
		this.cwid = cwid;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

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

	public String getSubDesc() {
		return subDesc;
	}

	public void setSubDesc(String subDesc) {
		this.subDesc = subDesc;
	}

	public String getComboDesc() {
		return comboDesc;
	}

	public void setComboDesc(String comboDesc) {
		this.comboDesc = comboDesc;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}