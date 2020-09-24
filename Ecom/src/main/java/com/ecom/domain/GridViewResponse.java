package com.ecom.domain;

import java.io.Serializable;
import java.util.List;

public class GridViewResponse implements Serializable{
	
	private static final long serialVersionUID = -1L;
    private List<ProductHdrDtl> prodList=null;
    private String catString;
    List<BrandDtl> bList=null;
    private String minMaxPrice;
    List<VarientDtlForGrid> vlList=null;
    
    
	public List<VarientDtlForGrid> getVlList() {
		return vlList;
	}
	public void setVlList(List<VarientDtlForGrid> vlList) {
		this.vlList = vlList;
	}
	public String getMinMaxPrice() {
		return minMaxPrice;
	}
	public void setMinMaxPrice(String minMaxPrice) {
		this.minMaxPrice = minMaxPrice;
	}
	public List<BrandDtl> getbList() {
		return bList;
	}
	public void setbList(List<BrandDtl> bList) {
		this.bList = bList;
	}
	public String getCatString() {
		return catString;
	}
	public void setCatString(String catString) {
		this.catString = catString;
	}
	public List<ProductHdrDtl> getProdList() {
		return prodList;
	}
	public void setProdList(List<ProductHdrDtl> prodList) {
		this.prodList = prodList;
	}
    
    
}