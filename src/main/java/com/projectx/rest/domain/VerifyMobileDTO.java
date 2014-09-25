package com.projectx.rest.domain;

public class VerifyMobileDTO {
	
	Long customerId;
	
	Integer mobilePin;
	

	public VerifyMobileDTO() {
	
	}


	public VerifyMobileDTO(Long customerId, Integer mobilePin) {
		super();
		this.customerId = customerId;
		this.mobilePin = mobilePin;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Integer getMobilePin() {
		return mobilePin;
	}


	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}


	@Override
	public String toString() {
		return "VerifyMobileDTO [customerId=" + customerId + ", mobilePin="
				+ mobilePin + "]";
	}
	
	
	

}
