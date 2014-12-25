package com.projectx.mvc.servicefixtures.completeregister;

import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

public class CustomerDetailsServiceFixture implements CustomerDetailsService {

	@Override
	public CustomerDetailsDTO createCustomerDetailsFromQuickRegisterEntity(
			QuickRegisterDTO quickRegisterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDetailsDTO merge(CustomerDetailsDTO customerDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verifyMobileDetails(VerifyMobileDTO verifyMobileDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verifyEmailDetails(VerifyEmailDTO verifyEmailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sendMobileVerificationDetails(
			CustomerIdTypeMobileDTO customerIdTypeMobileDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sendEmailVerificationDetails(
			CustomerIdTypeEmailDTO customerIdTypeEmailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean clearTestData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer count() {
		// TODO Auto-generated method stub
		return null;
	}

}
