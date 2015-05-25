package com.projectx.mvc.services.completeregister;

import java.util.List;

import com.projectx.mvc.domain.completeregister.DLClassOfVehicle;

public interface DLClassOfVehicleService {

	DLClassOfVehicle save(DLClassOfVehicle dLClassOfVehicle);
	
	DLClassOfVehicle getById(Long dLClassOfVehicleId);
	
	List<DLClassOfVehicle> getAll();
	
	Boolean deleteById(Long dLClassOfVehicleId);
	
	Boolean deleteAll();
	
}
