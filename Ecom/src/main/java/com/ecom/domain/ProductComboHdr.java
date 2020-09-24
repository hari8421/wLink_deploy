package com.ecom.domain;

import java.io.Serializable;

public class ProductComboHdr implements Serializable {

	private static final long serialVersionUID = -1L;
	private String pchId;
	private String com_code;
	private String comDesc;
	private String tag;
	private String effectiveStartDate;
	private String effectiveEndDate;
	private String isActive;
	private String originalRate;
	private String discountPercent;
	private String discountRate;
	private String actualRate;
	private String pcdId;
	private String comLongDesc;
	private String comType;
	private String prodId;
	private String psId;
	private String qty;
	private int noOfImages;
	
	

	public String getComDesc() {
		return comDesc;
	}

	public void setComDesc(String comDesc) {
		this.comDesc = comDesc;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public int getNoOfImages() {
		return noOfImages;
	}

	public void setNoOfImages(int noOfImages) {
		this.noOfImages = noOfImages;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getComLongDesc() {
		return comLongDesc;
	}

	public void setComLongDesc(String comLongDesc) {
		this.comLongDesc = comLongDesc;
	}

	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}

	public String getPchId() {
		return pchId;
	}

	public void setPchId(String pchId) {
		this.pchId = pchId;
	}

	public String getCom_code() {
		return com_code;
	}

	public void setCom_code(String com_code) {
		this.com_code = com_code;
	}

	public String getCom_desc() {
		return comDesc;
	}

	public void setCom_desc(String com_desc) {
		this.comDesc = com_desc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(String effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public String getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(String effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getOriginalRate() {
		return originalRate;
	}

	public void setOriginalRate(String originalRate) {
		this.originalRate = originalRate;
	}

	public String getDiscountPercentage() {
		return discountPercent;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercent = discountPercentage;
	}

	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	public String getActualRate() {
		return actualRate;
	}

	public void setActualRate(String actualRate) {
		this.actualRate = actualRate;
	}

	public String getPcdId() {
		return pcdId;
	}

	public void setPcdId(String pcdId) {
		this.pcdId = pcdId;
	}

}
