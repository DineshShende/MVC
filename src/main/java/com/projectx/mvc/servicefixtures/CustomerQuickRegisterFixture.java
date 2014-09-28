package com.projectx.mvc.servicefixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.CustomerId;
import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.UpdateMobilePinDTO;
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
				
		
		if(customerQuickRegisterEntity.getEmail().equals(CUST_EMAIL)&& customerQuickRegisterEntity.getMobile().equals(CUST_MOBILE))
			return REGISTER_NOT_REGISTERED;
		else if(customerQuickRegisterEntity.getEmail().equals("din@gmail.com")&& customerQuickRegisterEntity.getMobile()==null)
			return REGISTER_EMAIL_ALREADY_REGISTERED;
		else if(customerQuickRegisterEntity.getEmail().equals("") && customerQuickRegisterEntity.getMobile().equals(8598058044L))
			return REGISTER_MOBILE_ALREADY_REGISTERED;
		
		return REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED;
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

	@Override
	public Boolean ResendMobilePin(UpdateMobilePinDTO mobileDTO) {
		
		if(mobileDTO.getCustomerId().equals(212L))
			return true;
		else
			return false;
	}

	
		
	

}
