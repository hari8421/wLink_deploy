package com.ecom.domain;

import java.io.Serializable;
import java.util.List;

public class SingleProdDetails implements Serializable {

	private static final long serialVersionUID = -1L;
	private String bmDesc;
	private String prodCode;
	private String prodDesc;
	private String prodId;
	private String prodName;
	private String tags;
	private String dtlDesc;
	private String price;
	private String primaryuom;
	private String statusCode;
	private String statusDesc;
	private String uomDesc;
	private String psId;
	private String comDesc;
	private String comLongDesc;
	private String originalRate;
	private String actualRate;
	private String discountRate;
	private String discountPercent;
	private String rating;
	private String oneRat;
	private String twoRat;
	private String threeRat;
	private String fourRat;
	private String fiveRat;
	private String isRequired;
	private String noOfImages;
	List<OrderInCartVarSummary> orderSumm;
	List<ProdExtnDtl> prodExtn;
	List<ProdReviewDtl> reviewExtn;
	List<ProdSingleCmbDtl> cmbDtl;

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public List<ProdSingleCmbDtl> getCmbDtl() {
		return cmbDtl;
	}

	public void setCmbDtl(List<ProdSingleCmbDtl> cmbDtl) {
		this.cmbDtl = cmbDtl;
	}

	public String getOneRat() {
		return oneRat;
	}

	public void setOneRat(String oneRat) {
		this.oneRat = oneRat;
	}

	public String getTwoRat() {
		return twoRat;
	}

	public void setTwoRat(String twoRat) {
		this.twoRat = twoRat;
	}

	public String getThreeRat() {
		return threeRat;
	}

	public void setThreeRat(String threeRat) {
		this.threeRat = threeRat;
	}

	public String getFourRat() {
		return fourRat;
	}

	public void setFourRat(String fourRat) {
		this.fourRat = fourRat;
	}

	public String getFiveRat() {
		return fiveRat;
	}

	public void setFiveRat(String fiveRat) {
		this.fiveRat = fiveRat;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public List<ProdReviewDtl> getReviewExtn() {
		return reviewExtn;
	}

	public void setReviewExtn(List<ProdReviewDtl> reviewExtn) {
		this.reviewExtn = reviewExtn;
	}

	public String getComDesc() {
		return comDesc;
	}

	public void setComDesc(String comDesc) {
		this.comDesc = comDesc;
	}

	public String getComLongDesc() {
		return comLongDesc;
	}

	public void setComLongDesc(String comLongDesc) {
		this.comLongDesc = comLongDesc;
	}

	public String getOriginalRate() {
		return originalRate;
	}

	public void setOriginalRate(String originalRate) {
		this.originalRate = originalRate;
	}

	public String getActualRate() {
		return actualRate;
	}

	public void setActualRate(String actualRate) {
		this.actualRate = actualRate;
	}

	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public List<ProdExtnDtl> getProdExtn() {
		return prodExtn;
	}

	public void setProdExtn(List<ProdExtnDtl> prodExtn) {
		this.prodExtn = prodExtn;
	}

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public List<OrderInCartVarSummary> getOrderSumm() {
		return orderSumm;
	}

	public void setOrderSumm(List<OrderInCartVarSummary> orderSumm) {
		this.orderSumm = orderSumm;
	}

	public String getBmDesc() {
		return bmDesc;
	}

	public void setBmDesc(String bmDesc) {
		this.bmDesc = bmDesc;
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

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDtlDesc() {
		return dtlDesc;
	}

	public void setDtlDesc(String dtlDesc) {
		this.dtlDesc = dtlDesc;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrimaryuom() {
		return primaryuom;
	}

	public void setPrimaryuom(String primaryuom) {
		this.primaryuom = primaryuom;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getUomDesc() {
		return uomDesc;
	}

	public void setUomDesc(String uomDesc) {
		this.uomDesc = uomDesc;
	}

	public String getNoOfImages() {
		return noOfImages;
	}

	public void setNoOfImages(String noOfImages) {
		this.noOfImages = noOfImages;
	}

}
