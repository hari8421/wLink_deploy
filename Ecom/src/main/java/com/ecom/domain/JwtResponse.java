package com.ecom.domain;

import java.io.Serializable;
import java.util.List;



public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private  String jwttoken ;
	private String returnMsg;
	private List<UserDtls> ul;

	
	public List<UserDtls> getUl() {
		return ul;
	}



	public void setUl(List<UserDtls> ul) {
		this.ul = ul;
	}



	public String getReturnMsg() {
		return returnMsg;
	}



	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}



	public String getJwttoken() {
		return jwttoken;
	}



	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}



	
}