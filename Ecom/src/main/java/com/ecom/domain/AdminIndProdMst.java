package com.ecom.domain;

import java.io.Serializable;
import java.util.List;

public class AdminIndProdMst implements Serializable {

	private static final long serialVersionUID = -1L;

	private String prodId;
	private String prodCode;
	private String prodname;
	private String prodDesc;
	private String categoryId;
	private String isActive;
	private String brand;
	private String tags;
	private String distinctVarCode;
	private String distinctVarDesc;
	private List<AdminIndProdExtra> extraDet;
	private List<AdminIndProdVar> prodVar;

	public String getDistinctVarDesc() {
		return distinctVarDesc;
	}

	public void setDistinctVarDesc(String distinctVarDesc) {
		this.distinctVarDesc = distinctVarDesc;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<AdminIndProdExtra> getExtraDet() {
		return extraDet;
	}

	public void setExtraDet(List<AdminIndProdExtra> extraDet) {
		this.extraDet = extraDet;
	}

	public List<AdminIndProdVar> getProdVar() {
		return prodVar;
	}

	public void setProdVar(List<AdminIndProdVar> prodVar) {
		this.prodVar = prodVar;
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

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getDistinctVarCode() {
		return distinctVarCode;
	}

	public void setDistinctVarCode(String distinctVarCode) {
		this.distinctVarCode = distinctVarCode;
	}

}
