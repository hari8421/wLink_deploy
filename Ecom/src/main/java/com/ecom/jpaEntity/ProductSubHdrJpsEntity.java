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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_sub_hdr")
public class ProductSubHdrJpsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PS_ID")
	private Long psId;
	@Column(name = "PROD_ID", insertable = false, updatable = false)
	private String prodId;

	@ManyToOne
	@JoinColumn(name = "PROD_ID", nullable = false, updatable = false)
	@JsonIgnore
	private ProductJpaEntity productJpaEntity;

	
	  @ManyToOne
	  
	  @JoinColumn(name = "PS_ID", nullable = false,insertable = false, updatable =
	  false)
	  
	  @JsonIgnore private PcDtlJpaEntity pcDtlJpaEntity;
	 

	@OneToMany(mappedBy = "productSubHdrJpsEntity")
	private List<ProductSubJpaEntity> productSubJpaEntity;

	@Column(name = "DTL_DESC")
	private String dtlDesc;

	@Column(name = "PRIMARY_UOM")
	private String pUom;
	@Column(name = "SECONDARY_UOM")
	private String sUom;
	@Column(name = "CONVERSION_RATE")
	private String cRate;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "PRICE")
	private String prce;
	@Column(name = "CREATED_DATETIME")
	private String cDtime;
	@Column(name = "LAST_UPDATED_DATETIME")
	private String lDtime;
	@Column(name = "RATING")
	private String rating;
	@Column(name = "VENDOR_ID")
	private String vId;

	public Long getPsId() {
		return psId;
	}

	public void setPsId(Long psId) {
		this.psId = psId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getDtlDesc() {
		return dtlDesc;
	}

	public void setDtlDesc(String dtlDesc) {
		this.dtlDesc = dtlDesc;
	}

	public String getpUom() {
		return pUom;
	}

	public void setpUom(String pUom) {
		this.pUom = pUom;
	}

	public String getsUom() {
		return sUom;
	}

	public void setsUom(String sUom) {
		this.sUom = sUom;
	}

	public String getcRate() {
		return cRate;
	}

	public void setcRate(String cRate) {
		this.cRate = cRate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrce() {
		return prce;
	}

	public void setPrce(String prce) {
		this.prce = prce;
	}

	public String getcDtime() {
		return cDtime;
	}

	public void setcDtime(String cDtime) {
		this.cDtime = cDtime;
	}

	public String getlDtime() {
		return lDtime;
	}

	public void setlDtime(String lDtime) {
		this.lDtime = lDtime;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getvId() {
		return vId;
	}

	public void setvId(String vId) {
		this.vId = vId;
	}

	public ProductJpaEntity getProductJpaEntity() {
		return productJpaEntity;
	}

	public void setProductJpaEntity(ProductJpaEntity productJpaEntity) {
		this.productJpaEntity = productJpaEntity;
	}

	public List<ProductSubJpaEntity> getProductSubJpaEntity() {
		return productSubJpaEntity;
	}

	public void setProductSubJpaEntity(List<ProductSubJpaEntity> productSubJpaEntity) {
		this.productSubJpaEntity = productSubJpaEntity;
	}

}
