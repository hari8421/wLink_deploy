package com.ecom.requestEntity;

import java.io.Serializable;

public class insertProductMstRequest implements Serializable {

	private static final long serialVersionUID = -1L;
	private String productCode;
	private String tags;
	private String productDesc;
	private String productName;
	private String varient;
	private String primCat;
	private String prodCat;
	private String brand;
	private String vendorId;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPrimCat() {
		return primCat;
	}

	public void setPrimCat(String primCat) {
		this.primCat = primCat;
	}

	public String getProdCat() {
		return prodCat;
	}

	public void setProdCat(String prodCat) {
		this.prodCat = prodCat;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVarient() {
		return varient;
	}

	public void setVarient(String varient) {
		this.varient = varient;
	}

}
