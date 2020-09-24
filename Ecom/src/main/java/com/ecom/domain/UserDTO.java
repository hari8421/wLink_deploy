package com.ecom.domain;

public class UserDTO {
	private String LOGIN_USERNAME;
	private String LOGIN_PASSWORD;
	private String IS_ENABLED;
	private String organization;
	public String getIS_ENABLED() {
		return IS_ENABLED;
	}
	public void setIS_ENABLED(String iS_ENABLED) {
		IS_ENABLED = iS_ENABLED;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getLOGIN_USERNAME() {
		return LOGIN_USERNAME;
	}
	public void setLOGIN_USERNAME(String lOGIN_USERNAME) {
		LOGIN_USERNAME = lOGIN_USERNAME;
	}
	public String getLOGIN_PASSWORD() {
		return LOGIN_PASSWORD;
	}
	public void setLOGIN_PASSWORD(String lOGIN_PASSWORD) {
		LOGIN_PASSWORD = lOGIN_PASSWORD;
	}

	
}