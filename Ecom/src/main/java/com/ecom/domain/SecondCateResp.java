package com.ecom.domain;

import java.io.Serializable;
import java.util.List;

public class SecondCateResp implements Serializable {

	private static final long serialVersionUID = -1L;
	private String prodCatCode;
	private String prodCatDesc;
	List<SecondCateResp> subList;
	private String dtlId;

	public String getDtlId() {
		return dtlId;
	}

	public void setDtlId(String dtlId) {
		this.dtlId = dtlId;
	}

	public List<SecondCateResp> getSubList() {
		return subList;
	}

	public void setSubList(List<SecondCateResp> subList) {
		this.subList = subList;
	}

	public String getProdCatCode() {
		return prodCatCode;
	}

	public void setProdCatCode(String prodCatCode) {
		this.prodCatCode = prodCatCode;
	}

	public String getProdCatDesc() {
		return prodCatDesc;
	}

	public void setProdCatDesc(String prodCatDesc) {
		this.prodCatDesc = prodCatDesc;
	}

}
