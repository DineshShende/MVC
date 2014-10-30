package com.projectx.mvc.servicehandler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.*;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class CustomerQuickRegisterHandler implements CustomerQuickRegisterService {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	
	@Override
	public String checkIfAlreadyExist(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		
		String status=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/checkifexist", customerQuickRegisterEntity, String.class);
		
		return status;
	}

	@Override
	public String populateMessageForDuplicationField(
			String duplicationStatus) {
		
		String message=null;
		
		if(duplicationStatus.equals(REGISTER_EMAIL_ALREADY_REGISTERED))
			message="Provided Email Already Registered";
		else if(duplicationStatus.equals(REGISTER_MOBILE_ALREADY_REGISTERED))
			message="Provided Mobile Already Registered";
		else if(duplicationStatus.equals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED))
			message="Provided Email And Mobile Already Registered";
			
		return message;
	}

	@Override
	public CustomerQuickRegisterSavedEntityDTO addNewCustomer(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {

		CustomerQuickRegisterSavedEntityDTO saveResultEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister", customerQuickRegisterEntity, CustomerQuickRegisterSavedEntityDTO.class);
		
		return saveResultEntity;
	}

	@Override
	public CustomerQuickRegisterDTO getByCustomerId(CustomerIdDTO customerIdDTO) {

		CustomerQuickRegisterDTO fetchedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/getByCustomerId", customerIdDTO, CustomerQuickRegisterDTO.class);
		
		return fetchedEntity;
	}

	
	@Override
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO) {

		Boolean verificationStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/verifyMobilePin", mobileDTO, Boolean.class);
		
		return verificationStatus;
	}

	@Override
	public Boolean verifyEmail(VerifyEmailDTO emailDTO) {

		Boolean verificationStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/verifyEmailHash", emailDTO, Boolean.class);
		
		return verificationStatus;
	}

	@Override
	public Boolean reSendMobilePin(CustomerIdDTO customerDTO) {
		
		Boolean detailsSentStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resendMobilePin", customerDTO, Boolean.class);

		return detailsSentStatus;
	}

	@Override
	public Boolean reSendEmailHash(CustomerIdDTO customerDTO) {

		Boolean detailsSentStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resendEmailHash", customerDTO, Boolean.class);

		return detailsSentStatus;
	}

	@Override
	public void clearTestData() {
		restTemplate.getForObject(env.getProperty("rest.host")+"/customer/quickregister/cleartestdata", Boolean.class);
		
	}


	/*
	@Override
	public ModelAndView handleNewCustomer(CustomerQuickRegisterEntity customer) {
		
		ModelAndView modelAndView=new ModelAndView();
		
		if(checkIfAlreadyExist(customer).equals(REGISTER_NOT_REGISTERED))
		{
			CustomerQuickRegisterSavedEntityDTO saveResultEntity=addNewCustomer(customer);
			
			
			
		}
		
	
		return null;
	}
*/		
	 

}
