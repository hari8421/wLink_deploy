package com.ecom.domain;

import java.io.Serializable;



public class UserDtls implements Serializable{

	private static final long serialVersionUID = -1L;
	private String cdId;
	private String primaryContact;
	private String primaryAddress1;
	private String primaryAddress2;
	private String name;
	private String clId;
	private String inCartCnt;
	
	
	public String getInCartCnt() {
		return inCartCnt;
	}
	public void setInCartCnt(String inCartCnt) {
		this.inCartCnt = inCartCnt;
	}
	public String getClId() {
		return clId;
	}
	public void setClId(String clId) {
		this.clId = clId;
	}
	public String getCdId() {
		return cdId;
	}
	public void setCdId(String cdId) {
		this.cdId = cdId;
	}
	public String getPrimaryContact() {
		return primaryContact;
	}
	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}
	public String getPrimaryAddress1() {
		return primaryAddress1;
	}
	public void setPrimaryAddress1(String primaryAddress1) {
		this.primaryAddress1 = primaryAddress1;
	}
	public String getPrimaryAddress2() {
		return primaryAddress2;
	}
	public void setPrimaryAddress2(String primaryAddress2) {
		this.primaryAddress2 = primaryAddress2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
