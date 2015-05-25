package com.projectx.mvc.services.completeregister;

import java.util.List;

import com.projectx.mvc.domain.completeregister.VehicleBodyType;



public interface VehicleBodyTypeService {

	VehicleBodyType save(VehicleBodyType vehicleBodyType);
	
	VehicleBodyType getById(Long vehicleBodyTypeId);
	
	List<VehicleBodyType> getAll();
	
	Boolean deleteById(Long vehicleBodyTypeId);
	
	Boolean deleteAll();
}
