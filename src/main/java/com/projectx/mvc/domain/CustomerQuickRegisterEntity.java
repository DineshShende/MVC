package com.projectx.mvc.domain;

public class CustomerQuickRegisterEntity {

	// private CustomerQuickRegisterKey key;
	private String firstName;
	private String lastName;

	private String email;
	private Long mobile;

	private Integer pin;
	private String status;

	
	
	
	

	public CustomerQuickRegisterEntity(String firstName, String lastName,
			String email, Long mobile, Integer pin, String status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pin = pin;
		this.status = status;
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

	public CustomerQuickRegisterEntity() {
		super();
	}

	public boolean isEmailVerificationPending() {
		return status == "EmailVerificationPending";
	}

	public boolean isMobileVerificationPending() {
		return status == "MobileVerificationPending";
	}

	public boolean isEmailVerifiedMobileVerficationPending() {
		return status == "EmailVerifiedMobileVerficationPending";
	}

	public boolean isMobileVerifiedEmailVerficationPending() {
		return status == "MobileVerifiedEmailVerficationPending";
	}

	public boolean isMobileVerified() {
		return status == "MobileVerified";
	}

	public boolean isEmailVerified() {
		return status == "EmailVerified";
	}

	public boolean isEmailMobileVerified() {
		return status == "EmailMobileVerified";
	}

	public boolean isEmailMobileVerificationPending() {
		return status == "EmailMobileVerificationPending";
	}

}