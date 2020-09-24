package com.ecom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class ProductComboDtl implements Serializable{

	private static final long serialVersionUID = -1L;
	 
	private String pcdId;
	private String pchId;
	private String prodId;
	private String psId;
	private String qty;
	private String isActive;
	private String prodCode;
	private String prodDesc;
	private String prodCatId;
	private List<ProductSubHdrDtl> psList=new ArrayList<>();
	
	
	public String getProdCatId() {
		return prodCatId;
	}
	public void setProdCatId(String prodCatId) {
		this.prodCatId = prodCatId;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	
	
	public List<ProductSubHdrDtl> getPsList() {
		return psList;
	}
	public void setPsList(List<ProductSubHdrDtl> psList) {
		this.psList = psList;
	}
	public String getPcdId() {
		return pcdId;
	}
	public void setPcdId(String pcdId) {
		this.pcdId = pcdId;
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
	public String getPsId() {
		return psId;
	}
	public void setPsId(String psId) {
		this.psId = psId;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
}
