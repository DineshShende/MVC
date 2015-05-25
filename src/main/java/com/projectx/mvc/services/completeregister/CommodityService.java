package com.projectx.mvc.services.completeregister;

import java.util.List;

import com.projectx.mvc.domain.completeregister.Commodity;



public interface CommodityService {

	Commodity save(Commodity Commodity);
	
	Commodity getById(Long CommodityId);
	
	List<Commodity> getAll();
	
	Boolean deleteById(Long CommodityId);
	
	Boolean deleteAll();
}
