package com.ecom.jpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brand_mst")
public class BrandJpaEntity {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "BM_ID")
	    private Long bmId;
	  
	  @Column(name = "BM_CODE")
	    private String bmCode;
	  
	  @Column(name = "BM_DESC")
	    private String bmDesc;
	  
	  @Column(name = "IS_ACTIVE")
	    private String isActive;
	  @Column(name = "VENDOR_ID")
	    private String vendorId;
	  @Column(name = "IS_OWN_BRAND")
	    private String isOwnBrand;
	public Long getBmId() {
		return bmId;
	}
	public void setBmId(Long bmId) {
		this.bmId = bmId;
	}
	public String getBmCode() {
		return bmCode;
	}
	public void setBmCode(String bmCode) {
		this.bmCode = bmCode;
	}
	public String getBmDesc() {
		return bmDesc;
	}
	public void setBmDesc(String bmDesc) {
		this.bmDesc = bmDesc;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getIsOwnBrand() {
		return isOwnBrand;
	}
	public void setIsOwnBrand(String isOwnBrand) {
		this.isOwnBrand = isOwnBrand;
	}
	 
	
	  
}
