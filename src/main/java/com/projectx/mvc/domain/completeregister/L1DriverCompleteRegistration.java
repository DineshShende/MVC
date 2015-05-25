package com.projectx.mvc.domain.completeregister;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;

public class L1DriverCompleteRegistration {
	
	private Long driverId;
	
	@NotNull
	private String firstName;
	
	private String middleName;
	
	@NotNull
	private String lastName;

	private Date dateOfBirth;

	@NotNull
	private String addressLine;
	
	@NotNull
	private String city;
	
	@NotNull
	private String district;
	
	@NotNull
	private String state;
	
	@Max(value=999999)
	@Min(value=100000)
	private Integer pincode;

	@NotNull
	private String licenceNumber;

	@NotNull
	private Date licenceDOI;
	
	@NotNull
	private Date licenceValidTill;
	
	private List<String> covList=new ArrayList<String>();

	
	@NotNull
	private Boolean isDetailRegistrationCompleted;




	public L1DriverCompleteRegistration() {
		super();
	}


	public L1DriverCompleteRegistration(Long driverId, String firstName,
			String middleName, String lastName, Date dateOfBirth,
			String addressLine, String city, String district, String state,
			Integer pincode, String licenceNumber, Date licenceDOI,
			Date licenceValidTill, List<String> covList,
			Boolean isDetailRegistrationCompleted) {
		super();
		this.driverId = driverId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.addressLine = addressLine;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
		this.licenceNumber = licenceNumber;
		this.licenceDOI = licenceDOI;
		this.licenceValidTill = licenceValidTill;
		this.covList = covList;
		this.isDetailRegistrationCompleted = isDetailRegistrationCompleted;
	}


	public Long getDriverId() {
		return driverId;
	}


	public void setDriverId(Long driverId) {
		this.driverId = driverId;
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

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getAddressLine() {
		return addressLine;
	}


	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Integer getPincode() {
		return pincode;
	}


	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}


	public String getLicenceNumber() {
		return licenceNumber;
	}


	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getLicenceDOI() {
		return licenceDOI;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setLicenceDOI(Date licenceDOI) {
		this.licenceDOI = licenceDOI;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getLicenceValidTill() {
		return licenceValidTill;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setLicenceValidTill(Date licenceValidTill) {
		this.licenceValidTill = licenceValidTill;
	}


	public List<String> getCovList() {
		return covList;
	}


	public void setCovList(List<String> covList) {
		this.covList = covList;
	}


	public Boolean getIsDetailRegistrationCompleted() {
		return isDetailRegistrationCompleted;
	}


	public void setIsDetailRegistrationCompleted(
			Boolean isDetailRegistrationCompleted) {
		this.isDetailRegistrationCompleted = isDetailRegistrationCompleted;
	}
	
	


	@Override
	public String toString() {
		return "L1DriverCompleteRegistration [driverId=" + driverId
				+ ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", addressLine=" + addressLine + ", city=" + city
				+ ", district=" + district + ", state=" + state + ", pincode="
				+ pincode + ", licenceNumber=" + licenceNumber
				+ ", licenceDOI=" + licenceDOI + ", licenceValidTill="
				+ licenceValidTill + ", covList=" + covList
				+ ", isDetailRegistrationCompleted="
				+ isDetailRegistrationCompleted + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressLine == null) ? 0 : addressLine.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((covList == null) ? 0 : covList.hashCode());
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result
				+ ((district == null) ? 0 : district.hashCode());
		result = prime * result
				+ ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime
				* result
				+ ((isDetailRegistrationCompleted == null) ? 0
						: isDetailRegistrationCompleted.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((licenceDOI == null) ? 0 : licenceDOI.hashCode());
		result = prime * result
				+ ((licenceNumber == null) ? 0 : licenceNumber.hashCode());
		result = prime
				* result
				+ ((licenceValidTill == null) ? 0 : licenceValidTill.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		L1DriverCompleteRegistration other = (L1DriverCompleteRegistration) obj;
		if (addressLine == null) {
			if (other.addressLine != null)
				return false;
		} else if (!addressLine.equals(other.addressLine))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (covList == null) {
			if (other.covList != null)
				return false;
		} else if (!covList.equals(other.covList))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isDetailRegistrationCompleted == null) {
			if (other.isDetailRegistrationCompleted != null)
				return false;
		} else if (!isDetailRegistrationCompleted
				.equals(other.isDetailRegistrationCompleted))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (licenceDOI == null) {
			if (other.licenceDOI != null)
				return false;
		} else if (!licenceDOI.equals(other.licenceDOI))
			return false;
		if (licenceNumber == null) {
			if (other.licenceNumber != null)
				return false;
		} else if (!licenceNumber.equals(other.licenceNumber))
			return false;
		if (licenceValidTill == null) {
			if (other.licenceValidTill != null)
				return false;
		} else if (!licenceValidTill.equals(other.licenceValidTill))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	
	
}
