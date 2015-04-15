package com.projectx.mvc.servicefixtures.completeregister;

import org.springframework.ui.Model;

import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

public class CustomerDetailsServiceFixture implements CustomerDetailsService {

	@Override
	public CustomerDetailsDTO createCustomerDetailsFromQuickRegisterEntity(
			Long entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDetailsDTO merge(CustomerDetailsDTO customerDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDetailsDTO getCustomerDetailsById(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDetailsDTO InitializeMetaData(
			CustomerDetailsDTO customerDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model initialiseShowCustomerDetails(Long customerId, Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean clearTestData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String awsTest() {
		// TODO Auto-generated method stub
		return null;
	}

	
		
}
