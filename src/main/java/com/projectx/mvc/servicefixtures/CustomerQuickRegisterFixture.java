package com.projectx.mvc.servicefixtures;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.*;
@Component
@Profile("Test")
public class CustomerQuickRegisterFixture implements
		CustomerQuickRegisterService {

	
	@Override
	public String checkIfAlreadyExist(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
				
		return REGISTER_NOT_REGISTERED;
	}

	@Override
	public CustomerQuickRegisterDTO addNewCustomer(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		
		return standardEmailMobileCustomer();
	}

	@Override
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO) {
		
		System.out.println(mobileDTO);
		
		if(mobileDTO.getCustomerId()==212 && mobileDTO.getMobilePin()==101010)
			return true;
		else
			return false;
	}

	@Override
	public Boolean verifyEmail(VerifyEmailDTO emailDTO) {
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

	

}
