package com.projectx.mvc.domain.completeregister;

import javax.validation.constraints.NotNull;

public class L1VehicleCompleteRegistration {
	
	private Long vehicleDetailsId;
	
	@NotNull
	private String ownerFirstName;

	private String ownerMiddleName;
	
	@NotNull
	private String ownerLastName;


	private String vehicleBrandName;
	
	private String modelNumber;

	private String vehicleTypeName;
	
	@NotNull
	private String registrationNumber;
	
	@NotNull
	private String chassisNumber;

	@NotNull
	private Boolean insuranceStatus;
	
	private String insuranceNumber;
	
	private String insuranceCompany;

	@NotNull
	private String permitType;

	@NotNull
	private Boolean isDetailRegistrationCompleted;

	public L1VehicleCompleteRegistration() {
		super();
	}

	
	public L1VehicleCompleteRegistration(Long vehicleDetailsId,
			String ownerFirstName, String ownerMiddleName,
			String ownerLastName, String vehicleBrandName, String modelNumber,
			String vehicleTypeName, String registrationNumber,
			String chassisNumber, Boolean insuranceStatus,
			String insuranceNumber, String insuranceCompany, String permitType,
			Boolean isDetailRegistrationCompleted) {
		super();
		this.vehicleDetailsId = vehicleDetailsId;
		this.ownerFirstName = ownerFirstName;
		this.ownerMiddleName = ownerMiddleName;
		this.ownerLastName = ownerLastName;
		this.vehicleBrandName = vehicleBrandName;
		this.modelNumber = modelNumber;
		this.vehicleTypeName = vehicleTypeName;
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
		this.insuranceStatus = insuranceStatus;
		this.insuranceNumber = insuranceNumber;
		this.insuranceCompany = insuranceCompany;
		this.permitType = permitType;
		this.isDetailRegistrationCompleted = isDetailRegistrationCompleted;
	}


	public L1VehicleCompleteRegistration(String ownerFirstName,
			String ownerMiddleName, String ownerLastName,
			String vehicleBrandName, String modelNumber,
			String vehicleTypeName, String registrationNumber,
			String chassisNumber, Boolean insuranceStatus,
			String insuranceNumber, String insuranceCompany, String permitType,
			Boolean isDetailRegistrationCompleted) {
		super();
		this.ownerFirstName = ownerFirstName;
		this.ownerMiddleName = ownerMiddleName;
		this.ownerLastName = ownerLastName;
		this.vehicleBrandName = vehicleBrandName;
		this.modelNumber = modelNumber;
		this.vehicleTypeName = vehicleTypeName;
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
		this.insuranceStatus = insuranceStatus;
		this.insuranceNumber = insuranceNumber;
		this.insuranceCompany = insuranceCompany;
		this.permitType = permitType;
		this.isDetailRegistrationCompleted = isDetailRegistrationCompleted;
	}


	public Long getVehicleDetailsId() {
		return vehicleDetailsId;
	}

	public void setVehicleDetailsId(Long vehicleDetailsId) {
		this.vehicleDetailsId = vehicleDetailsId;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerMiddleName() {
		return ownerMiddleName;
	}

	public void setOwnerMiddleName(String ownerMiddleName) {
		this.ownerMiddleName = ownerMiddleName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getVehicleBrandName() {
		return vehicleBrandName;
	}

	public void setVehicleBrandName(String vehicleBrandName) {
		this.vehicleBrandName = vehicleBrandName;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getVehicleTypeName() {
		return vehicleTypeName;
	}

	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}

	public Boolean getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(Boolean insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getPermitType() {
		return permitType;
	}

	public void setPermitType(String permitType) {
		this.permitType = permitType;
	}

	public Boolean getIsDetailRegistrationCompleted() {
		return isDetailRegistrationCompleted;
	}

	public void setIsDetailRegistrationCompleted(
			Boolean isDetailRegistrationCompleted) {
		this.isDetailRegistrationCompleted = isDetailRegistrationCompleted;
	}
	
	

	public String getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public String getChassisNumber() {
		return chassisNumber;
	}


	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}


	@Override
	public String toString() {
		return "L1VehicleCompleteRegistration [vehicleDetailsId="
				+ vehicleDetailsId + ", ownerFirstName=" + ownerFirstName
				+ ", ownerMiddleName=" + ownerMiddleName + ", ownerLastName="
				+ ownerLastName + ", vehicleBrandName=" + vehicleBrandName
				+ ", modelNumber=" + modelNumber + ", vehicleTypeName="
				+ vehicleTypeName + ", registrationNumber="
				+ registrationNumber + ", chassisNumber=" + chassisNumber
				+ ", insuranceStatus=" + insuranceStatus + ", insuranceNumber="
				+ insuranceNumber + ", insuranceCompany=" + insuranceCompany
				+ ", permitType=" + permitType
				+ ", isDetailRegistrationCompleted="
				+ isDetailRegistrationCompleted + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chassisNumber == null) ? 0 : chassisNumber.hashCode());
		result = prime
				* result
				+ ((insuranceCompany == null) ? 0 : insuranceCompany.hashCode());
		result = prime * result
				+ ((insuranceNumber == null) ? 0 : insuranceNumber.hashCode());
		result = prime * result
				+ ((insuranceStatus == null) ? 0 : insuranceStatus.hashCode());
		result = prime
				* result
				+ ((isDetailRegistrationCompleted == null) ? 0
						: isDetailRegistrationCompleted.hashCode());
		result = prime * result
				+ ((modelNumber == null) ? 0 : modelNumber.hashCode());
		result = prime * result
				+ ((ownerFirstName == null) ? 0 : ownerFirstName.hashCode());
		result = prime * result
				+ ((ownerLastName == null) ? 0 : ownerLastName.hashCode());
		result = prime * result
				+ ((ownerMiddleName == null) ? 0 : ownerMiddleName.hashCode());
		result = prime * result
				+ ((permitType == null) ? 0 : permitType.hashCode());
		result = prime
				* result
				+ ((registrationNumber == null) ? 0 : registrationNumber
						.hashCode());
		result = prime
				* result
				+ ((vehicleBrandName == null) ? 0 : vehicleBrandName.hashCode());
		result = prime
				* result
				+ ((vehicleDetailsId == null) ? 0 : vehicleDetailsId.hashCode());
		result = prime * result
				+ ((vehicleTypeName == null) ? 0 : vehicleTypeName.hashCode());
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
		L1VehicleCompleteRegistration other = (L1VehicleCompleteRegistration) obj;
		if (chassisNumber == null) {
			if (other.chassisNumber != null)
				return false;
		} else if (!chassisNumber.equals(other.chassisNumber))
			return false;
		if (insuranceCompany == null) {
			if (other.insuranceCompany != null)
				return false;
		} else if (!insuranceCompany.equals(other.insuranceCompany))
			return false;
		if (insuranceNumber == null) {
			if (other.insuranceNumber != null)
				return false;
		} else if (!insuranceNumber.equals(other.insuranceNumber))
			return false;
		if (insuranceStatus == null) {
			if (other.insuranceStatus != null)
				return false;
		} else if (!insuranceStatus.equals(other.insuranceStatus))
			return false;
		if (isDetailRegistrationCompleted == null) {
			if (other.isDetailRegistrationCompleted != null)
				return false;
		} else if (!isDetailRegistrationCompleted
				.equals(other.isDetailRegistrationCompleted))
			return false;
		if (modelNumber == null) {
			if (other.modelNumber != null)
				return false;
		} else if (!modelNumber.equals(other.modelNumber))
			return false;
		if (ownerFirstName == null) {
			if (other.ownerFirstName != null)
				return false;
		} else if (!ownerFirstName.equals(other.ownerFirstName))
			return false;
		if (ownerLastName == null) {
			if (other.ownerLastName != null)
				return false;
		} else if (!ownerLastName.equals(other.ownerLastName))
			return false;
		if (ownerMiddleName == null) {
			if (other.ownerMiddleName != null)
				return false;
		} else if (!ownerMiddleName.equals(other.ownerMiddleName))
			return false;
		if (permitType == null) {
			if (other.permitType != null)
				return false;
		} else if (!permitType.equals(other.permitType))
			return false;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		if (vehicleBrandName == null) {
			if (other.vehicleBrandName != null)
				return false;
		} else if (!vehicleBrandName.equals(other.vehicleBrandName))
			return false;
		if (vehicleDetailsId == null) {
			if (other.vehicleDetailsId != null)
				return false;
		} else if (!vehicleDetailsId.equals(other.vehicleDetailsId))
			return false;
		if (vehicleTypeName == null) {
			if (other.vehicleTypeName != null)
				return false;
		} else if (!vehicleTypeName.equals(other.vehicleTypeName))
			return false;
		return true;
	}


		

}
