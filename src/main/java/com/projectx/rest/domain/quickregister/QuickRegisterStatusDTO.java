package com.projectx.rest.domain.quickregister;

public class QuickRegisterStatusDTO {
	
	private String status;
	
	private QuickRegisterDTO customer;

	
	
	public QuickRegisterStatusDTO() {
		super();
	}

	public QuickRegisterStatusDTO(String status,
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
