package com.projectx.mvc.domain.completeregister;

import javax.validation.constraints.NotNull;

public class VehicleTypeDTO {
	
	private Long vehicleTypeId;
	
	@NotNull
	private String vehicleTypeName;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public VehicleTypeDTO() {
		super();
	}

	public VehicleTypeDTO(Long vehicleTypeId, String vehicleTypeName,
			Integer requestedBy, Long requestedById) {
		super();
		this.vehicleTypeId = vehicleTypeId;
		this.vehicleTypeName = vehicleTypeName;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getVehicleTypeName() {
		return vehicleTypeName;
	}

	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
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
		return "VehicleTypeDTO [vehicleTypeId=" + vehicleTypeId
				+ ", vehicleTypeName=" + vehicleTypeName + ", requestedBy="
				+ requestedBy + ", requestedById=" + requestedById + "]";
	}
	
	

}
