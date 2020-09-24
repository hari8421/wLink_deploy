package com.ecom.requestEntity;

import java.io.Serializable;

public class customerDeviceDtl implements Serializable {

	private static final long serialVersionUID = -1L;
	private String vendorId;
	private String deviceId;
	private String deviceName;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

}
