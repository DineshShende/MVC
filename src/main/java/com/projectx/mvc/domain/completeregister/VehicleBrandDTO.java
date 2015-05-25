package com.projectx.mvc.domain.completeregister;

import javax.validation.constraints.NotNull;

public class VehicleBrandDTO {
	
	
	private Long vehicleBrandId;

	@NotNull
	private String vehicleBrandName;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public VehicleBrandDTO() {
		super();
	}

	public VehicleBrandDTO(Long vehicleBrandId, String vehicleBrandName,
			Integer requestedBy, Long requestedById) {
		super();
		this.vehicleBrandId = vehicleBrandId;
		this.vehicleBrandName = vehicleBrandName;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}

	public Long getVehicleBrandId() {
		return vehicleBrandId;
	}

	public void setVehicleBrandId(Long vehicleBrandId) {
		this.vehicleBrandId = vehicleBrandId;
	}

	public String getVehicleBrandName() {
		return vehicleBrandName;
	}

	public void setVehicleBrandName(String vehicleBrandName) {
		this.vehicleBrandName = vehicleBrandName;
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
		return "VehicleBrandDTO [vehicleBrandId=" + vehicleBrandId
				+ ", vehicleBrandName=" + vehicleBrandName + ", requestedBy="
				+ requestedBy + ", requestedById=" + requestedById + "]";
	}
	
	

}
