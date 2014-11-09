package com.projectx.mvc.domain;

import java.util.Arrays;

public class CustomerDocumetDTO {

	private Long customerId;
	
	private byte[] image;

	
	public CustomerDocumetDTO() {
		super();
	}

	public CustomerDocumetDTO(Long customerId, byte[] image) {
		super();
		this.customerId = customerId;
		this.image = image;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CustomerDocumetDTO [customerId=" + customerId + ", image="
				+ Arrays.toString(image) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + Arrays.hashCode(image);
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
		CustomerDocumetDTO other = (CustomerDocumetDTO) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		return true;
	}
	
	
	
	
	
	
}
