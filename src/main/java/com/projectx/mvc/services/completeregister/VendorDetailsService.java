package com.projectx.mvc.services.completeregister;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;


@Service
public interface VendorDetailsService {

	VendorDetailsDTO createCustomerDetailsFromQuickRegisterEntity( QuickRegisterDTO quickRegisterEntity);
	
	VendorDetailsDTO update(VendorDetailsDTO vendorDetails);
	
	VendorDetailsDTO getCustomerDetailsById( Long vendorId);
	
	VendorDetailsDTO intializeMetaData(VendorDetailsDTO vendorDetailsDTO);
	
	Boolean verifyMobileDetails(VerifyMobileDTO verifyMobileDTO);
	
	Boolean verifyEmailDetails( VerifyEmailDTO verifyEmailDTO);
	
	Boolean sendMobileVerificationDetails(CustomerIdTypeMobileTypeDTO customerIdTypeMobileDTO);
	
	Boolean sendEmailVerificationDetails(CustomerIdTypeEmailTypeDTO customerIdTypeEmailDTO);
	
	Model initialiseShowVendorDetails(Long entityId,Model model);
	
	Integer count();
	
	Boolean clearTestData();
	
	
}
