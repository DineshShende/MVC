package com.projectx.mvc.services.completeregister;

import java.util.List;

import com.projectx.mvc.domain.completeregister.VehicleModel;



public interface VehicleModelService {

	VehicleModel save(VehicleModel vehicleModel);
	
	VehicleModel getById(Long vehicleModelId);
	
	List<VehicleModel> getAll();
	
	Boolean deleteById(Long vehicleModelId);
	
	Boolean deleteAll();
	
}
