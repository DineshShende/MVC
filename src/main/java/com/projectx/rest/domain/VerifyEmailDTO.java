package com.projectx.rest.domain;

public class VerifyEmailDTO {
	
	private Long customerId;
	private String emailHash;
	
	public VerifyEmailDTO() {
		super();
	}


	public VerifyEmailDTO(Long customerId, String emailHash) {
		super();
		this.customerId = customerId;
		this.emailHash = emailHash;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getEmailHash() {
		return emailHash;
	}


	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	
	
	
	
	

}
