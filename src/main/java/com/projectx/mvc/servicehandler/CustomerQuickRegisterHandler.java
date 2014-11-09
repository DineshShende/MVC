package com.projectx.mvc.servicehandler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.*;

import com.projectx.mvc.domain.CustomerDocumetDTO;
import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.domain.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.UpdatePasswordDTO;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerAuthenticationDetailsDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.CustomerQuickRegisterStringStatusDTO;
import com.projectx.rest.domain.LoginVerificationDTO;
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
	public CustomerQuickRegisterStringStatusDTO checkIfAlreadyExist(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		
		CustomerQuickRegisterStringStatusDTO status=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/checkifexist", customerQuickRegisterEntity, CustomerQuickRegisterStringStatusDTO.class);
		
		return status;
	}

	@Override
	public String populateMessageForDuplicationField(
			String duplicationStatus) {
		
		String message=null;
		
		if(duplicationStatus.equals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED))
			message=MSG_REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED;
		else if(duplicationStatus.equals(REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED))
			message=MSG_REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED;
		else if(duplicationStatus.equals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED))
			message=MSG_REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED;
		else if(duplicationStatus.equals(REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED))
			message=MSG_REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED;
		else if(duplicationStatus.equals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED))
			message=MSG_REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED;
		else if(duplicationStatus.equals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED))
			message=MSG_REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED;
		else if(duplicationStatus.equals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED))
			message=MSG_REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED;
		else if(duplicationStatus.equals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED))
			message=MSG_REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED;
		
		
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

	@Override
	public CustomerAuthenticationDetailsDTO verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO) {
		
		System.out.println(loginVerificationDTO);
		
		CustomerAuthenticationDetailsDTO verifiedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/verifyLoginDetails", loginVerificationDTO, CustomerAuthenticationDetailsDTO.class);
		
		return verifiedEntity;
	}

	@Override
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		
		Boolean updateStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/updatePassword", updatePasswordDTO, Boolean.class);
		
		return updateStatus;
	}


	@Override
	public CustomerDocumetDTO saveCustomerDocumet(
			CustomerDocumetDTO customerDocumetDTO) {
		
		CustomerDocumetDTO savedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/saveCustomerDocument", customerDocumetDTO, CustomerDocumetDTO.class);
		
		return savedEntity;

	}

	@Override
	public CustomerDocumetDTO getCustomerDocumetById(Long customerId) {
		
		CustomerDocumetDTO savedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/getCustomerDocumentById", new CustomerIdDTO(customerId), CustomerDocumetDTO.class);
		
		return savedEntity;

	}

	@Override
	public Boolean resetPassword(Long customerId) {
	
		CustomerIdDTO customerIdDTO=new CustomerIdDTO(customerId);
		
		Boolean updateStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resetPassword", customerIdDTO, Boolean.class);
		
		return updateStatus;	
		
	}

	@Override
	public CustomerQuickRegisterDTO resetPasswordRedirect(String entity) {

		ResetPasswordRedirectDTO resetPasswordRedirectDTO=new ResetPasswordRedirectDTO(entity);
		
		CustomerQuickRegisterDTO fetchedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resetPasswordRedirect",
																		resetPasswordRedirectDTO, CustomerQuickRegisterDTO.class);
		return fetchedEntity;
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
