package com.projectx.rest.domain.completeregister;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;


public class CustomerDetailsDTO {
	
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
	
	private Boolean isVerified;
	
	private Date insertTime;
	
	private Date updateTime;
	
	private Integer updatedBy;
	
	private Integer insertedBy;
	
	private Long updatedById;
	
	private Long insertedById;

	
	public CustomerDetailsDTO() {

	}


	

	public CustomerDetailsDTO(Long customerId, String firstName,
			String middleName, String lastName, Date dateOfBirth,
			Address homeAddressId, Long mobile,Long phoneNumber, Boolean isMobileVerified,
			String email, Boolean isEmailVerified, String language,
			String businessDomain, String nameOfFirm, Address firmAddressId,
			Long secondaryMobile, Boolean isSecondaryMobileVerified,
			String secondaryEmail,Boolean isVerified, Date insertTime, Date updateTime,
			Integer updatedBy,Integer insertedBy,Long updatedById,Long insertedById) {
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
		this.isVerified=isVerified;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.updatedBy = updatedBy;
		this.insertedBy=insertedBy;
		this.updatedById=updatedById;
		this.insertedById=insertedById;
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

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
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


		public Integer getUpdatedBy() {
		return updatedBy;
	}




	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}




	public Integer getInsertedBy() {
		return insertedBy;
	}




	public void setInsertedBy(Integer insertedBy) {
		this.insertedBy = insertedBy;
	}




	public Long getUpdatedById() {
		return updatedById;
	}




	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}




	public Long getInsertedById() {
		return insertedById;
	}




	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
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
				+ ", secondaryEmail=" + secondaryEmail + ", isVerified="
				+ isVerified + ", insertTime=" + insertTime + ", updateTime="
				+ updateTime + ", updatedBy=" + updatedBy + ", insertedBy="
				+ insertedBy + ", updatedById=" + updatedById
				+ ", insertedById=" + insertedById + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessDomain == null) ? 0 : businessDomain.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
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
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
		result = prime * result
				+ ((isEmailVerified == null) ? 0 : isEmailVerified.hashCode());
		result = prime
				* result
				+ ((isMobileVerified == null) ? 0 : isMobileVerified.hashCode());
		result = prime
				* result
				+ ((isSecondaryMobileVerified == null) ? 0
						: isSecondaryMobileVerified.hashCode());
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
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
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
		CustomerDetailsDTO other = (CustomerDetailsDTO) obj;
		if (businessDomain == null) {
			if (other.businessDomain != null)
				return false;
		} else if (!businessDomain.equals(other.businessDomain))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		}
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} 
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
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		}
		if (insertedBy == null) {
			if (other.insertedBy != null)
				return false;
		} 
		if (insertedById == null) {
			if (other.insertedById != null)
				return false;
		}
		if (isEmailVerified == null) {
			if (other.isEmailVerified != null)
				return false;
		} else if (!isEmailVerified.equals(other.isEmailVerified))
			return false;
		if (isVerified == null) {
			if (other.isVerified != null)
				return false;
		} else if (!isVerified.equals(other.isVerified))
			return false;
		
		if (isMobileVerified == null) {
			if (other.isMobileVerified != null)
				return false;
		} else if (!isMobileVerified.equals(other.isMobileVerified))
			return false;
		if (isSecondaryMobileVerified == null) {
			if (other.isSecondaryMobileVerified != null)
				return false;
		} else if (!isSecondaryMobileVerified
				.equals(other.isSecondaryMobileVerified))
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
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		}
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} 
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		}
		return true;
	}




		
	
}
