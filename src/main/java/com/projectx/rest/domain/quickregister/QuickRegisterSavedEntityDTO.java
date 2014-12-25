package com.projectx.rest.domain.quickregister;

public class QuickRegisterSavedEntityDTO {

	private Boolean status;
	
	private QuickRegisterDTO customer;

	public QuickRegisterSavedEntityDTO() {
		super();
	}

	public QuickRegisterSavedEntityDTO(Boolean status,
			QuickRegisterDTO customer) {
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

	public QuickRegisterDTO getCustomer() {
		return customer;
	}

	public void setCustomer(QuickRegisterDTO customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerQuickRegisterSavedEntityDTO [status=" + status
				+ ", customer=" + customer + "]";
	}
	
	
	
	
}
