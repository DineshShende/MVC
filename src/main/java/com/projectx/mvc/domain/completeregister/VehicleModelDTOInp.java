package com.projectx.mvc.domain.completeregister;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

public class VehicleModelDTOInp {
	
	private Long vehicleModeId;
	
	@NotNull
	private VehicleBrand vehicleBrand;
	
	@NotNull
	private VehicleType vehicleType;
	
	@NotNull
	private String vehiclemodelName;
	
	@NotNull
	private byte[] vehiclePhoto;
	
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public VehicleModelDTOInp() {
		super();
	}

	public VehicleModelDTOInp(Long vehicleModeId, VehicleBrand vehicleBrand,
			VehicleType vehicleType, String vehiclemodelName,
			byte[] vehiclePhoto, Integer requestedBy, Long requestedById) {
		super();
		this.vehicleModeId = vehicleModeId;
		this.vehicleBrand = vehicleBrand;
		this.vehicleType = vehicleType;
		this.vehiclemodelName = vehiclemodelName;
		this.vehiclePhoto = vehiclePhoto;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}

	public Long getVehicleModeId() {
		return vehicleModeId;
	}

	public void setVehicleModeId(Long vehicleModeId) {
		this.vehicleModeId = vehicleModeId;
	}

	public VehicleBrand getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(VehicleBrand vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehiclemodelName() {
		return vehiclemodelName;
	}

	public void setVehiclemodelName(String vehiclemodelName) {
		this.vehiclemodelName = vehiclemodelName;
	}

	public byte[] getVehiclePhoto() {
		return vehiclePhoto;
	}

	public void setVehiclePhoto(byte[] vehiclePhoto) {
		this.vehiclePhoto = vehiclePhoto;
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
		return "VehicleModelDTOInp [vehicleModeId=" + vehicleModeId
				+ ", vehicleBrand=" + vehicleBrand + ", vehicleType="
				+ vehicleType + ", vehiclemodelName=" + vehiclemodelName
				+ ", vehiclePhoto=" + Arrays.toString(vehiclePhoto)
				+ ", requestedBy=" + requestedBy + ", requestedById="
				+ requestedById + "]";
	}

		
	

}
