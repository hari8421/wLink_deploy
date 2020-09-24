package com.ecom.domain;

import java.io.Serializable;

public class ProdSingleCmbDtl implements Serializable {

	private static final long serialVersionUID = -1L;

	private String originalRate;
	private String discPerc;
	private String discRate;
	private String actRate;
	private String cmbDesc;
	private String pchid;
	private String dtlDesc;

	public String getOriginalRate() {
		return originalRate;
	}

	public void setOriginalRate(String originalRate) {
		this.originalRate = originalRate;
	}

	public String getDiscPerc() {
		return discPerc;
	}

	public void setDiscPerc(String discPerc) {
		this.discPerc = discPerc;
	}

	public String getDiscRate() {
		return discRate;
	}

	public void setDiscRate(String discRate) {
		this.discRate = discRate;
	}

	public String getActRate() {
		return actRate;
	}

	public void setActRate(String actRate) {
		this.actRate = actRate;
	}

	public String getCmbDesc() {
		return cmbDesc;
	}

	public void setCmbDesc(String cmbDesc) {
		this.cmbDesc = cmbDesc;
	}

	public String getPchid() {
		return pchid;
	}

	public void setPchid(String pchid) {
		this.pchid = pchid;
	}

	public String getDtlDesc() {
		return dtlDesc;
	}

	public void setDtlDesc(String dtlDesc) {
		this.dtlDesc = dtlDesc;
	}

}
