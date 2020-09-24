package com.ecom.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "customer_login_dtl")
public class DAOUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long CL_ID;
	@Column
	private String EMAIL_ID;
	@Column
	@JsonIgnore
	private String PASSWORD;
	@Column
	@JsonIgnore
	private String CONTACT_NO;
	
	
	
	public String getCONTACT_NO() {
		return CONTACT_NO;
	}

	public void setCONTACT_NO(String cONTACT_NO) {
		CONTACT_NO = cONTACT_NO;
	}

	

	public String getEMAIL_ID() {
		return EMAIL_ID;
	}

	public void setEMAIL_ID(String eMAIL_ID) {
		EMAIL_ID = eMAIL_ID;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public long getCL_ID() {
		return CL_ID;
	}

	public void setCL_ID(long cL_ID) {
		CL_ID = cL_ID;
	}
	
	
	

}