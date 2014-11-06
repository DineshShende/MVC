//This
package com.projectx.mvc.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerQuickRegisterEntity {

	
	
	private String firstName;
	
	private String lastName;

	@Email	
	private String email;
	
	private Long mobile;

	private Integer pin;
	private String status;
	
	
	public CustomerQuickRegisterEntity() {
		super();
	}

	
	
	

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




	@JsonIgnore
	public boolean isEmailVerificationPending() {
		return status == "EmailVerificationPending";
	}
	@JsonIgnore
	public boolean isMobileVerificationPending() {
		return status == "MobileVerificationPending";
	}
	@JsonIgnore
	public boolean isEmailVerifiedMobileVerficationPending() {
		return status == "EmailVerifiedMobileVerficationPending";
	}
	@JsonIgnore
	public boolean isMobileVerifiedEmailVerficationPending() {
		return status == "MobileVerifiedEmailVerficationPending";
	}
	@JsonIgnore
	public boolean isMobileVerified() {
		return status == "MobileVerified";
	}
	@JsonIgnore
	public boolean isEmailVerified() {
		return status == "EmailVerified";
	}
	@JsonIgnore
	public boolean isEmailMobileVerified() {
		return status == "EmailMobileVerified";
	}
	@JsonIgnore
	public boolean isEmailMobileVerificationPending() {
		return status == "EmailMobileVerificationPending";
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





	@Override
	public String toString() {
		return "CustomerQuickRegisterEntity [firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", mobile="
				+ mobile + ", pin=" + pin + ", status=" + status + "]";
	}


	
	
	
}
