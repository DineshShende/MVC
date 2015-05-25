package com.projectx.rest.domain.ang;

import java.util.Date;

import com.projectx.mvc.domain.completeregister.DriverSimplified;

public class DriverSimplifiedAng {
	
	private Long driverId;

	private String bloodGroup;

	private Long mobile;

	private Long homeContactNumber;

	private Date drivingSince;

	private Date employedSince;

	private Boolean isFreightRequestPermissionGiven;

	private Boolean isDealFinalizationPermissionGiven;

	private String language;
	
	private Long vendorId;
	
	private Integer requestedBy;
	
	private Long requestedById;

	public DriverSimplifiedAng() {
		super();
	}





	public DriverSimplifiedAng(Long driverId, String bloodGroup, Long mobile,
			Long homeContactNumber, Date drivingSince, Date employedSince,
			Boolean isFreightRequestPermissionGiven,
			Boolean isDealFinalizationPermissionGiven, String language,
			Long vendorId, Integer requestedBy, Long requestedById) {
		super();
		this.driverId = driverId;
		this.bloodGroup = bloodGroup;
		this.mobile = mobile;
		this.homeContactNumber = homeContactNumber;
		this.drivingSince = drivingSince;
		this.employedSince = employedSince;
		this.isFreightRequestPermissionGiven = isFreightRequestPermissionGiven;
		this.isDealFinalizationPermissionGiven = isDealFinalizationPermissionGiven;
		this.language = language;
		this.vendorId = vendorId;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}





	public DriverSimplifiedAng(String bloodGroup, Long mobile,
			Long homeContactNumber, Date drivingSince, Date employedSince,
			Boolean isFreightRequestPermissionGiven,
			Boolean isDealFinalizationPermissionGiven, String language,
			Long vendorId, Integer requestedBy, Long requestedById) {
		super();
		this.bloodGroup = bloodGroup;
		this.mobile = mobile;
		this.homeContactNumber = homeContactNumber;
		this.drivingSince = drivingSince;
		this.employedSince = employedSince;
		this.isFreightRequestPermissionGiven = isFreightRequestPermissionGiven;
		this.isDealFinalizationPermissionGiven = isDealFinalizationPermissionGiven;
		this.language = language;
		this.vendorId = vendorId;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}

	public DriverSimplified toDriverSimplified()
	{
		DriverSimplified driverSimplified=new DriverSimplified(this.driverId, this.bloodGroup, this.mobile,
				this.homeContactNumber, this.drivingSince, this.employedSince, this.isFreightRequestPermissionGiven,
				this.isDealFinalizationPermissionGiven, this.language,
				this.vendorId, this.requestedBy, this.requestedById);
		
		return driverSimplified;
	}



	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Long getHomeContactNumber() {
		return homeContactNumber;
	}

	public void setHomeContactNumber(Long homeContactNumber) {
		this.homeContactNumber = homeContactNumber;
	}

	//@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDrivingSince() {
		return drivingSince;
	}

	//@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setDrivingSince(Date drivingSince) {
		this.drivingSince = drivingSince;
	}

	//@JsonSerialize(using=JsonDateSerializer.class)
	public Date getEmployedSince() {
		return employedSince;
	}

	//@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setEmployedSince(Date employedSince) {
		this.employedSince = employedSince;
	}

	public Boolean getIsFreightRequestPermissionGiven() {
		return isFreightRequestPermissionGiven;
	}

	public void setIsFreightRequestPermissionGiven(
			Boolean isFreightRequestPermissionGiven) {
		this.isFreightRequestPermissionGiven = isFreightRequestPermissionGiven;
	}

	public Boolean getIsDealFinalizationPermissionGiven() {
		return isDealFinalizationPermissionGiven;
	}

	public void setIsDealFinalizationPermissionGiven(
			Boolean isDealFinalizationPermissionGiven) {
		this.isDealFinalizationPermissionGiven = isDealFinalizationPermissionGiven;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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


		public Long getVendorId() {
		return vendorId;
	}


	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}







	@Override
	public String toString() {
		return "DriverSimplified [driverId=" + driverId + ", bloodGroup="
				+ bloodGroup + ", mobile=" + mobile + ", homeContactNumber="
				+ homeContactNumber + ", drivingSince=" + drivingSince
				+ ", employedSince=" + employedSince
				+ ", isFreightRequestPermissionGiven="
				+ isFreightRequestPermissionGiven
				+ ", isDealFinalizationPermissionGiven="
				+ isDealFinalizationPermissionGiven + ", language=" + language
				+ ", vendorId=" + vendorId + ", requestedBy=" + requestedBy
				+ ", requestedById=" + requestedById + "]";
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bloodGroup == null) ? 0 : bloodGroup.hashCode());
		result = prime * result
				+ ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result
				+ ((drivingSince == null) ? 0 : drivingSince.hashCode());
		result = prime * result
				+ ((employedSince == null) ? 0 : employedSince.hashCode());
		result = prime
				* result
				+ ((homeContactNumber == null) ? 0 : homeContactNumber
						.hashCode());
		result = prime
				* result
				+ ((isDealFinalizationPermissionGiven == null) ? 0
						: isDealFinalizationPermissionGiven.hashCode());
		result = prime
				* result
				+ ((isFreightRequestPermissionGiven == null) ? 0
						: isFreightRequestPermissionGiven.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
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
		DriverSimplifiedAng other = (DriverSimplifiedAng) obj;
		if (bloodGroup == null) {
			if (other.bloodGroup != null)
				return false;
		} else if (!bloodGroup.equals(other.bloodGroup))
			return false;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		if (drivingSince == null) {
			if (other.drivingSince != null)
				return false;
		} else if (!drivingSince.equals(other.drivingSince))
			return false;
		if (employedSince == null) {
			if (other.employedSince != null)
				return false;
		} else if (!employedSince.equals(other.employedSince))
			return false;
		if (homeContactNumber == null) {
			if (other.homeContactNumber != null)
				return false;
		} else if (!homeContactNumber.equals(other.homeContactNumber))
			return false;
		if (isDealFinalizationPermissionGiven == null) {
			if (other.isDealFinalizationPermissionGiven != null)
				return false;
		} else if (!isDealFinalizationPermissionGiven
				.equals(other.isDealFinalizationPermissionGiven))
			return false;
		if (isFreightRequestPermissionGiven == null) {
			if (other.isFreightRequestPermissionGiven != null)
				return false;
		} else if (!isFreightRequestPermissionGiven
				.equals(other.isFreightRequestPermissionGiven))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
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
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}




	

}
