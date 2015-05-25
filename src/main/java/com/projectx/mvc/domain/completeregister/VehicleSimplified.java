package com.projectx.mvc.domain.completeregister;

import java.util.List;

import javax.validation.constraints.NotNull;

public class VehicleSimplified {

	private Long vehicleId;
	
	@NotNull
	private String vehicleBodyType;

	@NotNull
	private Boolean isBodyTypeFlexible;

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
	
	@NotNull
	private Long vendorId;
	

	private List<String> commodityList;
	
	private Integer requestedBy;
	
	private Long requestedById;

	public VehicleSimplified() {
		super();
	}

	
	public VehicleSimplified(Long vehicleId, String vehicleBodyType,
			Boolean isBodyTypeFlexible, Integer loadCapacityInTons,
			Integer length, Integer width, Integer height,
			Integer numberOfWheels,Long vendorId, List<String> commodityList,
			Integer requestedBy, Long requestedById) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleBodyType = vehicleBodyType;
		this.isBodyTypeFlexible = isBodyTypeFlexible;
		this.loadCapacityInTons = loadCapacityInTons;
		this.length = length;
		this.width = width;
		this.height = height;
		this.numberOfWheels = numberOfWheels;
		this.vendorId=vendorId;
		this.commodityList = commodityList;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}

	public VehicleSimplified(String vehicleBodyType,
			Boolean isBodyTypeFlexible, Integer loadCapacityInTons,
			Integer length, Integer width, Integer height,
			Integer numberOfWheels,Long vendorId, List<String> commodityList,
			Integer requestedBy, Long requestedById) {
		super();
		this.vehicleBodyType = vehicleBodyType;
		this.isBodyTypeFlexible = isBodyTypeFlexible;
		this.loadCapacityInTons = loadCapacityInTons;
		this.length = length;
		this.width = width;
		this.height = height;
		this.numberOfWheels = numberOfWheels;
		this.vendorId=vendorId;
		this.commodityList = commodityList;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}




	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
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

	public List<String> getCommodityList() {
		return commodityList;
	}

	public void setCommodityList(List<String> commodityList) {
		this.commodityList = commodityList;
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
		return "VehicleSimplified [vehicleId=" + vehicleId
				+ ", vehicleBodyType=" + vehicleBodyType
				+ ", isBodyTypeFlexible=" + isBodyTypeFlexible
				+ ", loadCapacityInTons=" + loadCapacityInTons + ", length="
				+ length + ", width=" + width + ", height=" + height
				+ ", numberOfWheels=" + numberOfWheels + ", vendorId="
				+ vendorId + ", commodityList=" + commodityList
				+ ", requestedBy=" + requestedBy + ", requestedById="
				+ requestedById + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commodityList == null) ? 0 : commodityList.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
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
				+ ((vehicleBodyType == null) ? 0 : vehicleBodyType.hashCode());
		result = prime * result
				+ ((vehicleId == null) ? 0 : vehicleId.hashCode());
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
		VehicleSimplified other = (VehicleSimplified) obj;
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
		if (vehicleBodyType == null) {
			if (other.vehicleBodyType != null)
				return false;
		} else if (!vehicleBodyType.equals(other.vehicleBodyType))
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
		return true;
	}

	
	
	
}
