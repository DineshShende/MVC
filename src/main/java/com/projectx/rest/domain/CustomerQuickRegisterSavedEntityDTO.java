package com.projectx.rest.domain;

public class CustomerQuickRegisterSavedEntityDTO {

	private Boolean status;
	
	private CustomerQuickRegisterDTO customer;

	public CustomerQuickRegisterSavedEntityDTO() {
		super();
	}

	public CustomerQuickRegisterSavedEntityDTO(Boolean status,
			CustomerQuickRegisterDTO customer) {
		super();
		this.status = status;
		this.customer = customer;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public CustomerQuickRegisterDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerQuickRegisterDTO customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerQuickRegisterSavedEntityDTO [status=" + status
				+ ", customer=" + customer + "]";
	}
	
	
	
	
}
