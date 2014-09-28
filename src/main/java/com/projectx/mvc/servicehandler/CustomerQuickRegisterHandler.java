package com.projectx.mvc.servicehandler;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;


public class CustomerQuickRegisterHandler implements CustomerQuickRegisterService {

	@Override
	public String checkIfAlreadyExist(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		
		return null;
	}

	@Override
	public CustomerQuickRegisterDTO addNewCustomer(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CustomerQuickRegisterDTO getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerQuickRegisterDTO getByMobile(Long mobile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verifyEmail(VerifyEmailDTO emailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean ResendMobilePin(UpdateMobilePinDTO mobileDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 

}
