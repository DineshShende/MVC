package com.projectx.mvc.domain.quickregister;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class QuickRegisterMVCDTO {

	private Long customerId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Long mobile;
	
	private Integer pincode;
	
	private Boolean isEmailVerified;

	private Boolean isMobileVerified;
	
	private Integer customerType;
	
	public QuickRegisterMVCDTO() {
		
	}


	public QuickRegisterMVCDTO(Long customerId, String firstName,
			String lastName, String email, Long mobile, Integer pincode,
			Boolean isEmailVerified, Boolean isMobileVerified,
			Integer customerType) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pincode = pincode;
		this.isEmailVerified = isEmailVerified;
		this.isMobileVerified = isMobileVerified;
		this.customerType = customerType;
	}


	public Long getCustomerId() {
		return customerId;
	}




	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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




	public Integer getPincode() {
		return pincode;
	}




	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}




	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}




	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}




	public Boolean getIsMobileVerified() {
		return isMobileVerified;
	}




	public void setIsMobileVerified(Boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}


	public Integer getCustomerType() {
		return customerType;
	}


	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}


	public void toCustomerQuickRegisterMVC(QuickRegisterDTO customerDTO)
	{

		this.customerId = customerDTO.getCustomerId();
		this.firstName = customerDTO.getFirstName();
		this.lastName = customerDTO.getLastName();
		this.email = customerDTO.getEmail();
		this.mobile = customerDTO.getMobile();
		this.pincode = customerDTO.getPincode();
		this.isEmailVerified=customerDTO.getIsEmailVerified();
		this.isMobileVerified=customerDTO.getIsMobileVerified();
		this.customerType=customerDTO.getCustomerType();
	
	}



	@Override
	public String toString() {
		return "CustomerQuickRegisterMVCDTO [customerId=" + customerId
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", mobile=" + mobile + ", pincode="
				+ pincode + ", isEmailVerified=" + isEmailVerified
				+ ", isMobileVerified=" + isMobileVerified + "]";
	}




	
	
	
	
	
	
	
	
}