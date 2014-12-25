package com.projectx.mvc.domain.quickregister;

public class LoginDetailsDTO {
	
	private String entity;
	
	private String password;

	public LoginDetailsDTO() {
		
		
	}

	public LoginDetailsDTO(String entity, String password) {
		super();
		this.entity = entity;
		this.password = password;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
