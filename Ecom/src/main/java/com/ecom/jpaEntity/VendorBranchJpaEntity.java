package com.ecom.jpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="vendor_branch_details")
public class VendorBranchJpaEntity  {


    @Id
    @Column(name="VENDOR_ID")
	private long vendorId;
    @Column(name="VENDOR_CODE")
	private String vendorCode;
    @Column(name="VENDOR_NAME")
	private String vendorName;
    @Column(name="PRIMARY_EMAIL")
	private String primaryEmail;
    @Column(name="PRIMARY_CONTACT_NO")
   	private String primaryContactNo;
    @Column(name="PRIMARY_ADDRESS_LINE_I")
   	private String primaryAddressLine1;
    @Column(name="PRIMARY_ADDRESS_LINE_II")
   	private String primaryAddressLine2;
    @Column(name="PDC_ID")
   	private String pcdId;
    @Column(name="PDS_ID")
   	private String pdsId;
    @Column(name="PDM_ID")
   	private String pdmId;
    @Column(name="PINCODE")
   	private String pinCode;
    @Column(name="IS_ACTIVE")
   	private int isActive;
    @Column(name="SUBSCRIPTION_ENDING_DATE")
   	private String subEndingDate;
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getPrimaryContactNo() {
		return primaryContactNo;
	}
	public void setPrimaryContactNo(String primaryContactNo) {
		this.primaryContactNo = primaryContactNo;
	}
	public String getPrimaryAddressLine1() {
		return primaryAddressLine1;
	}
	public void setPrimaryAddressLine1(String primaryAddressLine1) {
		this.primaryAddressLine1 = primaryAddressLine1;
	}
	public String getPrimaryAddressLine2() {
		return primaryAddressLine2;
	}
	public void setPrimaryAddressLine2(String primaryAddressLine2) {
		this.primaryAddressLine2 = primaryAddressLine2;
	}
	public String getPcdId() {
		return pcdId;
	}
	public void setPcdId(String pcdId) {
		this.pcdId = pcdId;
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
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getSubEndingDate() {
		return subEndingDate;
	}
	public void setSubEndingDate(String subEndingDate) {
		this.subEndingDate = subEndingDate;
	}
  
   
	

}
