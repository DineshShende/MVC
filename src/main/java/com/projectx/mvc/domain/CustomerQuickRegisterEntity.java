package com.projectx.mvc.domain;

public class CustomerQuickRegisterEntity {

	private String firstName;
	private String lastName;
	private String email;
	private Long mobile;
	private Integer pin;
	private String status;

	public CustomerQuickRegisterEntity(String firstName, String lastName,
			String email, Long mobile, Integer pin, String status) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pin = pin;
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerQuickRegisterEntity [firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", mobile="
				+ mobile + ", pin=" + pin + ", status=" + status + "]";
	}
	
	

	public CustomerQuickRegisterEntity() {
	
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
