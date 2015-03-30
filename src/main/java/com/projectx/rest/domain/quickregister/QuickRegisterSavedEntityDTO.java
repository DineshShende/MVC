package com.projectx.rest.domain.quickregister;

public class QuickRegisterSavedEntityDTO {

	private String status;
	
	private QuickRegisterDTO customer;

	public QuickRegisterSavedEntityDTO() {
		super();
	}

	public QuickRegisterSavedEntityDTO(String status, QuickRegisterDTO customer) {
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

	@Override
	public String toString() {
		return "QuickRegisterSavedEntityDTO [status=" + status + ", customer="
				+ customer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuickRegisterSavedEntityDTO other = (QuickRegisterSavedEntityDTO) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

		
	
}
