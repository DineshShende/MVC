package com.projectx.rest.domain;

public class CustomerQuickRegisterStringStatusDTO {
	
	private String status;
	
	private CustomerQuickRegisterDTO customer;

	
	
	public CustomerQuickRegisterStringStatusDTO() {
		super();
	}

	public CustomerQuickRegisterStringStatusDTO(String status,
			CustomerQuickRegisterDTO customer) {
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

	public CustomerQuickRegisterDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerQuickRegisterDTO customer) {
		this.customer = customer;
	}
	
	
	

}
