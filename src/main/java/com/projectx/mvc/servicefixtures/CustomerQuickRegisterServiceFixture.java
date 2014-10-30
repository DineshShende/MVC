package com.projectx.mvc.servicefixtures;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.*;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;
@Component
@Profile("Test")
public class CustomerQuickRegisterServiceFixture implements
		CustomerQuickRegisterService {

	@Override
	public String checkIfAlreadyExist(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		// TODO Auto-generated method stub
		//
		//
		return null;
	}

	@Override
	public String populateMessageForDuplicationField(String duplicationStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerQuickRegisterSavedEntityDTO addNewCustomer(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
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
	public Boolean reSendMobilePin(CustomerIdDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reSendEmailHash(CustomerIdDTO customerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearTestData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerQuickRegisterDTO getByCustomerId(CustomerIdDTO customerIdDTO) {
		// TODO Auto-generated method stub
		return null;
	}



}
