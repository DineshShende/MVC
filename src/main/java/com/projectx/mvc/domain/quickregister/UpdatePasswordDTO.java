package com.projectx.mvc.domain.quickregister;

import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;

public class UpdatePasswordDTO {
	
	private AuthenticationDetailsKey key;

	private String password;

	
	
	public UpdatePasswordDTO() {

	}



	public UpdatePasswordDTO(AuthenticationDetailsKey key, String password) {
		super();
		this.key = key;
		this.password = password;
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



	@Override
	public String toString() {
		return "UpdatePasswordDTO [key=" + key + ", password=" + password + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		return true;
	}

	
	
}
