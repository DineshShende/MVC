package com.projectx.rest.domain.completeregister;

public class VerifyEmailDTO {

	private Long customerId;
	
	private Integer customerType;
	
	private String email;
	
	private Integer emailPin;
	
	private String emailHash;

	public VerifyEmailDTO() {

	}

	public VerifyEmailDTO(Long customerId, Integer customerType, String email,
			Integer emailPin, String emailHash) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.email = email;
		this.emailPin = emailPin;
		this.emailHash = emailHash;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailPin() {
		return emailPin;
	}

	public void setEmailPin(Integer emailPin) {
		this.emailPin = emailPin;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	@Override
	public String toString() {
		return "VerifyEmailDTO [customerId=" + customerId + ", customerType="
				+ customerType + ", email=" + email + ", emailPin=" + emailPin
				+ ", emailHash=" + emailHash + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((emailHash == null) ? 0 : emailHash.hashCode());
		result = prime * result
				+ ((emailPin == null) ? 0 : emailPin.hashCode());
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
		VerifyEmailDTO other = (VerifyEmailDTO) obj;
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailHash == null) {
			if (other.emailHash != null)
				return false;
		} else if (!emailHash.equals(other.emailHash))
			return false;
		if (emailPin == null) {
			if (other.emailPin != null)
				return false;
		} else if (!emailPin.equals(other.emailPin))
			return false;
		return true;
	}
	
	
	
}
