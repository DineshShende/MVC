package com.projectx.rest.domain.ang;

public class CustomerIdTypeEmailOrMobileOptionUpdatedByAng {
	
	private Long customerId;
	
	private Integer customerType;
	
	private Integer emailOrMobile;
	
	
	private Integer requestedBy;
	
	private Long requestedById;

	
	
	public CustomerIdTypeEmailOrMobileOptionUpdatedByAng() {
		super();
	}



	public CustomerIdTypeEmailOrMobileOptionUpdatedByAng(Long customerId,
			Integer customerType, Integer emailOrMobile, Integer requestedBy,
			Long requestedById) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.emailOrMobile = emailOrMobile;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}



	public Long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	public Integer getCustomerType() {
		return customerType;
	}



	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}



	public Integer getEmailOrMobile() {
		return emailOrMobile;
	}



	public void setEmailOrMobile(Integer emailOrMobile) {
		this.emailOrMobile = emailOrMobile;
	}



	public Integer getRequestedBy() {
		return requestedBy;
	}



	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}



	public Long getRequestedById() {
		return requestedById;
	}



	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
	}



	@Override
	public String toString() {
		return "CustomerIdTypeEmailOrMobileOptionUpdatedByAng [customerId="
				+ customerId + ", customerType=" + customerType
				+ ", emailOrMobile=" + emailOrMobile + ", requestedBy="
				+ requestedBy + ", requestedById=" + requestedById + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result
				+ ((emailOrMobile == null) ? 0 : emailOrMobile.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
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
		CustomerIdTypeEmailOrMobileOptionUpdatedByAng other = (CustomerIdTypeEmailOrMobileOptionUpdatedByAng) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerType == null) {
			if (other.customerType != null)
				return false;
		} else if (!customerType.equals(other.customerType))
			return false;
		if (emailOrMobile == null) {
			if (other.emailOrMobile != null)
				return false;
		} else if (!emailOrMobile.equals(other.emailOrMobile))
			return false;
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		} else if (!requestedById.equals(other.requestedById))
			return false;
		return true;
	}
	

	

}
