package com.ecom.jpaEntity;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "order_information_dtl")
public class OrderDtlJpaEntity {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "OID_DTL")
	    private Long oiDtlId;
	  
	  @Column(name = "OI_ID")
	    private String oiD;
	  
	  @Column(name = "PCH_ID")
	    private String pchId;
	  @Column(name = "PS_ID")
	    private String psId; 
	  
	  @Column(name = "PROD_ID")
	    private String prodId;
	  @Column(name = "VENDOR_ID")
	    private String vendorId;
	  @Column(name = "PROD_CODE")
	    private String prodCode;
	  
	  @Column(name = "PROD_NAME")
	    private String prodName;
	  @Column(name = "PROD_DESC")
	    private String prodDesc;
	 
	  @Column(name = "SUB_DESC")
	    private String subDesc;
	 
	  @Column(name = "PRICE")
	    private Long price;
	  @Column(name = "QUANTITY")
	    private Long quantity;
	  
	  @OneToMany(mappedBy = "orderDtlJpaEntity")
	    private List<OrderVarientJpaEntity> odVarientEntity;
	  
	  @ManyToOne
	    @JoinColumn(name = "OI_ID", nullable = false, updatable = false,insertable = false)
	    private OrderHdrJpaEntity oHdrJpaEntity;
	  
	  @Transient
	  @JoinColumn(name = "PS_ID", nullable = false, updatable = false,insertable =
	  false) private Optional<ProductSubHdrJpsEntity> pshEntity;
	
	  @Transient
	  @JoinColumn(name = "PCH_ID", nullable = false, updatable = false,insertable =
	  false) private Optional<PcHdrJpaEntity> pcEntity;
	 
	
	 
	public List<OrderVarientJpaEntity> getOdVarientEntity() {
		return odVarientEntity;
	}
	public void setOdVarientEntity(List<OrderVarientJpaEntity> odVarientEntity) {
		this.odVarientEntity = odVarientEntity;
	}
	public Optional<ProductSubHdrJpsEntity> getPshEntity() {
		return pshEntity;
	}
	public void setPshEntity(Optional<ProductSubHdrJpsEntity> pshEntity) {
		this.pshEntity = pshEntity;
	}
	public Optional<PcHdrJpaEntity> getPcEntity() {
		return pcEntity;
	}
	public void setPcEntity(Optional<PcHdrJpaEntity> pcEntity) {
		this.pcEntity = pcEntity;
	}
	public String getPsId() {
		return psId;
	}
	public void setPsId(String psId) {
		this.psId = psId;
	}
	public Long getOiDtlId() {
		return oiDtlId;
	}
	public void setOiDtlId(Long oiDtlId) {
		this.oiDtlId = oiDtlId;
	}
	public String getOiD() {
		return oiD;
	}
	public void setOiD(String oiD) {
		this.oiD = oiD;
	}
	public String getPchId() {
		return pchId;
	}
	public void setPchId(String pchId) {
		this.pchId = pchId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getSubDesc() {
		return subDesc;
	}
	public void setSubDesc(String subDesc) {
		this.subDesc = subDesc;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	  
	  
	 
	  
	  
}
