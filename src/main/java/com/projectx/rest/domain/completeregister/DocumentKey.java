package com.projectx.rest.domain.completeregister;




public class DocumentKey   {

	private Long customerId;
	
	private Integer customerType;
	
	private String documentName;
	
	private Integer documentVersion;

	public DocumentKey() {

	}

	public DocumentKey(Long customerId, Integer customerType,
			String documentName, Integer documentVersion) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.documentName = documentName;
		this.documentVersion = documentVersion;
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

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	

	public Integer getDocumentVersion() {
		return documentVersion;
	}

	public void setDocumentVersion(Integer documentVersion) {
		this.documentVersion = documentVersion;
	}

	@Override
	public String toString() {
		return "DocumentKey [customerId=" + customerId + ", customerType="
				+ customerType + ", documentName=" + documentName
				+ ", documentVersion=" + documentVersion + "]";
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
				+ ((documentName == null) ? 0 : documentName.hashCode());
		result = prime * result
				+ ((documentVersion == null) ? 0 : documentVersion.hashCode());
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
		DocumentKey other = (DocumentKey) obj;
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
		if (documentName == null) {
			if (other.documentName != null)
				return false;
		} else if (!documentName.equals(other.documentName))
			return false;
		if (documentVersion == null) {
			if (other.documentVersion != null)
				return false;
		} else if (!documentVersion.equals(other.documentVersion))
			return false;
		return true;
	}

		
	
	
}
