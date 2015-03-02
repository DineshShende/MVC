package com.projectx.mvc.services.request;

import java.util.List;

import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

public interface FreightRequestByCustomerService {

	FreightRequestByCustomer save(FreightRequestByCustomer freightRequestByCustomer)
									throws ResourceAlreadyPresentException,ValidationFailedException;
	
	FreightRequestByCustomer getRequestById(Long requestId) throws ResourceNotFoundException;
	
	List<FreightRequestByCustomer> getAllRequestForCustomer(Long customerId);
	
	List<FreightRequestByCustomer> getCustmerReqForVendorReq(FreightRequestByVendorDTO freightRequestByVendorDTO);
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
}
