package com.projectx.mvc.services.request;

import java.util.List;

import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;



public interface FreightRequestByVendorService {

	FreightRequestByVendorDTO save(FreightRequestByVendorDTO freightRequestByCustomer);
	
	FreightRequestByVendor getRequestById(Long requestId);
	
	List<FreightRequestByVendor> getAllRequestForVendor(Long vendorId);
	
	List<FreightRequestByVendor> getMatchingVendorReqForCustReq(FreightRequestByCustomerDTO freightRequestByCustomer);
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
}
