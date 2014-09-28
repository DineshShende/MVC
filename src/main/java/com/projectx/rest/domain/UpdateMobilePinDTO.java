package com.projectx.rest.domain;

public class UpdateMobilePinDTO {

	private Long customerId;

	public UpdateMobilePinDTO() {
		super();
	}

	public UpdateMobilePinDTO(Long customerId) {
		super();
		this.customerId = customerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	
	
	
}
