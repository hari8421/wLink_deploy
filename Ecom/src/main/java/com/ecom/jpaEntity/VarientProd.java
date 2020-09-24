package com.ecom.jpaEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="product_varient_type")
public class VarientProd  {


    @Id
    @Column(name="VARIENT_TYPE_CODE")
	private String typeCode;
    @Column(name="VARIENT_TYPE_DESC")
	private String desc;
    @Column(name="VENDOR_ID")
	private String vendorId;
    
    @OneToMany(mappedBy = "varientProd")
    private List<ProductSubJpaEntity> productSubHdrJpsEntity;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	
	

}
