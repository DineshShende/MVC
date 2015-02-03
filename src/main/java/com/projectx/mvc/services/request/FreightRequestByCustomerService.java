package com.projectx.mvc.services.request;

import java.util.List;

import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;

public interface FreightRequestByCustomerService {

	FreightRequestByCustomer save(FreightRequestByCustomer freightRequestByCustomer);
	
	FreightRequestByCustomer getRequestById(Long requestId);
	
	List<FreightRequestByCustomer> getAllRequestForCustomer(Long customerId);
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
}
