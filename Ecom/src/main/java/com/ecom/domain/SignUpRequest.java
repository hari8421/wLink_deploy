package com.ecom.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequest {
    
	@NotBlank(message = "Enter valid contact number")
	@Size(min=10,max=10 ,message="Enter valid contact number")
    private String contactNo;
	@NotNull(message = "Enter valid password")
    private String password;
    private String vendorId;
    private String name;
    

    

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
    
}