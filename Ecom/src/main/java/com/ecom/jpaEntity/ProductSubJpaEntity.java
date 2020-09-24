package com.ecom.jpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_sub_dtl")
public class ProductSubJpaEntity {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "PSD_ID")
	    private Long psdId;
	  
	  @Column(name = "PS_ID",insertable = false,updatable = false)
	    private String psId;
	  
	  @Column(name = "VARIENT_TYPE_CODE",insertable = false,updatable = false)
	    private String vTcode;
	  
	  
	  @ManyToOne
	    @JoinColumn(name = "VARIENT_TYPE_CODE", nullable = false, updatable = false)
	  @JsonIgnore
	    private VarientProd varientProd;
	  
	  @ManyToOne
	    @JoinColumn(name = "PS_ID", nullable = false, updatable = false)
	  @JsonIgnore
	    private ProductSubHdrJpsEntity productSubHdrJpsEntity;
	  
	  @Column(name = "VALUE")
	    private String value;

	public Long getPsdId() {
		return psdId;
	}

	public void setPsdId(Long psdId) {
		this.psdId = psdId;
	}

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public String getvTcode() {
		return vTcode;
	}

	public void setvTcode(String vTcode) {
		this.vTcode = vTcode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



	
	 
	
	  
}
