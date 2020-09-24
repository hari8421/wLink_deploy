package com.ecom.jpaEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductJpaEntity {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "PROD_ID")
	    private Long prodId;
	  
	  @Column(name = "BM_ID")
	    private String bmId;
	  
	  @Column(name = "PARENT_CATE_DTL_ID")
	    private String pcDtlId;
	  
	  @OneToMany(mappedBy = "productJpaEntity")
	    private List<ProductSubHdrJpsEntity> productSubHdrJpsEntity;
	  
	  @ManyToOne
	    @JoinColumn(name = "BM_ID", nullable = false, updatable = false,insertable = false)
	    private BrandJpaEntity brandJpaEntity;
	  
	  
	  
	  @Column(name = "PROD_CODE")
	    private String pCode;
	  @Column(name = "PROD_NAME")
	    private String pName;
	  @Column(name = "PROD_DESC")
	    private String pDesc;
	  @Column(name = "TAGS")
	    private String pTag;
	  @Column(name = "IS_ACTIVE")
	    private String isActive;
	  @Column(name = "VENDOR_ID")
	    private String vId;
	  @Column(name = "CREATED_DATETIME")
	    private String cDtime;
	  @Column(name = "PARENT_CATE_HDR_ID")
	    private String pcHdrId;
	public Long getProdId() {
		return prodId;
	}
	public void setTicketPriceId(Long prodId) {
		this.prodId = prodId;
	}
	public String getBmId() {
		return bmId;
	}
	public void setBmId(String bmId) {
		this.bmId = bmId;
	}
	public String getPcDtlId() {
		return pcDtlId;
	}
	public void setPcDtlId(String pcDtlId) {
		this.pcDtlId = pcDtlId;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDesc() {
		return pDesc;
	}
	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}
	public String getpTag() {
		return pTag;
	}
	public void setpTag(String pTag) {
		this.pTag = pTag;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getvId() {
		return vId;
	}
	public void setvId(String vId) {
		this.vId = vId;
	}
	public String getcDtime() {
		return cDtime;
	}
	public void setcDtime(String cDtime) {
		this.cDtime = cDtime;
	}
	public String getPcHdrId() {
		return pcHdrId;
	}
	public void setPcHdrId(String pcHdrId) {
		this.pcHdrId = pcHdrId;
	}

	public List<ProductSubHdrJpsEntity> getProductSubHdrJpsEntity() {
		return productSubHdrJpsEntity;
	}
	public void setProductSubHdrJpsEntity(List<ProductSubHdrJpsEntity> productSubHdrJpsEntity) {
		this.productSubHdrJpsEntity = productSubHdrJpsEntity;
	}
	public BrandJpaEntity getBrandJpaEntity() {
		return brandJpaEntity;
	}
	public void setBrandJpaEntity(BrandJpaEntity brandJpaEntity) {
		this.brandJpaEntity = brandJpaEntity;
	}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	  
	  
	  
}
