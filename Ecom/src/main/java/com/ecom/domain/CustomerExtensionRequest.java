package com.ecom.domain;

public class CustomerExtensionRequest {

	private String cdId;
	private String deliveryContactName;
	private String deliveryContactNo;
	private String deliveryAddressLine1;
	private String deliveryAddressLine2;
	private String pdcId;
	private String pdsId;
	private String pdmId;
	private String pincode;
	private String primary;
	private String vendorId;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getCdId() {
		return cdId;
	}

	public void setCdId(String cdId) {
		this.cdId = cdId;
	}

	public String getDeliveryContactName() {
		return deliveryContactName;
	}

	public void setDeliveryContactName(String deliveryContactName) {
		this.deliveryContactName = deliveryContactName;
	}

	public String getDeliveryContactNo() {
		return deliveryContactNo;
	}

	public void setDeliveryContactNo(String deliveryContactNo) {
		this.deliveryContactNo = deliveryContactNo;
	}

	public String getDeliveryAddressLine1() {
		return deliveryAddressLine1;
	}

	public void setDeliveryAddressLine1(String deliveryAddressLine1) {
		this.deliveryAddressLine1 = deliveryAddressLine1;
	}

	public String getDeliveryAddressLine2() {
		return deliveryAddressLine2;
	}

	public void setDeliveryAddressLine2(String deliveryAddressLine2) {
		this.deliveryAddressLine2 = deliveryAddressLine2;
	}

	public String getPdcId() {
		return pdcId;
	}

	public void setPdcId(String pdcId) {
		this.pdcId = pdcId;
	}

	public String getPdsId() {
		return pdsId;
	}

	public void setPdsId(String pdsId) {
		this.pdsId = pdsId;
	}

	public String getPdmId() {
		return pdmId;
	}

	public void setPdmId(String pdmId) {
		this.pdmId = pdmId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

}