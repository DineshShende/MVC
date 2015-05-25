package com.projectx.mvc.domain.completeregister;

import javax.validation.constraints.NotNull;

public class VehicleBodyTypeDTO {
	
	private Long vehicleBodyTypeId;
	
	@NotNull
	private String vehicleBodyTypeName;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public VehicleBodyTypeDTO() {
		super();
	}

	public VehicleBodyTypeDTO(Long vehicleBodyTypeId,
			String vehicleBodyTypeName, Integer requestedBy, Long requestedById) {
		super();
		this.vehicleBodyTypeId = vehicleBodyTypeId;
		this.vehicleBodyTypeName = vehicleBodyTypeName;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}

	public Long getVehicleBodyTypeId() {
		return vehicleBodyTypeId;
	}

	public void setVehicleBodyTypeId(Long vehicleBodyTypeId) {
		this.vehicleBodyTypeId = vehicleBodyTypeId;
	}

	public String getVehicleBodyTypeName() {
		return vehicleBodyTypeName;
	}

	public void setVehicleBodyTypeName(String vehicleBodyTypeName) {
		this.vehicleBodyTypeName = vehicleBodyTypeName;
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

	@Override
	public String toString() {
		return "VehicleBodyTypeDTO [vehicleBodyTypeId=" + vehicleBodyTypeId
				+ ", vehicleBodyTypeName=" + vehicleBodyTypeName
				+ ", requestedBy=" + requestedBy + ", requestedById="
				+ requestedById + "]";
	}
	
	
	
	

}
