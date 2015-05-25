package com.projectx.mvc.services.completeregister;

import java.util.List;

import com.projectx.mvc.domain.completeregister.VehicleBrand;



public interface VehicleBrandTypeService {

	VehicleBrand save(VehicleBrand vehicleBrand);
	
	VehicleBrand getById(Long vehicleBrandId);
	
	List<VehicleBrand> getAll();
	
	Boolean deleteById(Long vehicleBrandId);
	
	Boolean deleteAll();
}
