package com.ecom.domain;

import java.io.Serializable;



public class ProductHdrDtl implements Serializable{

	private static final long serialVersionUID = -1L;
	
	private String prodId;
	private String bmId;
	private String pcHdrId;
	private String prodCode;
	private String prodName;
	private String prodDesc;
	private String tags;
	private String isActive;
	private String vendorId;
	private String ceratedDtime;
	private String lastUpdatedTime;
	private String bmName;
	private String bmCode;
	private String isOwnBrand;
	private String primaryUom;
	private String secondaryUom;
	private String conversionRate;
	private String status;
	private String price;
	private String psid;
	private String dtlDesc;
	private String pcDtlId;
	
	
	public String getPcDtlId() {
		return pcDtlId;
	}
	public void setPcDtlId(String pcDtlId) {
		this.pcDtlId = pcDtlId;
	}
	public String getDtlDesc() {
		return dtlDesc;
	}
	public void setDtlDesc(String dtlDesc) {
		this.dtlDesc = dtlDesc;
	}
	public String getPsid() {
		return psid;
	}
	public void setPsid(String psid) {
		this.psid = psid;
	}
	
	public String getPrimaryUom() {
		return primaryUom;
	}
	public void setPrimaryUom(String primaryUom) {
		this.primaryUom = primaryUom;
	}
	public String getSecondaryUom() {
		return secondaryUom;
	}
	public void setSecondaryUom(String secondaryUom) {
		this.secondaryUom = secondaryUom;
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

	public String getBmCode() {
		return bmCode;
	}
	public void setBmCode(String bmCode) {
		this.bmCode = bmCode;
	}
	public String getIsOwnBrand() {
		return isOwnBrand;
	}
	public void setIsOwnBrand(String isOwnBrand) {
		this.isOwnBrand = isOwnBrand;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getBmId() {
		return bmId;
	}
	public void setBmId(String bmId) {
		this.bmId = bmId;
	}
	public String getPcHdrId() {
		return pcHdrId;
	}
	public void setPcHdrId(String pcHdrId) {
		this.pcHdrId = pcHdrId;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getCeratedDtime() {
		return ceratedDtime;
	}
	public void setCeratedDtime(String ceratedDtime) {
		this.ceratedDtime = ceratedDtime;
	}
	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public String getBmName() {
		return bmName;
	}
	public void setBmName(String bmName) {
		this.bmName = bmName;
	}
	
	
	
	
}
