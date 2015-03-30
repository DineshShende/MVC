package com.projectx.mvc.domain.quickregister;

import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;

public class UpdatePasswordDTO {
	
	private AuthenticationDetailsKey key;

	private String password;

	private Boolean isForcefulChangePassword;
	
	private String requestedBy;
	
	public UpdatePasswordDTO() {

	}


	public UpdatePasswordDTO(AuthenticationDetailsKey key, String password,
			Boolean isForcefulChangePassword, String requestedBy) {
		super();
		this.key = key;
		this.password = password;
		this.isForcefulChangePassword = isForcefulChangePassword;
		this.requestedBy = requestedBy;
	}





	public AuthenticationDetailsKey getKey() {
		return key;
	}



	public void setKey(AuthenticationDetailsKey key) {
		this.key = key;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public String getRequestedBy() {
		return requestedBy;
	}


	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}


	
	
	public Boolean getIsForcefulChangePassword() {
		return isForcefulChangePassword;
	}


	public void setIsForcefulChangePassword(Boolean isForcefulChangePassword) {
		this.isForcefulChangePassword = isForcefulChangePassword;
	}


	@Override
	public String toString() {
		return "UpdatePasswordDTO [key=" + key + ", password=" + password
				+ ", isForcefulChangePassword=" + isForcefulChangePassword
				+ ", requestedBy=" + requestedBy + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((isForcefulChangePassword == null) ? 0
						: isForcefulChangePassword.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
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
		UpdatePasswordDTO other = (UpdatePasswordDTO) obj;
		if (isForcefulChangePassword == null) {
			if (other.isForcefulChangePassword != null)
				return false;
		} else if (!isForcefulChangePassword
				.equals(other.isForcefulChangePassword))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		return true;
	}


	
	
	
}
