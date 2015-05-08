package com.projectx.rest.domain.ang;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;

public class CustomerDetailsDTOAng {

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
	
	private Integer entityType;
	
	private Date insertTime;
	
	private Date updateTime;
	
	private Integer requestedBy;
	
	private Long requestedById;

	
	public CustomerDetailsDTOAng() {

	}


	

	public CustomerDetailsDTOAng(Long customerId, String firstName,
			String middleName, String lastName, Date dateOfBirth,
			Address homeAddressId, Long mobile,Long phoneNumber, Boolean isMobileVerified,
			String email, Boolean isEmailVerified, String language,
			String businessDomain, String nameOfFirm, Address firmAddressId,
			Long secondaryMobile, Boolean isSecondaryMobileVerified,
			String secondaryEmail,Integer entityType, Date insertTime, Date updateTime,
			Integer updatedBy,Long updatedById) {
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
		this.entityType=entityType;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.requestedBy = updatedBy;
		this.requestedById=updatedById;
	}

	public CustomerDetailsDTO toCustomerDetailsDTO()
	{
		CustomerDetailsDTO detailsDTO=new CustomerDetailsDTO(this.getCustomerId(),
				this.getFirstName(),this.getMiddleName(), this.getLastName(),
				this.getDateOfBirth(), this.getHomeAddressId(),this.getMobile(),
				this.getPhoneNumber(), this.getIsMobileVerified(),this.getEmail(),
				this.getIsEmailVerified(),this.getLanguage(), this.getBusinessDomain(),
				this.getNameOfFirm(), this.getFirmAddressId(), this.getSecondaryMobile(), 
				false, this.getSecondaryEmail(), new Date(), new Date(), this.getRequestedBy(),
				this.getRequestedBy(),this.getRequestedById(),this.getRequestedById());
		
		return detailsDTO;
		
	}
	
	
	public VendorDetailsDTO toVendorDetailsDTO()
	{
		VendorDetailsDTO vendorDetails=new VendorDetailsDTO(this.getCustomerId(), this.getFirstName(), this.getMiddleName(),
				this.getLastName(),this.getDateOfBirth(), this.getNameOfFirm(), 
				this.getFirmAddressId(), this.getHomeAddressId(), this.getMobile(), this.getPhoneNumber(),
				this.getIsMobileVerified(), this.getEmail(), this.getIsEmailVerified(), this.getLanguage(),
				this.getSecondaryMobile(),this.getIsSecondaryMobileVerified(), new Date(),new Date(), 
				this.getRequestedBy(),this.getRequestedBy(),this.getRequestedById(),
				this.getRequestedById());
		
		return vendorDetails;
	}

	
	public static CustomerDetailsDTOAng fromCustomerDetailsDTO(CustomerDetailsDTO customerDetailsDTO)
	{
		CustomerDetailsDTOAng customerDetailsDTOAng=new CustomerDetailsDTOAng
				(customerDetailsDTO.getCustomerId(), customerDetailsDTO.getFirstName(), customerDetailsDTO.getMiddleName(),
						customerDetailsDTO.getLastName(), customerDetailsDTO.getDateOfBirth(),customerDetailsDTO.getHomeAddressId(),
						customerDetailsDTO.getMobile(), customerDetailsDTO.getPhoneNumber(),customerDetailsDTO.getIsMobileVerified(),
						customerDetailsDTO.getEmail(), customerDetailsDTO.getIsEmailVerified(), customerDetailsDTO.getLanguage(), 
						customerDetailsDTO.getBusinessDomain(), customerDetailsDTO.getNameOfFirm(), customerDetailsDTO.getFirmAddressId(),
						customerDetailsDTO.getSecondaryMobile(), customerDetailsDTO.getIsSecondaryMobileVerified(), 
						customerDetailsDTO.getSecondaryEmail(),1, customerDetailsDTO.getInsertTime(), customerDetailsDTO.getUpdateTime(), 
						customerDetailsDTO.getUpdatedBy(),customerDetailsDTO.getUpdatedById());
		
		return customerDetailsDTOAng;
		
	}
	
	public static CustomerDetailsDTOAng fromVendorDetailsDTO(VendorDetailsDTO fetchedEntity)
	{
		CustomerDetailsDTOAng ang=new CustomerDetailsDTOAng(fetchedEntity.getVendorId(), fetchedEntity.getFirstName(), fetchedEntity.getMiddleName(),
				fetchedEntity.getLastName(), fetchedEntity.getDateOfBirth(), fetchedEntity.getHomeAddress(), 
				fetchedEntity.getMobile(), fetchedEntity.getPhoneNumber(), fetchedEntity.getIsMobileVerified(), 
				fetchedEntity.getEmail(), fetchedEntity.getIsEmailVerified(), fetchedEntity.getLaguage(), 
				null, fetchedEntity.getFirmName(), fetchedEntity.getFirmAddress(), 
				fetchedEntity.getSecondaryMobile(), fetchedEntity.getIsSecondaryMobileVerified(),null,2, 
				fetchedEntity.getInsertTime(),fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy(),fetchedEntity.getUpdatedById());
		
		return ang;
		
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

	//@JsonSerialize(using=JsonDateSerializer.class)
	public Date getInsertTime() {
		return insertTime;
	}

	//@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	//@JsonSerialize(using=JsonDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	//@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}


	public Long getRequestedById() {
		return requestedById;
	}

	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
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


	public Integer getEntityType() {
		return entityType;
	}




	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}




	@Override
	public String toString() {
		return "CustomerDetailsDTOAng [customerId=" + customerId
				+ ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", homeAddressId=" + homeAddressId + ", mobile=" + mobile
				+ ", phoneNumber=" + phoneNumber + ", isMobileVerified="
				+ isMobileVerified + ", email=" + email + ", isEmailVerified="
				+ isEmailVerified + ", language=" + language
				+ ", businessDomain=" + businessDomain + ", nameOfFirm="
				+ nameOfFirm + ", firmAddressId=" + firmAddressId
				+ ", secondaryMobile=" + secondaryMobile
				+ ", isSecondaryMobileVerified=" + isSecondaryMobileVerified
				+ ", secondaryEmail=" + secondaryEmail + ", entityType="
				+ entityType + ", insertTime=" + insertTime + ", updateTime="
				+ updateTime + ", updatedBy=" + requestedBy + ", updatedById="
				+ requestedById + "]";
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
				+ ((entityType == null) ? 0 : entityType.hashCode());
		result = prime * result
				+ ((firmAddressId == null) ? 0 : firmAddressId.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homeAddressId == null) ? 0 : homeAddressId.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
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
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
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
		CustomerDetailsDTOAng other = (CustomerDetailsDTOAng) obj;
		if (businessDomain == null) {
			if (other.businessDomain != null)
				return false;
		} else if (!businessDomain.equals(other.businessDomain))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} 
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (entityType == null) {
			if (other.entityType != null)
				return false;
		} else if (!entityType.equals(other.entityType))
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
		if (isEmailVerified == null) {
			if (other.isEmailVerified != null)
				return false;
		} else if (!isEmailVerified.equals(other.isEmailVerified))
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
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		} else if (!requestedById.equals(other.requestedById))
			return false;
		return true;
	}


	
}
