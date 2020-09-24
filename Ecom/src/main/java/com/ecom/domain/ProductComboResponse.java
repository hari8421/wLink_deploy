package com.ecom.domain;

import java.io.Serializable;
import java.util.List;



public class ProductComboResponse implements Serializable{

	private static final long serialVersionUID = -1L;
	
	ProductComboHdr phList=null;
	List<ProductComboDtl> pdList=null;
	
	public ProductComboHdr getPhList() {
		return phList;
	}
	public void setPhList(ProductComboHdr phList) {
		this.phList = phList;
	}
	public List<ProductComboDtl> getPdList() {
		return pdList;
	}
	public void setPdList(List<ProductComboDtl> pdList) {
		this.pdList = pdList;
	}
	
	
	
	
	
	
}
