package com.projectx.rest.domain.quickregister;

public class VerifyMobileDTO {
	
	private Long customerId;
	
	private Integer customerType;
	
	private Integer mobileType;
	
	private Integer mobilePin;
	
	private Integer requestBy;
	
	private Long requestById;

	public VerifyMobileDTO() {
	
	}

	public VerifyMobileDTO(Long customerId, Integer customerType,
			Integer mobileType, Integer mobilePin, Integer requestBy,Long requestById) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.mobileType = mobileType;
		this.mobilePin = mobilePin;
		this.requestBy = requestBy;
		this.requestById=requestById;
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

	public Integer getMobileType() {
		return mobileType;
	}

	public void setMobileType(Integer mobileType) {
		this.mobileType = mobileType;
	}

	public Integer getMobilePin() {
		return mobilePin;
	}

	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}



	public Integer getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(Integer requestBy) {
		this.requestBy = requestBy;
	}

	public Long getRequestById() {
		return requestById;
	}

	public void setRequestById(Long requestById) {
		this.requestById = requestById;
	}

	@Override
	public String toString() {
		return "VerifyMobileDTO [customerId=" + customerId + ", customerType="
				+ customerType + ", mobileType=" + mobileType + ", mobilePin="
				+ mobilePin + ", requestBy=" + requestBy + ", requestById="
				+ requestById + "]";
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
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime * result
				+ ((mobileType == null) ? 0 : mobileType.hashCode());
		result = prime * result
				+ ((requestBy == null) ? 0 : requestBy.hashCode());
		result = prime * result
				+ ((requestById == null) ? 0 : requestById.hashCode());
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
		VerifyMobileDTO other = (VerifyMobileDTO) obj;
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
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
			return false;
		if (mobileType == null) {
			if (other.mobileType != null)
				return false;
		} else if (!mobileType.equals(other.mobileType))
			return false;
		if (requestBy == null) {
			if (other.requestBy != null)
				return false;
		} else if (!requestBy.equals(other.requestBy))
			return false;
		if (requestById == null) {
			if (other.requestById != null)
				return false;
		} else if (!requestById.equals(other.requestById))
			return false;
		return true;
	}

		

}
