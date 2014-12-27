package com.projectx.mvc.services.completeregister;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@Service
public interface CustomerDetailsService {
	
	CustomerDetailsDTO createCustomerDetailsFromQuickRegisterEntity(QuickRegisterDTO quickRegisterEntity);
	
	CustomerDetailsDTO merge(CustomerDetailsDTO customerDetails);
	
	CustomerDetailsDTO getCustomerDetailsById(Long customerId);
	
	CustomerDetailsDTO InitializeMetaData(CustomerDetailsDTO customerDetails);
	
	Boolean verifyMobileDetails(VerifyMobileDTO verifyMobileDTO);
	
	Boolean verifyEmailDetails(VerifyEmailDTO verifyEmailDTO);
	
	Boolean sendMobileVerificationDetails(CustomerIdTypeMobileDTO customerIdTypeMobileDTO);
	
	Boolean sendEmailVerificationDetails(CustomerIdTypeEmailDTO customerIdTypeEmailDTO);
	
	Integer count();
	
	Boolean clearTestData();

}
