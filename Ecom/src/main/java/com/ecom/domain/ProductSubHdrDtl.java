package com.ecom.domain;

import java.io.Serializable;



public class ProductSubHdrDtl implements Serializable{

	private static final long serialVersionUID = -1L;
	 
	private String psId;
	private String prodId;
	private String dtlDesc;
	private String primaryUom;
	private String conversionRate;
	private String status;
	private String price;
	private String psdId;
	private String varientTypeCode;
	private String value;
	private String varientDesc;
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
	public String getPrimaryUom() {
		return primaryUom;
	}
	public void setPrimaryUom(String primaryUom) {
		this.primaryUom = primaryUom;
	}
	public String getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(String conversionRate) {
		this.conversionRate = conversionRate;
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
	public String getPsdId() {
		return psdId;
	}
	public void setPsdId(String psdId) {
		this.psdId = psdId;
	}
	public String getVarientTypeCode() {
		return varientTypeCode;
	}
	public void setVarientTypeCode(String varientTypeCode) {
		this.varientTypeCode = varientTypeCode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getVarientDesc() {
		return varientDesc;
	}
	public void setVarientDesc(String varientDesc) {
		this.varientDesc = varientDesc;
	}
	
	
	
}
