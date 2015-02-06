package com.projectx.mvc.domain.request;

public class SessionData {

	
	private String name;
	
	private Integer password;

	public SessionData() {

	}

	public SessionData(String name, Integer password) {

		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SessionData [name=" + name + ", password=" + password + "]";
	}
	
	
	
	
}
