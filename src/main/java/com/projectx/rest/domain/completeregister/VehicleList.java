package com.projectx.rest.domain.completeregister;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;


public class VehicleList {

	List<VehicleDetailsDTO> vehicleList;

	@JsonCreator
	public VehicleList(List<VehicleDetailsDTO> vehicleList) {
		super();
		this.vehicleList = vehicleList;
	}

	public List<VehicleDetailsDTO> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<VehicleDetailsDTO> vehicleList) {
		this.vehicleList = vehicleList;
	}
	
	
}
