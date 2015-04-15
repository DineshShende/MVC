	package com.projectx.rest.domain.completeregister;

	import java.util.Date;

	import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
	import com.fasterxml.jackson.databind.annotation.JsonSerialize;
	import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
	import com.projectx.mvc.util.serializer.JsonDateSerializer;


	public class CustomerDetailsAngDTO {
		
		private Long customerId;
		
		private String firstName;
		
		private String middleName;
		
		private String lastName;
		
		
		private Date dateOfBirth;
		
		private Address homeAddressId;
		
		private Long mobile;
		
		private Long phoneNumber;
		
		private Boolean isMobileVerified ;
		
		private String  email;
		
		private Boolean isEmailVerified;
		
		private String language;
		
		private String  businessDomain;
		
		private String nameOfFirm;
		
		private Address firmAddressId;
		
		private Long secondaryMobile;
		
		private Boolean isSecondaryMobileVerified;
		
		private String secondaryEmail;
		
		private Date insertTime;
		
		private Date updateTime;
		
		private String updatedBy;

		
		public CustomerDetailsAngDTO() {

		}


		

		public CustomerDetailsAngDTO(Long customerId, String firstName,
				String middleName, String lastName, Date dateOfBirth,
				Address homeAddressId, Long mobile,Long phoneNumber, Boolean isMobileVerified,
				String email, Boolean isEmailVerified, String language,
				String businessDomain, String nameOfFirm, Address firmAddressId,
				Long secondaryMobile, Boolean isSecondaryMobileVerified,
				String secondaryEmail, Date insertTime, Date updateTime,
				String updatedBy) {
			super();
			this.customerId = customerId;
			this.firstName = firstName;
			this.middleName = middleName;
			this.lastName = lastName;
			this.dateOfBirth = dateOfBirth;
			this.homeAddressId = homeAddressId;
			this.mobile = mobile;
			this.phoneNumber=phoneNumber;
			this.isMobileVerified = isMobileVerified;
			this.email = email;
			this.isEmailVerified = isEmailVerified;
			this.language = language;
			this.businessDomain = businessDomain;
			this.nameOfFirm = nameOfFirm;
			this.firmAddressId = firmAddressId;
			this.secondaryMobile = secondaryMobile;
			this.isSecondaryMobileVerified = isSecondaryMobileVerified;
			this.secondaryEmail = secondaryEmail;
			this.insertTime = insertTime;
			this.updateTime = updateTime;
			this.updatedBy = updatedBy;
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

		//@JsonSerialize(using=JsonDateSerializer.class)
		public Date getDateOfBirth() {
			return dateOfBirth;
		}

		//@JsonDeserialize(using = JsonDateDeSerializer.class)
		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}


		public Address getHomeAddressId() {
			return homeAddressId;
		}


		public void setHomeAddressId(Address homeAddressId) {
			this.homeAddressId = homeAddressId;
		}


		public Long getMobile() {
			return mobile;
		}


		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}


		public Boolean getIsMobileVerified() {
			return isMobileVerified;
		}


		public void setIsMobileVerified(Boolean isMobileVerified) {
			this.isMobileVerified = isMobileVerified;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public Boolean getIsEmailVerified() {
			return isEmailVerified;
		}


		public void setIsEmailVerified(Boolean isEmailVerified) {
			this.isEmailVerified = isEmailVerified;
		}


		public String getLanguage() {
			return language;
		}


		public void setLanguage(String language) {
			this.language = language;
		}


		public String getBusinessDomain() {
			return businessDomain;
		}


		public void setBusinessDomain(String businessDomain) {
			this.businessDomain = businessDomain;
		}


		public String getNameOfFirm() {
			return nameOfFirm;
		}


		public void setNameOfFirm(String nameOfFirm) {
			this.nameOfFirm = nameOfFirm;
		}


		public Address getFirmAddressId() {
			return firmAddressId;
		}


		public void setFirmAddressId(Address firmAddressId) {
			this.firmAddressId = firmAddressId;
		}


		public Long getSecondaryMobile() {
			return secondaryMobile;
		}


		public void setSecondaryMobile(Long secondaryMobile) {
			this.secondaryMobile = secondaryMobile;
		}


		public Boolean getIsSecondaryMobileVerified() {
			return isSecondaryMobileVerified;
		}


		public void setIsSecondaryMobileVerified(Boolean isSecondaryMobileVerified) {
			this.isSecondaryMobileVerified = isSecondaryMobileVerified;
		}


		public String getSecondaryEmail() {
			return secondaryEmail;
		}


		public void setSecondaryEmail(String secondaryEmail) {
			this.secondaryEmail = secondaryEmail;
		}

		@JsonSerialize(using=JsonDateSerializer.class)
		public Date getInsertTime() {
			return insertTime;
		}

		@JsonDeserialize(using = JsonDateDeSerializer.class)
		public void setInsertTime(Date insertTime) {
			this.insertTime = insertTime;
		}

		@JsonSerialize(using=JsonDateSerializer.class)
		public Date getUpdateTime() {
			return updateTime;
		}

		@JsonDeserialize(using = JsonDateDeSerializer.class)
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}


		public String getUpdatedBy() {
			return updatedBy;
		}


		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}


		
		
		public String getMiddleName() {
			return middleName;
		}




		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}




		public Long getPhoneNumber() {
			return phoneNumber;
		}




		public void setPhoneNumber(Long phoneNumber) {
			this.phoneNumber = phoneNumber;
		}




		@Override
		public String toString() {
			return "CustomerDetailsDTO [customerId=" + customerId + ", firstName="
					+ firstName + ", middleName=" + middleName + ", lastName="
					+ lastName + ", dateOfBirth=" + dateOfBirth
					+ ", homeAddressId=" + homeAddressId + ", mobile=" + mobile
					+ ", phoneNumber=" + phoneNumber + ", isMobileVerified="
					+ isMobileVerified + ", email=" + email + ", isEmailVerified="
					+ isEmailVerified + ", language=" + language
					+ ", businessDomain=" + businessDomain + ", nameOfFirm="
					+ nameOfFirm + ", firmAddressId=" + firmAddressId
					+ ", secondaryMobile=" + secondaryMobile
					+ ", isSecondaryMobileVerified=" + isSecondaryMobileVerified
					+ ", secondaryEmail=" + secondaryEmail + ", insertTime="
					+ insertTime + ", updateTime=" + updateTime + ", updatedBy="
					+ updatedBy + "]";
		}
		
	}

