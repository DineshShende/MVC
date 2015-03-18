package com.projectx.mvc.services.completeregister;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeUpdatedByDTO;
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
	
	Boolean sendMobileVerificationDetails(CustomerIdTypeMobileTypeUpdatedByDTO customerIdTypeMobileDTO);
	
	Boolean sendEmailVerificationDetails(CustomerIdTypeEmailTypeUpdatedByDTO customerIdTypeEmailDTO);
	
	public Model initialiseShowCustomerDetails(Long customerId,Model model);
	
	Integer count();
	
	Boolean clearTestData();

}
