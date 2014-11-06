package com.projectx.mvc.domain;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerQuickRegisterDTO;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CustomerQuickRegisterMVCDTO {

	private Long customerId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Long mobile;
	
	private Integer pin;
	
	private String status;

	
	private Date mobilePinSentTime;	

	private Date emailHashSentTime;
	
	private Date lastStatusChangedTime;
	
	private Boolean isMobileVerificationNeeded;
	
	private Boolean isEmailVerificationNeeded;
	
	public CustomerQuickRegisterMVCDTO() {
		
	}

	
	public CustomerQuickRegisterMVCDTO(Long customerId, String firstName,
			String lastName, String email, Long mobile, Integer pin,
			String status, Integer mobilePin, String emailHash,
			Integer mobileVerificationAttempts, Date mobilePinSentTime,
			Date emailHashSentTime, Date lastStatusChangedTime, String password) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pin = pin;
		this.status = status;
		this.mobilePinSentTime = mobilePinSentTime;
		this.emailHashSentTime = emailHashSentTime;
		this.lastStatusChangedTime = lastStatusChangedTime;
		this.isEmailVerificationNeeded=true;
		this.isMobileVerificationNeeded=true;
	
	}

	//public void setCustomerQuickRegisterInfo(CustomerQuickRegisterDTO)
	

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

	public Boolean getIsMobileVerificationNeeded() {
		return isMobileVerificationNeeded;
	}


	public void setIsMobileVerificationNeeded(Boolean isMobileVerificationNeeded) {
		this.isMobileVerificationNeeded = isMobileVerificationNeeded;
	}


	public Boolean getIsEmailVerificationNeeded() {
		return isEmailVerificationNeeded;
	}


	public void setIsEmailVerificationNeeded(Boolean isEmailVerificationNeeded) {
		this.isEmailVerificationNeeded = isEmailVerificationNeeded;
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

		public Date getMobilePinSentTime() {
		return mobilePinSentTime;
	}

	public void setMobilePinSentTime(Date mobilePinSentTime) {
		this.mobilePinSentTime = mobilePinSentTime;
	}

	public Date getEmailHashSentTime() {
		return emailHashSentTime;
	}

	public void setEmailHashSentTime(Date emailHashSentTime) {
		this.emailHashSentTime = emailHashSentTime;
	}

	public Date getLastStatusChangedTime() {
		return lastStatusChangedTime;
	}

	public void setLastStatusChangedTime(Date lastStatusChangedTime) {
		this.lastStatusChangedTime = lastStatusChangedTime;
	}


	public Boolean isMobileVerificationNeeded(String statusInp)
	{
		if(statusInp.equals("MobileVerificationPending")||statusInp.equals("EmailVerifiedMobileVerficationPending")|| statusInp.equals("EmailMobileVerificationPending"))
			return true;
		else
			return false;
	}

	public Boolean isEmailVerificationNeeded(String statusInp)
	{
		if(statusInp.equals("EmailVerificationPending")||statusInp.equals("MobileVerifiedEmailVerficationPending")|| statusInp.equals("EmailMobileVerificationPending"))
			return true;
		else
			return false;
	}

	public void toCustomerQuickRegisterMVC(CustomerQuickRegisterDTO customerDTO)
	{

		this.customerId = customerDTO.getCustomerId();
		this.firstName = customerDTO.getFirstName();
		this.lastName = customerDTO.getLastName();
		this.email = customerDTO.getEmail();
		this.mobile = customerDTO.getMobile();
		this.pin = customerDTO.getPin();
		this.status = customerDTO.getStatus();
		this.mobilePinSentTime = customerDTO.getMobilePinSentTime();
		this.emailHashSentTime = customerDTO.getEmailHashSentTime();
		this.lastStatusChangedTime = customerDTO.getLastStatusChangedTime();
		if(customerDTO.getStatus()!=null)
			this.isEmailVerificationNeeded=isEmailVerificationNeeded(customerDTO.getStatus());
		else
			this.isEmailVerificationNeeded=false;
		
		if(customerDTO.getStatus()!=null)
			this.isMobileVerificationNeeded=isMobileVerificationNeeded(customerDTO.getStatus());
		else
			this.isMobileVerificationNeeded=false;
	}


	@Override
	public String toString() {
		return "CustomerQuickRegisterMVCDTO [customerId=" + customerId
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", mobile=" + mobile + ", pin=" + pin
				+ ", status=" + status + ", mobilePinSentTime="
				+ mobilePinSentTime + ", emailHashSentTime="
				+ emailHashSentTime + ", lastStatusChangedTime="
				+ lastStatusChangedTime + ", isMobileVerificationNeeded="
				+ isMobileVerificationNeeded + ", isEmailVerificationNeeded="
				+ isEmailVerificationNeeded + "]";
	}


	
	
	
	
	
	
	
}
