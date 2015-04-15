package com.projectx.mvc.domain.completeregister;

import java.util.Date;

import com.projectx.rest.domain.completeregister.Address;

public class CustomerDetailsAngDTO {

	String firstName;
	
	String middleName;
		
	String lastName;
		
	Date dateOfBirth;
		
	Address homeAddressId;

	Long mobile;
		
	Long phoneNumber;
		
	String  email;
		
	String language;
		
	String  businessDomain;
		
	String nameOfFirm;
		
	Address firmAddressId;
		
	Long secondaryMobile;
		
	String secondaryEmail;
		
	String updatedBy;

	public CustomerDetailsAngDTO() {

	}

	public CustomerDetailsAngDTO(String firstName, String middleName,
			String lastName, Date dateOfBirth, Address homeAddressId,
			Long mobile, Long phoneNumber, String email, String language,
			String businessDomain, String nameOfFirm, Address firmAddressId,
			Long secondaryMobile, String secondaryEmail, String updatedBy) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.homeAddressId = homeAddressId;
		this.mobile = mobile;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.language = language;
		this.businessDomain = businessDomain;
		this.nameOfFirm = nameOfFirm;
		this.firmAddressId = firmAddressId;
		this.secondaryMobile = secondaryMobile;
		this.secondaryEmail = secondaryEmail;
		this.updatedBy = updatedBy;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

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

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "CustomerDetailsAngDTO [firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", homeAddressId="
				+ homeAddressId + ", mobile=" + mobile + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", language=" + language
				+ ", businessDomain=" + businessDomain + ", nameOfFirm="
				+ nameOfFirm + ", firmAddressId=" + firmAddressId
				+ ", secondaryMobile=" + secondaryMobile + ", secondaryEmail="
				+ secondaryEmail + ", updatedBy=" + updatedBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessDomain == null) ? 0 : businessDomain.hashCode());
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firmAddressId == null) ? 0 : firmAddressId.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homeAddressId == null) ? 0 : homeAddressId.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((nameOfFirm == null) ? 0 : nameOfFirm.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result
				+ ((secondaryEmail == null) ? 0 : secondaryEmail.hashCode());
		result = prime * result
				+ ((secondaryMobile == null) ? 0 : secondaryMobile.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		CustomerDetailsAngDTO other = (CustomerDetailsAngDTO) obj;
		if (businessDomain == null) {
			if (other.businessDomain != null)
				return false;
		} else if (!businessDomain.equals(other.businessDomain))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firmAddressId == null) {
			if (other.firmAddressId != null)
				return false;
		} else if (!firmAddressId.equals(other.firmAddressId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homeAddressId == null) {
			if (other.homeAddressId != null)
				return false;
		} else if (!homeAddressId.equals(other.homeAddressId))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (nameOfFirm == null) {
			if (other.nameOfFirm != null)
				return false;
		} else if (!nameOfFirm.equals(other.nameOfFirm))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (secondaryEmail == null) {
			if (other.secondaryEmail != null)
				return false;
		} else if (!secondaryEmail.equals(other.secondaryEmail))
			return false;
		if (secondaryMobile == null) {
			if (other.secondaryMobile != null)
				return false;
		} else if (!secondaryMobile.equals(other.secondaryMobile))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}

	
	
	
	
}
