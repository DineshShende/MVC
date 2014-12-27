package com.projectx.mvc.servicehandler.quickregister;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import static com.projectx.mvc.fixture.quickregister.QuickRegisterDataConstants.*;

import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStringStatusDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.UpdatePasswordMVCDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailLoginDetails;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class QuickRegisterHandler implements QuickRegisterService {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	
	
	@Override
	public QuickRegisterStringStatusDTO checkIfAlreadyExist(
			QuickRegisterEntity customerQuickRegisterEntity) {
		
		QuickRegisterStringStatusDTO status=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/checkifexist", customerQuickRegisterEntity, QuickRegisterStringStatusDTO.class);
		
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
	public QuickRegisterSavedEntityDTO addNewCustomer(
			QuickRegisterEntity customerQuickRegisterEntity) {

		QuickRegisterSavedEntityDTO saveResultEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister", customerQuickRegisterEntity, QuickRegisterSavedEntityDTO.class);
		
		return saveResultEntity;
	}

	@Override
	public QuickRegisterDTO getByCustomerIdType(CustomerIdTypeDTO customerIdDTO) {

		QuickRegisterDTO fetchedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/getByCustomerId", customerIdDTO, QuickRegisterDTO.class);
		
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
	public Boolean reSendMobilePin(CustomerIdTypeMobileDTO customerDTO) {
		
		Boolean detailsSentStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resendMobilePin", customerDTO, Boolean.class);

		return detailsSentStatus;
	}

	@Override
	public Boolean reSendEmailHash(CustomerIdTypeEmailDTO customerDTO) {

		Boolean detailsSentStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resendEmailHash", customerDTO, Boolean.class);

		return detailsSentStatus;
	}
	
	@Override
	public Boolean reSetMobilePin(CustomerIdTypeMobileDTO customerDTO) {

		Boolean detailsSentStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resetMobilePin", customerDTO, Boolean.class);

		return detailsSentStatus;

	}

	@Override
	public Boolean reSetEmailHash(CustomerIdTypeEmailDTO customerDTO) {

		Boolean detailsSentStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resetEmailHash", customerDTO, Boolean.class);

		return detailsSentStatus;
	}


	@Override
	public void clearTestData() {
		restTemplate.getForObject(env.getProperty("rest.host")+"/customer/quickregister/cleartestdata", Boolean.class);
		
	}

	@Override
	public AuthenticationDetailsDTO verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO) {
		
		//System.out.println(loginVerificationDTO);
		
		AuthenticationDetailsDTO verifiedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/verifyLoginDetails", loginVerificationDTO, AuthenticationDetailsDTO.class);
		
		
		
		return verifiedEntity;
	}

	@Override
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		
		System.out.println(updatePasswordDTO);
		
		UpdatePasswordMVCDTO mvcdto=new UpdatePasswordMVCDTO(updatePasswordDTO.getKey().getCustomerId(), 
				updatePasswordDTO.getKey().getCustomerType(), updatePasswordDTO.getPassword());
		
		Boolean updateStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/updatePassword", mvcdto, Boolean.class);
		
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
		
		//CustomerDocumetDTO savedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/getCustomerDocumentById", new CustomerIdTypeDTO(customerId,cu), CustomerDocumetDTO.class);
		
		return new CustomerDocumetDTO();

	}

	@Override
	public Boolean resetPassword(Long customerId,Integer customerType) {
	
		CustomerIdTypeDTO customerIdDTO=new CustomerIdTypeDTO(customerId,customerType);
		
		Boolean updateStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resetPassword", customerIdDTO, Boolean.class);
		
		return updateStatus;	
		
	}

	@Override
	public QuickRegisterDTO resetPasswordRedirect(String entity) {

		ResetPasswordRedirectDTO resetPasswordRedirectDTO=new ResetPasswordRedirectDTO(entity);
		
		QuickRegisterDTO fetchedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resetPasswordRedirect",
																		resetPasswordRedirectDTO, QuickRegisterDTO.class);
		return fetchedEntity;
	}

	@Override
	public EmailVerificationDetailsDTO getEmailVerificationDetailsByCustomerIdTypeAndEmail(
			Long customerId,Integer customerType, String email) {

		CustomerIdTypeEmailDTO emailDTO=new CustomerIdTypeEmailDTO(customerId,customerType, email);
		
		EmailVerificationDetailsDTO fetchedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/getEmailVerificationDetails", emailDTO, EmailVerificationDetailsDTO.class);
		
		return fetchedEntity;
	}

	@Override
	public MobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdTypeAndMobile(
			Long customerId,Integer customerType, Long mobile) {

		CustomerIdTypeMobileDTO mobileDTO=new CustomerIdTypeMobileDTO(customerId,customerType, mobile);
		
		MobileVerificationDetailsDTO fetchedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/getMobileVerificationDetails", mobileDTO, MobileVerificationDetailsDTO.class);
		
		return fetchedEntity;
	}

	@Override
	public AuthenticationDetailsDTO getAuthenticationDetailsByCustomerIdType(
			Long customerId,Integer customerType) {

		CustomerIdTypeDTO customerIdDTO=new CustomerIdTypeDTO(customerId,customerType);
	
		AuthenticationDetailsDTO fetchedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/getAuthenticationDetailsById", customerIdDTO, AuthenticationDetailsDTO.class);
		
		return fetchedEntity;
		
	}

	@Override
	public AuthenticationDetailsDTO verifyEmailLoginDetails(
			VerifyEmailLoginDetails emailLoginDetails) {

		System.out.println(emailLoginDetails);
		
		AuthenticationDetailsDTO fetchedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/verifyLoginDefaultEmailPassword", emailLoginDetails, AuthenticationDetailsDTO.class);
		
		return fetchedEntity;
	}

	@Override
	public ModelAndView populateCompleteRegisterRedirect(
			AuthenticationDetailsDTO result) {
		
		ModelAndView modelAndView=new ModelAndView();
		
		if(result.getKey().getCustomerType().equals(1))
		{	
			CustomerDetailsDTO detailsDTO=customerDetailsService.getCustomerDetailsById(result.getKey().getCustomerId());
			
			if(detailsDTO.getCustomerId()!=null)
			{
				modelAndView.addObject("customerDetails", detailsDTO);
				modelAndView.setViewName("showCustomerDetails");
				
				return modelAndView;
			}
			else
			{
				QuickRegisterDTO quickRegisterEntity=
						getByCustomerIdType(new CustomerIdTypeDTO(result.getKey().getCustomerId(),result.getKey().getCustomerType()));
				
				CustomerDetailsDTO createdRecord=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
				
							
				modelAndView.addObject("customerDetails", createdRecord);
				modelAndView.setViewName("customerDetailsForm");
				
				return modelAndView;
				
			}
		}
		else if(result.getKey().getCustomerType().equals(2))
		{
			//Vendor complete registration
			return modelAndView;
		}
		return modelAndView;
		
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
