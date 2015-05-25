package com.projectx.mvc.services.completeregister;

import java.util.List;

import com.projectx.mvc.domain.completeregister.VehicleType;



public interface VehicleTypeService {

	VehicleType save(VehicleType vehicleType);
	
	VehicleType getById(Long vehicleTypeId);
	
	List<VehicleType> getAll();
	
	Boolean deleteById(Long vehicleTypeId);
	
	Boolean deleteAll();
}
