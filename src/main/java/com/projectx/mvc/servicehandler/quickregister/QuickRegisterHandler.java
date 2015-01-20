package com.projectx.mvc.servicehandler.quickregister;


import java.util.Map;

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
import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
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
	
	@Autowired
	DocumentDetailsService 	documentDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService; 
	
	
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ENTITY_TYPE_SECONDARY=2;
	
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
	public Boolean reSendMobilePin(CustomerIdTypeMobileTypeDTO customerDTO) {
		
		Boolean detailsSentStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resendMobilePin", customerDTO, Boolean.class);

		return detailsSentStatus;
	}

	@Override
	public Boolean reSendEmailHash(CustomerIdTypeEmailTypeDTO customerDTO) {

		Boolean detailsSentStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resendEmailHash", customerDTO, Boolean.class);

		return detailsSentStatus;
	}
	
	@Override
	public Boolean reSetMobilePin(CustomerIdTypeMobileTypeDTO customerDTO) {

		Boolean detailsSentStatus=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/resetMobilePin", customerDTO, Boolean.class);

		return detailsSentStatus;

	}

	@Override
	public Boolean reSetEmailHash(CustomerIdTypeEmailTypeDTO customerDTO) {

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
			Long customerId,Integer customerType, Integer emailType) {

		CustomerIdTypeEmailTypeDTO emailDTO=new CustomerIdTypeEmailTypeDTO(customerId,customerType, emailType);
		
		EmailVerificationDetailsDTO fetchedEntity=restTemplate.postForObject(env.getProperty("rest.host")+"/customer/quickregister/getEmailVerificationDetails", emailDTO, EmailVerificationDetailsDTO.class);
		
		return fetchedEntity;
	}

	@Override
	public MobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdTypeAndMobile(
			Long customerId,Integer customerType, Integer mobileType) {

		CustomerIdTypeMobileTypeDTO mobileDTO=new CustomerIdTypeMobileTypeDTO(customerId,customerType, mobileType);
		
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

		if(result.getKey().getCustomerType().equals(ENTITY_TYPE_CUSTOMER))
		{	
			CustomerDetailsDTO detailsDTO=customerDetailsService.getCustomerDetailsById(result.getKey().getCustomerId());
			
			if(detailsDTO.getCustomerId()!=null)
			{
				modelAndView.addObject("customerDetails", detailsDTO);
				
				DocumentDetails documentDetails=documentDetailsService.getDocumentById(new DocumentKey(detailsDTO.getCustomerId(), 1, "DrivingLicense"));
				
				modelAndView.addObject("documentDetails", documentDetails);
				
				modelAndView=initialiseShowDetails(result.getKey().getCustomerId(), result.getKey().getCustomerType(), modelAndView);
				
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
		else if(result.getKey().getCustomerType().equals(ENTITY_TYPE_VENDOR))
		{
		
			
			VendorDetailsDTO vendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(result.getKey().getCustomerId());
			
			if(vendorDetailsDTO.getVendorId()!=null)
			{
				modelAndView.addObject("vendorDetails", vendorDetailsDTO);
				
				DocumentDetails documentDetails=documentDetailsService.getDocumentById(new DocumentKey(vendorDetailsDTO.getVendorId(), 2, "DrivingLicense"));
				
				modelAndView.addObject("documentDetails", documentDetails);
				
				modelAndView=initialiseShowDetails(result.getKey().getCustomerId(), result.getKey().getCustomerType(), modelAndView);
				
				modelAndView.setViewName("showVendorDetails");
				
				return modelAndView;
			}
			else
			{
				QuickRegisterDTO quickRegisterEntity=
						getByCustomerIdType(new CustomerIdTypeDTO(result.getKey().getCustomerId(),result.getKey().getCustomerType()));
				
				VendorDetailsDTO createdRecord=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
				
							
				modelAndView.addObject("vendorDetails", createdRecord);
				modelAndView.setViewName("vendorDetailsForm");
				
				return modelAndView;
				
			}
		}
		
		return modelAndView;
	}

	@Override
	public ModelAndView initialiseShowDetails(Long entityId, Integer entityType,
			ModelAndView model) {


		EmailVerificationDetailsDTO emailVerificationDetails=getEmailVerificationDetailsByCustomerIdTypeAndEmail(entityId,entityType , ENTITY_TYPE_PRIMARY);
		
		MobileVerificationDetailsDTO mobileVerificationDetailsPrimary=getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityId, entityType, ENTITY_TYPE_PRIMARY);
		
		MobileVerificationDetailsDTO mobileVerificationDetailsSeconadry=null;
		
		if(entityType.equals(ENTITY_TYPE_CUSTOMER))
		{	
			mobileVerificationDetailsSeconadry=getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityId, entityType, ENTITY_TYPE_SECONDARY);
			
			model.addObject("mobileVerificationDetailsSeconadry", mobileVerificationDetailsSeconadry);
		}
		
		model.addObject("emailVerificationDetails", emailVerificationDetails);
		model.addObject("mobileVerificationDetailsPrimary", mobileVerificationDetailsPrimary);
		
		
		return model;
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
