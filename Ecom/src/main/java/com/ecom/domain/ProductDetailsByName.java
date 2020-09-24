package com.ecom.domain;

import java.util.List;

public class ProductDetailsByName {

	private List<ProductComboHdr> pcList;
	private List<ProductHdrDtl> pmList;
	public List<ProductComboHdr> getPcList() {
		return pcList;
	}
	public void setPcList(List<ProductComboHdr> pcList) {
		this.pcList = pcList;
	}
	public List<ProductHdrDtl> getPmList() {
		return pmList;
	}
	public void setPmList(List<ProductHdrDtl> pmList) {
		this.pmList = pmList;
	}
	
}
