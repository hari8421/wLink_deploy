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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "order_information_hdr")
public class OrderHdrJpaEntity {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "OI_ID")
	    private Long oiD;
	  
	  @Column(name = "CD_ID")
	    private String cdId;
	  
	  @Column(name = "CDE_ID")
	    private String cdeId;
	  
	  @Column(name = "ORDER_CODE")
	    private String oCode;
	  @Column(name = "VENDOR_ID")
	    private String vendorId;
	  @Column(name = "ORDER_STATUS")
	    private String orderStatus;
	  @Temporal(TemporalType.TIMESTAMP)
	  @Column(name = "ORDER_DATETIME")
	    private java.util.Date orderDate;
	  @Column(name = "PLANNED_DELIVERY_DATE")
	  private String plannedDeliveryDate;
	  
	  @Column(name = "ACTUAL_DELIVERY_DATE")
	    private String actualDeliveryDate;
	  @Column(name = "IS_DELAYED")
	    private int isDelayed;
	  @Column(name = "DELAYED_REASON")
	    private String delayedReason;
	  @Column(name = "CUSTOMER_AUTHENTICATION_URL")
	    private String customerUrl;
	  @Column(name = "IS_CANCELLED")
	    private int isCancelled;
	  @Column(name = "CANCELLED_REASON")
	    private String cancelledReason;
	  @Column(name = "IS_DISCOUNT")
	    private int isDiscount;
	  @Column(name = "DC_ID")
	    private Long dcId;
	  @Column(name = "DISCOUNTED_PRICE")
	    private Long discountedPrice;
	  @Column(name = "COMBO_PRICE")
	    private Long comboPrice;
	  @Column(name = "GROSS_PRICE")
	    private Long grossPrice;
	  @Column(name = "NETT_PRICE")
	    private Long nettPrice;
	  @Column(name = "CREATED_DATETIME")
	  private String createdDtime;
	  @Column(name = "UPDATED_DATETIME")
	  private String updateDtime;
	  
	  @OneToMany(mappedBy = "oHdrJpaEntity")
	   private List<OrderDtlJpaEntity> odDtlEntity;

	  @ManyToOne
	  @Fetch(FetchMode.JOIN)
	    @JoinColumn(name = "CDE_ID", nullable = true, updatable = false,insertable = false)
	    private CustomerDtlExtentionJpaEntity cdeEntity;
	  
	  
	  
	  
	  
	public CustomerDtlExtentionJpaEntity getCdeEntity() {
		return cdeEntity;
	}
	public void setCdeEntity(CustomerDtlExtentionJpaEntity cdeEntity) {
		this.cdeEntity = cdeEntity;
	}
	public List<OrderDtlJpaEntity> getOdDtlEntity() {
		return odDtlEntity;
	}
	public void setOdDtlEntity(List<OrderDtlJpaEntity> odDtlEntity) {
		this.odDtlEntity = odDtlEntity;
	}
	public Long getOiD() {
		return oiD;
	}
	public void setOiD(Long oiD) {
		this.oiD = oiD;
	}
	public String getCdId() {
		return cdId;
	}
	public void setCdId(String cdId) {
		this.cdId = cdId;
	}
	public String getCdeId() {
		return cdeId;
	}
	public void setCdeId(String cdeId) {
		this.cdeId = cdeId;
	}
	public String getoCode() {
		return oCode;
	}
	public void setoCode(String oCode) {
		this.oCode = oCode;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public java.util.Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getPlannedDeliveryDate() {
		return plannedDeliveryDate;
	}
	public void setPlannedDeliveryDate(String plannedDeliveryDate) {
		this.plannedDeliveryDate = plannedDeliveryDate;
	}
	public String getActualDeliveryDate() {
		return actualDeliveryDate;
	}
	public void setActualDeliveryDate(String actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}
	public int getIsDelayed() {
		return isDelayed;
	}
	public void setIsDelayed(int isDelayed) {
		this.isDelayed = isDelayed;
	}
	public String getDelayedReason() {
		return delayedReason;
	}
	public void setDelayedReason(String delayedReason) {
		this.delayedReason = delayedReason;
	}
	public String getCustomerUrl() {
		return customerUrl;
	}
	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}
	public int getIsCancelled() {
		return isCancelled;
	}
	public void setIsCancelled(int isCancelled) {
		this.isCancelled = isCancelled;
	}
	public String getCancelledReason() {
		return cancelledReason;
	}
	public void setCancelledReason(String cancelledReason) {
		this.cancelledReason = cancelledReason;
	}
	public int getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(int isDiscount) {
		this.isDiscount = isDiscount;
	}
	public Long getDcId() {
		return dcId;
	}
	public void setDcId(Long dcId) {
		this.dcId = dcId;
	}
	public Long getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(Long discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public Long getComboPrice() {
		return comboPrice;
	}
	public void setComboPrice(Long comboPrice) {
		this.comboPrice = comboPrice;
	}
	public Long getGrossPrice() {
		return grossPrice;
	}
	public void setGrossPrice(Long grossPrice) {
		this.grossPrice = grossPrice;
	}
	public Long getNettPrice() {
		return nettPrice;
	}
	public void setNettPrice(Long nettPrice) {
		this.nettPrice = nettPrice;
	}
	public String getCreatedDtime() {
		return createdDtime;
	}
	public void setCreatedDtime(String createdDtime) {
		this.createdDtime = createdDtime;
	}
	public String getUpdateDtime() {
		return updateDtime;
	}
	public void setUpdateDtime(String updateDtime) {
		this.updateDtime = updateDtime;
	}
	  
	  
	  
}
