package com.ecom.jpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "admin_login_mst")
public class AdminJpaEntity {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ADL_ID")
	    private Long adlId;
	  
	  @Column(name = "EMAIL_ID")
	    private String email;
	  
	  @Column(name = "CONTACT_NO")
	    private String contactNo;
	  
	  @Column(name = "PASSWORD")
	    private String passWord;
	  @Column(name = "VENDOR_ID")
	    private long vendorId;
	  @Column(name = "IS_ACTIVE")
	    private int isActive;
	  
	  @ManyToOne
	  @Fetch(FetchMode.JOIN)
	    @JoinColumn(name = "VENDOR_ID", updatable = false,insertable = false)
	    private VendorBranchJpaEntity vbEntity;
	  
	  
	  
	  
	public VendorBranchJpaEntity getVbEntity() {
		return vbEntity;
	}
	public void setVbEntity(VendorBranchJpaEntity vbEntity) {
		this.vbEntity = vbEntity;
	}
	public Long getAdlId() {
		return adlId;
	}
	public void setAdlId(Long adlId) {
		this.adlId = adlId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	  
	  
	
}
