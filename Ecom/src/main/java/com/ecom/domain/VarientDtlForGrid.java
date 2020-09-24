package com.ecom.domain;

import java.io.Serializable;
import java.util.List;

public class VarientDtlForGrid implements Serializable {

	private static final long serialVersionUID = -1L;
	
	private String varientTypeCode;
	private String varientTypeDesc;
	List<VarientValueDtl> vlList=null;
	public String getVarientTypeCode() {
		return varientTypeCode;
	}
	public void setVarientTypeCode(String varientTypeCode) {
		this.varientTypeCode = varientTypeCode;
	}
	public String getVarientTypeDesc() {
		return varientTypeDesc;
	}
	public void setVarientTypeDesc(String varientTypeDesc) {
		this.varientTypeDesc = varientTypeDesc;
	}
	public List<VarientValueDtl> getVlList() {
		return vlList;
	}
	public void setVlList(List<VarientValueDtl> vlList) {
		this.vlList = vlList;
	}
	
	

}
