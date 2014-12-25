package com.projectx.rest.domain.quickregister;

public class QuickRegisterStringStatusDTO {
	
	private String status;
	
	private QuickRegisterDTO customer;

	
	
	public QuickRegisterStringStatusDTO() {
		super();
	}

	public QuickRegisterStringStatusDTO(String status,
			QuickRegisterDTO customer) {
		super();
		this.status = status;
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public QuickRegisterDTO getCustomer() {
		return customer;
	}

	public void setCustomer(QuickRegisterDTO customer) {
		this.customer = customer;
	}
	
	
	

}
