package com.ecom.jpaEntity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="product_combo_hdr")
public class PcHdrJpaEntity  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PCH_ID")
	private long pcHdrId;
    @Column(name="COM_CODE")
	private String comCode;
    @Column(name="VENDOR_ID")
	private String vendorId;
    @Column(name="COM_DESC")
   	private String comDesc;
    @Column(name="TAGS")
   	private String tags;
    @Column(name = "EFFECTIVE_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date effectiveStartDate;
    @Column(name = "EFFECTIVE_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date effectiveEndDate;
    @Column(name="IS_ACTIVE")
	private long isActive ;
    @Column(name="ORIGINAL_RATE")
   	private BigDecimal orginalRate ;
    @Column(name="DISCOUNT_PERCENTAGE")
   	private BigDecimal discoutPercent ;
    @Column(name="DISCOUNT_RATE")
   	private BigDecimal discoutRate ;
    @Column(name="ACTUAL_RATE")
   	private BigDecimal actualRate;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createdDate;
    @Column(name = "LAST_UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updatedDate;             
    @Column(name="PC_TYPE")
   	private String pcType;
    @Column(name="COM_LONG_DESC")
   	private String comLongDesc;
    
    @OneToMany(mappedBy = "pchEntity")
    private List<PcDtlJpaEntity> pcDtlEntity;

    
	public long getPcHdrId() {
		return pcHdrId;
	}

	public void setPcHdrId(long pcHdrId) {
		this.pcHdrId = pcHdrId;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getComDesc() {
		return comDesc;
	}

	public void setComDesc(String comDesc) {
		this.comDesc = comDesc;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public java.util.Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(java.util.Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public java.util.Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(java.util.Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public long getIsActive() {
		return isActive;
	}

	public void setIsActive(long isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getOrginalRate() {
		return orginalRate;
	}

	public void setOrginalRate(BigDecimal orginalRate) {
		this.orginalRate = orginalRate;
	}

	public BigDecimal getDiscoutPercent() {
		return discoutPercent;
	}

	public void setDiscoutPercent(BigDecimal discoutPercent) {
		this.discoutPercent = discoutPercent;
	}

	public BigDecimal getDiscoutRate() {
		return discoutRate;
	}

	public void setDiscoutRate(BigDecimal discoutRate) {
		this.discoutRate = discoutRate;
	}

	public BigDecimal getActualRate() {
		return actualRate;
	}

	public void setActualRate(BigDecimal actualRate) {
		this.actualRate = actualRate;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getPcType() {
		return pcType;
	}

	public void setPcType(String pcType) {
		this.pcType = pcType;
	}

	public String getComLongDesc() {
		return comLongDesc;
	}

	public void setComLongDesc(String comLongDesc) {
		this.comLongDesc = comLongDesc;
	}

	public List<PcDtlJpaEntity> getPcDtlEntity() {
		return pcDtlEntity;
	}

	public void setPcDtlEntity(List<PcDtlJpaEntity> pcDtlEntity) {
		this.pcDtlEntity = pcDtlEntity;
	}
    
    
    
 
	

}
