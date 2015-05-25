package com.projectx.mvc.domain.completeregister;

import javax.validation.constraints.NotNull;

public class VehiclePermitTypeDTO {

	private Long vehiclePermitTypeId;
	
	@NotNull
	private String vehiclePermitTypeName;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public VehiclePermitTypeDTO() {
		super();
	}

	public VehiclePermitTypeDTO(Long vehiclePermitTypeId,
			String vehiclePermitTypeName, Integer requestedBy,
			Long requestedById) {
		super();
		this.vehiclePermitTypeId = vehiclePermitTypeId;
		this.vehiclePermitTypeName = vehiclePermitTypeName;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}

	public Long getVehiclePermitTypeId() {
		return vehiclePermitTypeId;
	}

	public void setVehiclePermitTypeId(Long vehiclePermitTypeId) {
		this.vehiclePermitTypeId = vehiclePermitTypeId;
	}

	public String getVehiclePermitTypeName() {
		return vehiclePermitTypeName;
	}

	public void setVehiclePermitTypeName(String vehiclePermitTypeName) {
		this.vehiclePermitTypeName = vehiclePermitTypeName;
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
		return "VehiclePermitTypeDTO [vehiclePermitTypeId="
				+ vehiclePermitTypeId + ", vehiclePermitTypeName="
				+ vehiclePermitTypeName + ", requestedBy=" + requestedBy
				+ ", requestedById=" + requestedById + "]";
	}
	
	
	
}
