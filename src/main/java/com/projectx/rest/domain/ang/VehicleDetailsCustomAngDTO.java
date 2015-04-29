package com.projectx.rest.domain.ang;

import com.projectx.rest.domain.completeregister.VehicleBrandDetails;

public class VehicleDetailsCustomAngDTO {
	
	
	private VehicleBrandDetails vehicleBrandId;
	
	private String vehicleBodyType;
	
    private Integer loadCapacityInTons;
    
    private Integer length;
    
    private Integer width;
    
    private Integer height;
    
    private Integer numberOfWheels;
    
    private String permitType;
    
    private Boolean insuranceStatus;

	public VehicleDetailsCustomAngDTO() {
		super();
	}

	public VehicleDetailsCustomAngDTO(VehicleBrandDetails vehicleBrandId,
			String vehicleBodyType, Integer loadCapacityInTons, Integer length,
			Integer width, Integer height, Integer numberOfWheels,
			String permitType, Boolean insuranceStatus) {
		super();
		this.vehicleBrandId = vehicleBrandId;
		this.vehicleBodyType = vehicleBodyType;
		this.loadCapacityInTons = loadCapacityInTons;
		this.length = length;
		this.width = width;
		this.height = height;
		this.numberOfWheels = numberOfWheels;
		this.permitType = permitType;
		this.insuranceStatus = insuranceStatus;
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

	public Integer getLoadCapacityInTons() {
		return loadCapacityInTons;
	}

	public void setLoadCapacityInTons(Integer loadCapacityInTons) {
		this.loadCapacityInTons = loadCapacityInTons;
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

	public Integer getNumberOfWheels() {
		return numberOfWheels;
	}

	public void setNumberOfWheels(Integer numberOfWheels) {
		this.numberOfWheels = numberOfWheels;
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

	@Override
	public String toString() {
		return "VehicleDetailsCustomAngDTO [vehicleBrandId=" + vehicleBrandId
				+ ", vehicleBodyType=" + vehicleBodyType
				+ ", loadCapacityInTons=" + loadCapacityInTons + ", length="
				+ length + ", width=" + width + ", height=" + height
				+ ", numberOfWheels=" + numberOfWheels + ", permitType="
				+ permitType + ", insuranceStatus=" + insuranceStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result
				+ ((insuranceStatus == null) ? 0 : insuranceStatus.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime
				* result
				+ ((loadCapacityInTons == null) ? 0 : loadCapacityInTons
						.hashCode());
		result = prime * result
				+ ((numberOfWheels == null) ? 0 : numberOfWheels.hashCode());
		result = prime * result
				+ ((permitType == null) ? 0 : permitType.hashCode());
		result = prime * result
				+ ((vehicleBodyType == null) ? 0 : vehicleBodyType.hashCode());
		result = prime * result
				+ ((vehicleBrandId == null) ? 0 : vehicleBrandId.hashCode());
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
		VehicleDetailsCustomAngDTO other = (VehicleDetailsCustomAngDTO) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (insuranceStatus == null) {
			if (other.insuranceStatus != null)
				return false;
		} else if (!insuranceStatus.equals(other.insuranceStatus))
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
		if (permitType == null) {
			if (other.permitType != null)
				return false;
		} else if (!permitType.equals(other.permitType))
			return false;
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
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}

	

}
