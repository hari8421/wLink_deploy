package com.ecom.domain;

import java.io.Serializable;

public class CustomerMasterDtl implements Serializable {

	private static final long serialVersionUID = -1L;
	private String customerID;
	private String customerName;
	private String phNo;
	private String addresslineI;
	private String addresslineII;
	private String country;
	private String state;
	private String district;
	private String refId;
	private String isPrimary;

	public String getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public String getAddresslineI() {
		return addresslineI;
	}

	public void setAddresslineI(String addresslineI) {
		this.addresslineI = addresslineI;
	}

	public String getAddresslineII() {
		return addresslineII;
	}

	public void setAddresslineII(String addresslineII) {
		this.addresslineII = addresslineII;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
