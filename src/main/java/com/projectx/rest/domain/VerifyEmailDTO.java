package com.projectx.rest.domain;

public class VerifyEmailDTO {
	
	private String email;
	private Long mobile;
	
	public VerifyEmailDTO() {
		super();
	}

	public VerifyEmailDTO(String email, Long mobile) {
		super();
		this.email = email;
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	
	
	
	

}
