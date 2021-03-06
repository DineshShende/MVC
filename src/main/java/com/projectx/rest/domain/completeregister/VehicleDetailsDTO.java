package com.projectx.rest.domain.completeregister;

import java.util.Date;









import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.mvc.util.serializer.JsonDateDeSerializer;
import com.projectx.mvc.util.serializer.JsonDateSerializer;




public class VehicleDetailsDTO {

	private Long vehicleId;
	
	@NotEmpty
	private String ownerFirstName;
	
	@NotEmpty
	private String ownerMiddleName;
	
	@NotEmpty
	private String ownerLastName;
	
	
	//private VehicleTypeDetails vehicleTypeId;
	
	private VehicleBrandDetails vehicleBrandId;
	
	@NotEmpty
	private String vehicleBodyType;
	
	@NotNull
	private Boolean isBodyTypeFlexible;
	
	@NotEmpty
	private String registrationNumber;
	
	@NotEmpty
	private String chassisNumber;
	
	@NotNull
	private Integer loadCapacityInTons;
	
	@NotNull
	private Integer length;
	
	@NotNull
	private Integer width;
	
	@NotNull
	private Integer height;
	
	@NotNull
	private Integer numberOfWheels;
	
	
	@NotEmpty
	private String permitType;
	
	@NotNull
	private Boolean insuranceStatus;
	
	@NotEmpty
	private String insuranceNumber;
	
	@NotEmpty
	private String insuranceCompany;
	
	@NotNull
	private Long vendorId;

	@NotNull
	private Boolean isSimplifiedRegistration;
	
	@NotNull
	private Boolean isDetailRegistrationCompleted;
	
	private List<String> commodityList;
	
	private Boolean isVerified;
	
	private Date insertTime;
	
	private Date updateTime;
	
	private Integer updatedBy;
	
	private Integer insertedBy;
	
	private Long updatedById;
	
	private Long insertedById;

	public VehicleDetailsDTO() {

	}


	

	public VehicleDetailsDTO(Long vehicleId, String ownerFirstName,
			String ownerMiddleName, String ownerLastName,
			VehicleBrandDetails vehicleBrandId, String vehicleBodyType,
			Boolean isBodyTypeFlexible, String registrationNumber,
			String chassisNumber, Integer loadCapacityInTons, Integer length,
			Integer width, Integer height, Integer numberOfWheels,
			String permitType, Boolean insuranceStatus, String insuranceNumber,
			String insuranceCompany, Long vendorId,
			Boolean isSimplifiedRegistration,
			Boolean isDetailRegistrationCompleted, List<String> commodityList,Boolean isVerified,
			Date insertTime, Date updateTime, Integer updatedBy,
			Integer insertedBy, Long updatedById, Long insertedById) {
		super();
		this.vehicleId = vehicleId;
		this.ownerFirstName = ownerFirstName;
		this.ownerMiddleName = ownerMiddleName;
		this.ownerLastName = ownerLastName;
		this.vehicleBrandId = vehicleBrandId;
		this.vehicleBodyType = vehicleBodyType;
		this.isBodyTypeFlexible = isBodyTypeFlexible;
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
		this.loadCapacityInTons = loadCapacityInTons;
		this.length = length;
		this.width = width;
		this.height = height;
		this.numberOfWheels = numberOfWheels;
		this.permitType = permitType;
		this.insuranceStatus = insuranceStatus;
		this.insuranceNumber = insuranceNumber;
		this.insuranceCompany = insuranceCompany;
		this.vendorId = vendorId;
		this.isSimplifiedRegistration = isSimplifiedRegistration;
		this.isDetailRegistrationCompleted = isDetailRegistrationCompleted;
		this.commodityList = commodityList;
		this.isVerified=isVerified;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy = insertedBy;
		this.updatedById = updatedById;
		this.insertedById = insertedById;
	}




	public Boolean getIsSimplifiedRegistration() {
		return isSimplifiedRegistration;
	}



	public void setIsSimplifiedRegistration(Boolean isSimplifiedRegistration) {
		this.isSimplifiedRegistration = isSimplifiedRegistration;
	}



	public Boolean getIsDetailRegistrationCompleted() {
		return isDetailRegistrationCompleted;
	}



	public void setIsDetailRegistrationCompleted(
			Boolean isDetailRegistrationCompleted) {
		this.isDetailRegistrationCompleted = isDetailRegistrationCompleted;
	}



	public Long getVehicleId() {
		return vehicleId;
	}



	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
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


	public VehicleBrandDetails getVehicleBrandId() {
		return vehicleBrandId;
	}



	public void setVehicleBrandId(VehicleBrandDetails vehicleBrandId) {
		this.vehicleBrandId = vehicleBrandId;
	}



	public String getVehicleBodyType() {
		return vehicleBodyType;
	}



	public void setVehicleBodyType(String vehicleBodyType) {
		this.vehicleBodyType = vehicleBodyType;
	}



	public Boolean getIsBodyTypeFlexible() {
		return isBodyTypeFlexible;
	}



	public void setIsBodyTypeFlexible(Boolean isBodyTypeFlexible) {
		this.isBodyTypeFlexible = isBodyTypeFlexible;
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



	public Integer getLoadCapacityInTons() {
		return loadCapacityInTons;
	}



	public void setLoadCapacityInTons(Integer loadCapacityInTons) {
		this.loadCapacityInTons = loadCapacityInTons;
	}



	public Integer getNumberOfWheels() {
		return numberOfWheels;
	}



	public void setNumberOfWheels(Integer numberOfVehicle) {
		this.numberOfWheels = numberOfVehicle;
	}



	public String getPermitType() {
		return permitType;
	}



	public void setPermitType(String permitType) {
		this.permitType = permitType;
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



	public Long getVendorId() {
		return vendorId;
	}



	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
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

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}


	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}


	public List<String> getCommodityList() {
		return commodityList;
	}


	public void setCommodityList(List<String> commodityList) {
		this.commodityList = commodityList;
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
		return "VehicleDetailsDTO [vehicleId=" + vehicleId
				+ ", ownerFirstName=" + ownerFirstName + ", ownerMiddleName="
				+ ownerMiddleName + ", ownerLastName=" + ownerLastName
				+ ", vehicleBrandId=" + vehicleBrandId + ", vehicleBodyType="
				+ vehicleBodyType + ", isBodyTypeFlexible="
				+ isBodyTypeFlexible + ", registrationNumber="
				+ registrationNumber + ", chassisNumber=" + chassisNumber
				+ ", loadCapacityInTons=" + loadCapacityInTons + ", length="
				+ length + ", width=" + width + ", height=" + height
				+ ", numberOfWheels=" + numberOfWheels + ", permitType="
				+ permitType + ", insuranceStatus=" + insuranceStatus
				+ ", insuranceNumber=" + insuranceNumber
				+ ", insuranceCompany=" + insuranceCompany + ", vendorId="
				+ vendorId + ", isSimplifiedRegistration="
				+ isSimplifiedRegistration + ", isDetailRegistrationCompleted="
				+ isDetailRegistrationCompleted + ", commodityList="
				+ commodityList + ", isVerified=" + isVerified
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", updatedBy=" + updatedBy + ", insertedBy=" + insertedBy
				+ ", updatedById=" + updatedById + ", insertedById="
				+ insertedById + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chassisNumber == null) ? 0 : chassisNumber.hashCode());
		result = prime * result
				+ ((commodityList == null) ? 0 : commodityList.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
		result = prime
				* result
				+ ((insuranceCompany == null) ? 0 : insuranceCompany.hashCode());
		result = prime * result
				+ ((insuranceNumber == null) ? 0 : insuranceNumber.hashCode());
		result = prime * result
				+ ((insuranceStatus == null) ? 0 : insuranceStatus.hashCode());
		result = prime
				* result
				+ ((isBodyTypeFlexible == null) ? 0 : isBodyTypeFlexible
						.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime
				* result
				+ ((loadCapacityInTons == null) ? 0 : loadCapacityInTons
						.hashCode());
		result = prime * result
				+ ((numberOfWheels == null) ? 0 : numberOfWheels.hashCode());
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
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
		result = prime * result
				+ ((vehicleBodyType == null) ? 0 : vehicleBodyType.hashCode());
		result = prime * result
				+ ((vehicleBrandId == null) ? 0 : vehicleBrandId.hashCode());
		result = prime * result
				+ ((vehicleId == null) ? 0 : vehicleId.hashCode());
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		VehicleDetailsDTO other = (VehicleDetailsDTO) obj;
		if (chassisNumber == null) {
			if (other.chassisNumber != null)
				return false;
		} else if (!chassisNumber.equals(other.chassisNumber))
			return false;
		if (commodityList == null) {
			if (other.commodityList != null)
				return false;
		} else if (!commodityList.equals(other.commodityList))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		}
		if (insertedBy == null) {
			if (other.insertedBy != null)
				return false;
		} else if (!insertedBy.equals(other.insertedBy))
			return false;
		if (insertedById == null) {
			if (other.insertedById != null)
				return false;
		} 
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
		if (isBodyTypeFlexible == null) {
			if (other.isBodyTypeFlexible != null)
				return false;
		} else if (!isBodyTypeFlexible.equals(other.isBodyTypeFlexible))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (loadCapacityInTons == null) {
			if (other.loadCapacityInTons != null)
				return false;
		} else if (!loadCapacityInTons.equals(other.loadCapacityInTons))
			return false;
		if (numberOfWheels == null) {
			if (other.numberOfWheels != null)
				return false;
		} else if (!numberOfWheels.equals(other.numberOfWheels))
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
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		}
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		} 
		if (vehicleBodyType == null) {
			if (other.vehicleBodyType != null)
				return false;
		} else if (!vehicleBodyType.equals(other.vehicleBodyType))
			return false;
		if (vehicleBrandId == null) {
			if (other.vehicleBrandId != null)
				return false;
		} else if (!vehicleBrandId.equals(other.vehicleBrandId))
			return false;
		if (vehicleId == null) {
			if (other.vehicleId != null)
				return false;
		} else if (!vehicleId.equals(other.vehicleId))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		if (isVerified == null) {
			if (other.isVerified != null)
				return false;
		} else if (!isVerified.equals(other.isVerified))
			return false;
		return true;
	}


		
	
}
