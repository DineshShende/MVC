package com.projectx.mvc.servicehandler.quickregister;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import static com.projectx.mvc.fixture.quickregister.QuickRegisterDataConstants.*;

import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.PasswordRestFailedException;
import com.projectx.mvc.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.mvc.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailOrMobileOptionUpdatedBy;
import com.projectx.rest.domain.quickregister.CustomerIdTypeUpdatedBy;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.ForgetPasswordEntity;
import com.projectx.rest.domain.quickregister.MobilePinPasswordDTO;
import com.projectx.rest.domain.quickregister.MobilePinPasswordList;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStatusDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.UpdatePasswordMVCDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailLoginDetails;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;


@Component
@Profile(value={"Dev","Prod"})
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
	public QuickRegisterStatusDTO checkIfAlreadyExist(
			QuickRegisterEntity customerQuickRegisterEntity) {
		
		ResponseEntity<QuickRegisterStatusDTO> status=null;
				
		try{
			status=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/checkifexist",HttpMethod.POST, 
					new HttpEntity<QuickRegisterEntity>(customerQuickRegisterEntity), QuickRegisterStatusDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
				
		return status.getBody();
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
			QuickRegisterEntity customerQuickRegisterEntity) throws QuickRegisterDetailsAlreadyPresentException{

		
		
		HttpEntity<QuickRegisterEntity> entity=new HttpEntity<QuickRegisterEntity>(customerQuickRegisterEntity);
		
		ResponseEntity<QuickRegisterSavedEntityDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister",
					HttpMethod.POST, entity, QuickRegisterSavedEntityDTO.class);
		}catch(ValidationFailedException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else if(result.getStatusCode()==HttpStatus.NOT_ACCEPTABLE)
			throw new ValidationFailedException();
		else if(result.getStatusCode()==HttpStatus.ALREADY_REPORTED)
			throw new QuickRegisterDetailsAlreadyPresentException();
		
		throw new ResourceNotFoundException();
	}

	@Override
	public QuickRegisterDTO getByCustomerIdType(CustomerIdTypeDTO customerIdDTO) throws QuickRegisterEntityNotFoundException{

		HttpEntity<CustomerIdTypeDTO> entity=new HttpEntity<CustomerIdTypeDTO>(customerIdDTO);
		
		ResponseEntity<QuickRegisterDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/getByCustomerId",
					HttpMethod.POST, entity, QuickRegisterDTO.class);
		
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new QuickRegisterEntityNotFoundException();
	}

	
	@Override
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO) {
		
		HttpEntity<VerifyMobileDTO > entity=new HttpEntity<VerifyMobileDTO>(mobileDTO);

		ResponseEntity<Boolean> verificationStatus=null;
		
		try{
			verificationStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/verifyMobilePin",
					HttpMethod.POST,entity, Boolean.class);
		
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(verificationStatus.getStatusCode()==HttpStatus.OK)
			return verificationStatus.getBody();
		else
			throw new ResourceNotFoundException();
	}

	@Override
	public Boolean verifyEmail(VerifyEmailDTO emailDTO) throws ResourceNotFoundException {

		HttpEntity<VerifyEmailDTO> entity=new HttpEntity<VerifyEmailDTO>(emailDTO);
		
		ResponseEntity<Boolean> verificationStatus=null;
		
		try{
			verificationStatus=restTemplate.
					exchange(env.getProperty("rest.host")+"/customer/quickregister/verifyEmailHash",HttpMethod.POST, entity, Boolean.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(verificationStatus.getStatusCode()==HttpStatus.OK)
			return verificationStatus.getBody();
		else
			throw new ResourceNotFoundException();
	}

	@Override
	public Boolean reSendMobilePin(CustomerIdTypeMobileTypeUpdatedByDTO customerDTO) {
		
		HttpEntity<CustomerIdTypeMobileTypeUpdatedByDTO> entity=new HttpEntity<CustomerIdTypeMobileTypeUpdatedByDTO>(customerDTO);
		
		ResponseEntity<Boolean> detailsSentStatus=null;
		
		try{
			detailsSentStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/resendMobilePin",
					HttpMethod.POST,entity, Boolean.class);

		}catch(RestClientException e)
		{
			throw new ValidationFailedException(); 
		}
		
		if(detailsSentStatus.getStatusCode()==HttpStatus.OK)
			return detailsSentStatus.getBody();
		else
			throw new ResourceNotFoundException();
	}

	@Override
	public Boolean reSendEmailHash(CustomerIdTypeEmailTypeUpdatedByDTO customerDTO) {
				
		HttpEntity<CustomerIdTypeEmailTypeUpdatedByDTO> entity=new HttpEntity<CustomerIdTypeEmailTypeUpdatedByDTO>(customerDTO);
		
		ResponseEntity<Boolean> detailsSentStatus=null;
		
		try{
			detailsSentStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/resendEmailHash",
					HttpMethod.POST,entity, Boolean.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(detailsSentStatus.getStatusCode()==HttpStatus.OK)
			return detailsSentStatus.getBody();
		else
			throw new ResourceNotFoundException();
	}
	
	@Override
	public Boolean reSetMobilePin(CustomerIdTypeMobileTypeUpdatedByDTO customerDTO) {
		
		HttpEntity<CustomerIdTypeMobileTypeUpdatedByDTO> entity=new HttpEntity<CustomerIdTypeMobileTypeUpdatedByDTO>(customerDTO);

		ResponseEntity<Boolean> detailsSentStatus=null;
		
		try{
			detailsSentStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/resetMobilePin",
					HttpMethod.POST,entity, Boolean.class);

		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(detailsSentStatus.getStatusCode()==HttpStatus.OK)
			return detailsSentStatus.getBody();
		else
			throw new ResourceNotFoundException();

	}

	@Override
	public Boolean reSetEmailHash(CustomerIdTypeEmailTypeUpdatedByDTO customerDTO) throws ValidationFailedException,ResourceNotFoundException{

		HttpEntity<CustomerIdTypeEmailTypeUpdatedByDTO> entity=new HttpEntity<CustomerIdTypeEmailTypeUpdatedByDTO>(customerDTO);
		
		ResponseEntity<Boolean> detailsSentStatus=null;
		
		try{
			
			detailsSentStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/resetEmailHash",HttpMethod.POST,
					entity, Boolean.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		

		if(detailsSentStatus.getStatusCode()==HttpStatus.OK)
			return detailsSentStatus.getBody();
		else
			throw new ResourceNotFoundException();
		
	}


	@Override
	public void clearTestData() {
		restTemplate.getForObject(env.getProperty("rest.host")+"/customer/quickregister/cleartestdata", Boolean.class);
		
	}

	

	@Override
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		
		UpdatePasswordMVCDTO mvcdto=new UpdatePasswordMVCDTO(updatePasswordDTO.getKey().getCustomerId(), 
				updatePasswordDTO.getKey().getCustomerType(), updatePasswordDTO.getPassword(),updatePasswordDTO.getRequestedBy());
		
		HttpEntity<UpdatePasswordMVCDTO> entity=new HttpEntity<UpdatePasswordMVCDTO>(mvcdto);
		
		ResponseEntity<Boolean> updateStatus=null;
		
		try{
			updateStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/updatePassword",HttpMethod.POST,
					entity, Boolean.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
			
		if(updateStatus.getStatusCode()==HttpStatus.OK)
			return updateStatus.getBody();
		
		throw new ResourceNotFoundException();
	}



	@Override
	public EmailVerificationDetailsDTO getEmailVerificationDetailsByCustomerIdTypeAndEmail(
			Long customerId,Integer customerType, Integer emailType) throws EmailVerificationDetailNotFoundException {

		CustomerIdTypeEmailTypeDTO emailDTO=new CustomerIdTypeEmailTypeDTO(customerId,customerType, emailType);
		
		HttpEntity<CustomerIdTypeEmailTypeDTO> entity=new HttpEntity<CustomerIdTypeEmailTypeDTO>(emailDTO);
		
		ResponseEntity<EmailVerificationDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/getEmailVerificationDetails",
					HttpMethod.POST, entity, EmailVerificationDetailsDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}

		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new EmailVerificationDetailNotFoundException();
	}

	@Override
	public MobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdTypeAndMobile(
			Long customerId,Integer customerType, Integer mobileType) throws MobileVerificationDetailsNotFoundException{

		CustomerIdTypeMobileTypeDTO mobileDTO=new CustomerIdTypeMobileTypeDTO(customerId,customerType, mobileType);
		
		HttpEntity<CustomerIdTypeMobileTypeDTO> entity=new HttpEntity<CustomerIdTypeMobileTypeDTO>(mobileDTO);
		
		ResponseEntity<MobileVerificationDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/getMobileVerificationDetails",
					HttpMethod.POST, entity, MobileVerificationDetailsDTO.class);
		
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new MobileVerificationDetailsNotFoundException();
		
	}

	@Override
	public AuthenticationDetailsDTO verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO) throws AuthenticationDetailsNotFoundException {
		
		HttpEntity<LoginVerificationDTO> entity=new HttpEntity<LoginVerificationDTO>(loginVerificationDTO);
		
		ResponseEntity<AuthenticationDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/verifyLoginDetails",
					HttpMethod.POST, entity, AuthenticationDetailsDTO.class);
						
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new AuthenticationDetailsNotFoundException();
		
		
	}
	
	@Override
	public AuthenticationDetailsDTO getAuthenticationDetailsByCustomerIdType(
			Long customerId,Integer customerType) throws AuthenticationDetailsNotFoundException{

		CustomerIdTypeDTO customerIdDTO=new CustomerIdTypeDTO(customerId,customerType);
	
		HttpEntity<CustomerIdTypeDTO> entity=new HttpEntity<CustomerIdTypeDTO>(customerIdDTO);
		
		ResponseEntity<AuthenticationDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/getAuthenticationDetailsById", 
					HttpMethod.POST, entity, AuthenticationDetailsDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new AuthenticationDetailsNotFoundException();
		
				
	}

	@Override
	public AuthenticationDetailsDTO verifyEmailLoginDetails(
			VerifyEmailLoginDetails emailLoginDetails) throws AuthenticationDetailsNotFoundException{

		HttpEntity<VerifyEmailLoginDetails> entity=new HttpEntity<VerifyEmailLoginDetails>(emailLoginDetails);
		
		ResponseEntity<AuthenticationDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/verifyLoginDefaultEmailPassword",
					HttpMethod.POST, entity, AuthenticationDetailsDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new AuthenticationDetailsNotFoundException();
	}

	@Override
	public Boolean resetPassword(Long customerId,Integer customerType,Integer emailOrMobile,String requestedBy) throws PasswordRestFailedException{
	
		CustomerIdTypeEmailOrMobileOptionUpdatedBy customerIdDTO=new 
				CustomerIdTypeEmailOrMobileOptionUpdatedBy(customerId,customerType,emailOrMobile,requestedBy);
		
		HttpEntity<CustomerIdTypeEmailOrMobileOptionUpdatedBy> entity=new HttpEntity<CustomerIdTypeEmailOrMobileOptionUpdatedBy>(customerIdDTO);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/resetPassword",
					HttpMethod.POST, entity, Boolean.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
				
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new PasswordRestFailedException();
		
		
	}

	@Override
	public ForgetPasswordEntity resetPasswordRedirect(String entityInp,String requestedBy) throws PasswordRestFailedException{
		
		ResetPasswordRedirectDTO resetPasswordRedirectDTO=new ResetPasswordRedirectDTO(entityInp,requestedBy);
		
		HttpEntity<ResetPasswordRedirectDTO> entity=new HttpEntity<ResetPasswordRedirectDTO>(resetPasswordRedirectDTO);
		
		ResponseEntity<ForgetPasswordEntity> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/resetPasswordRedirect",
					HttpMethod.POST, entity, ForgetPasswordEntity.class);
	
		}catch(ValidationFailedException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new PasswordRestFailedException();
	}
	
	@Override
	public ModelAndView populateCompleteRegisterRedirect(
			AuthenticationDetailsDTO result) {
		
		ModelAndView modelAndView=new ModelAndView();

		if(result.getKey().getCustomerType().equals(ENTITY_TYPE_CUSTOMER))
		{	
			CustomerDetailsDTO detailsDTO=new CustomerDetailsDTO();
					
			try{
				
				detailsDTO=customerDetailsService.getCustomerDetailsById(result.getKey().getCustomerId());
				
				modelAndView.addObject("customerDetails", detailsDTO);
				
				/*
				DocumentDetails documentDetails=documentDetailsService.getDocumentById(new DocumentKey(detailsDTO.getCustomerId(), 1, "DrivingLicense"));
				
				modelAndView.addObject("documentDetails", documentDetails);
				*/
				modelAndView=initialiseShowDetails(result.getKey().getCustomerId(), result.getKey().getCustomerType(), modelAndView);
				
				modelAndView.setViewName("completeregister/showCustomerDetails");
				
				return modelAndView;
				
			}catch(CustomerDetailsNotFoundException e)
			{
				QuickRegisterDTO quickRegisterEntity=
						getByCustomerIdType(new CustomerIdTypeDTO(result.getKey().getCustomerId(),result.getKey().getCustomerType()));
				
				CustomerDetailsDTO createdRecord=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
				
							
				modelAndView.addObject("customerDetails", createdRecord);
				modelAndView.setViewName("completeregister/customerDetailsForm");
				
				return modelAndView;
			}
			
		}
		else if(result.getKey().getCustomerType().equals(ENTITY_TYPE_VENDOR))
		{
			VendorDetailsDTO vendorDetailsDTO=new VendorDetailsDTO();
			
			try{
				vendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(result.getKey().getCustomerId());
				
				modelAndView.addObject("vendorDetails", vendorDetailsDTO);
				
				/*
				DocumentDetails documentDetails=documentDetailsService.getDocumentById(new DocumentKey(vendorDetailsDTO.getVendorId(), 2, "DrivingLicense"));
				
				modelAndView.addObject("documentDetails", documentDetails);
				*/
				modelAndView=initialiseShowDetails(result.getKey().getCustomerId(), result.getKey().getCustomerType(), modelAndView);
				
				modelAndView.setViewName("completeregister/showVendorDetails");
				
				return modelAndView;
				
			}catch(VendorDetailsNotFoundException e)
			{
				QuickRegisterDTO quickRegisterEntity=
						getByCustomerIdType(new CustomerIdTypeDTO(result.getKey().getCustomerId(),result.getKey().getCustomerType()));
				
				VendorDetailsDTO createdRecord=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
				
							
				modelAndView.addObject("vendorDetails", createdRecord);
				modelAndView.setViewName("completeregister/vendorDetailsForm");
				
				return modelAndView;

			}
					
		}
		
		return modelAndView;
	}

	@Override
	public ModelAndView initialiseShowDetails(Long entityId, Integer entityType,
			ModelAndView model) {


		EmailVerificationDetailsDTO emailVerificationDetails=null;
				
		try{
			emailVerificationDetails=getEmailVerificationDetailsByCustomerIdTypeAndEmail(entityId,entityType , ENTITY_TYPE_PRIMARY);
		}catch(EmailVerificationDetailNotFoundException e)
		{
			emailVerificationDetails=new EmailVerificationDetailsDTO();
		}
		
		MobileVerificationDetailsDTO mobileVerificationDetailsPrimary=null;
		
		try{
			mobileVerificationDetailsPrimary=getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityId, entityType, ENTITY_TYPE_PRIMARY);
		}
		catch(MobileVerificationDetailsNotFoundException e)
		{
			mobileVerificationDetailsPrimary=new MobileVerificationDetailsDTO();
		}
		
		MobileVerificationDetailsDTO mobileVerificationDetailsSeconadry=null;
		
		if(entityType.equals(ENTITY_TYPE_CUSTOMER))
		{	
			try{
				mobileVerificationDetailsSeconadry=getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityId, entityType, ENTITY_TYPE_SECONDARY);
			}catch(MobileVerificationDetailsNotFoundException e)
			{
				mobileVerificationDetailsSeconadry=new MobileVerificationDetailsDTO();
			}
			model.addObject("mobileVerificationDetailsSeconadry", mobileVerificationDetailsSeconadry);
		}
		
		model.addObject("emailVerificationDetails", emailVerificationDetails);
		model.addObject("mobileVerificationDetailsPrimary", mobileVerificationDetailsPrimary);
		
		
		return model;
	}

	@Override
	public List<MobilePinPasswordDTO> getTestData() {

		ResponseEntity<MobilePinPasswordList> result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/getTestData",
				HttpMethod.GET, null, MobilePinPasswordList.class);
		
		return result.getBody().getList();
	}

	
	
}
