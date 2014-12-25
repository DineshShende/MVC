package com.projectx.mvc.servicehandler.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class CustomerDetailsHandler implements CustomerDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	
	@Override
	public CustomerDetailsDTO createCustomerDetailsFromQuickRegisterEntity(
			QuickRegisterDTO quickRegisterEntity) {
		
		CustomerDetailsDTO status=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/createFromQuickRegister", quickRegisterEntity, CustomerDetailsDTO.class);
		
		return status;
		
		
	}

	@Override
	public CustomerDetailsDTO merge(CustomerDetailsDTO customerDetails) {
		
		CustomerDetailsDTO status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/customer", customerDetails, CustomerDetailsDTO.class);
		
		return status;
		
		
	}

	@Override
	public Boolean verifyMobileDetails(VerifyMobileDTO verifyMobileDTO) {
		
		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/customer/verifyMobileDetails", verifyMobileDTO, Boolean.class);
		
		return status;
		
	}

	@Override
	public Boolean verifyEmailDetails(VerifyEmailDTO verifyEmailDTO) {
		
		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/customer/verifyEmailDetails", verifyEmailDTO, Boolean.class);
		
		return status;
		
	}

	@Override
	public Boolean sendMobileVerificationDetails(
			CustomerIdTypeMobileDTO customerIdTypeMobileDTO) {
		
		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/customer/sendMobileVerificationDetails", customerIdTypeMobileDTO, Boolean.class);
		
		return status;
	}

	@Override
	public Boolean sendEmailVerificationDetails(
			CustomerIdTypeEmailDTO customerIdTypeEmailDTO) {

		Boolean status=restTemplate
				.postForObject(env.getProperty("rest.host")+"/customer/sendEmailVerificationDetails", customerIdTypeEmailDTO, Boolean.class);
		
		return status;
		
	}

	@Override
	public Boolean clearTestData() {
		
		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/customer/clearTestData", Boolean.class);
		
		return result;
		
		
	}

	@Override
	public Integer count() {

		Integer count=restTemplate
				.getForObject(env.getProperty("rest.host")+"/customer/count", Integer.class);
		
		return count;
		
	}

}
