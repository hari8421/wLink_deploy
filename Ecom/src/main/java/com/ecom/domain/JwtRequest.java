package com.ecom.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	@NotBlank(message = "Enter valid User name")
	@Pattern(message = "Enter a valid mobile number", regexp="(^$|[0-9]{10})")
	private String username;
	@NotBlank(message = "Enter valid Password")
	private String password;
	private String otp;
	private String vendorId;
	private String isOtp;
	
	public String getIsOtp() {
		return isOtp;
	}
   public void setIsOtp(String isOtp) {
		this.isOtp = isOtp;
	}

	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}



	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public JwtRequest(String username, String password,String isOtp,String vendorId,String otp) {
		this.setUsername(username);
		this.setPassword(password);
		this.setIsOtp(isOtp);
		this.setOtp(otp);
		this.setVendorId(vendorId);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}