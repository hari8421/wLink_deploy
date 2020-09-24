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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_combo_dtl")
public class PcDtlJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PCD_ID")
	private long pcdId;
	@Column(name = "PROD_ID")
	private long prodId;
	@Column(name = "PS_ID")
	private long psId;
	@Column(name = "PCH_ID")
	private long pcHdrId;
	@Column(name = "QTY")
	private long qty;
	@Column(name = "IS_ACTIVE")
	private long isActive;
	@Column(name = "CREATED_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date createdDate;
	@Column(name = "LAST_UPDATED_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date updatedDate;

	@ManyToOne
	@JoinColumn(name = "PCH_ID", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private PcHdrJpaEntity pchEntity;

	@OneToMany(mappedBy = "pcDtlJpaEntity")
	private List<ProductSubHdrJpsEntity> productSubHdrJpsEntity;

	public List<ProductSubHdrJpsEntity> getProductSubHdrJpsEntity() {
		return productSubHdrJpsEntity;
	}

	public void setProductSubHdrJpsEntity(List<ProductSubHdrJpsEntity> productSubHdrJpsEntity) {
		this.productSubHdrJpsEntity = productSubHdrJpsEntity;
	}

	public long getPcdId() {
		return pcdId;
	}

	public void setPcdId(long pcdId) {
		this.pcdId = pcdId;
	}

	public long getProdId() {
		return prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	public long getPsId() {
		return psId;
	}

	public void setPsId(long psId) {
		this.psId = psId;
	}

	public long getPcHdrId() {
		return pcHdrId;
	}

	public void setPcHdrId(long pcHdrId) {
		this.pcHdrId = pcHdrId;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public long getIsActive() {
		return isActive;
	}

	public void setIsActive(long isActive) {
		this.isActive = isActive;
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

	public PcHdrJpaEntity getPchEntity() {
		return pchEntity;
	}

	public void setPchEntity(PcHdrJpaEntity pchEntity) {
		this.pchEntity = pchEntity;
	}

}
