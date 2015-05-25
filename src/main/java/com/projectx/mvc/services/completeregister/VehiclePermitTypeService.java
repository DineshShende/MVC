package com.projectx.mvc.services.completeregister;

import java.util.List;

import com.projectx.mvc.domain.completeregister.VehiclePermitType;



public interface VehiclePermitTypeService {

	VehiclePermitType save(VehiclePermitType vehiclePermitType);
	
	VehiclePermitType getById(Long vehiclePermitTypeId);
	
	List<VehiclePermitType> getAll();
	
	Boolean deleteById(Long vehiclePermitTypeId);
	
	Boolean deleteAll();
	
}
