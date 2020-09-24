package com.ecom.jpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="customer_details_extn")
public class CustomerDtlExtentionJpaEntity  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CDE_ID")
	private long cdeId;
    @Column(name="CD_ID")
	private String cdId;
    @Column(name="DELIVERY_CONTACT_NAME")
	private String deliveryContactName;
    @Column(name="DELIVERY_ADDRESS_LINE_I")
   	private String address1;
    @Column(name="DELIVERY_ADDRESS_LINE_II")
   	private String address2;
    @Column(name = "DELIVERY_CONTACT_NO")
    private String contactNo;
    
    @Column(name = "PINCODE")
    private String pinCode;
   
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public long getCdeId() {
		return cdeId;
	}
	public void setCdeId(long cdeId) {
		this.cdeId = cdeId;
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
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
    
 
	

}
