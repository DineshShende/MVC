package com.projectx.mvc.servicehandler.quickregister;


import static com.projectx.mvc.fixture.quickregister.QuickRegisterDataConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.commn.ResponseDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.UpdatePasswordFailedException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
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
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailOrMobileOptionUpdatedBy;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.ForgetPasswordEntity;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.SendResendResetEmailHashDTO;
import com.projectx.rest.domain.quickregister.SendResendResetMobilePinDTO;
import com.projectx.rest.domain.quickregister.SendResendResetPasswordDTO;
import com.projectx.rest.domain.quickregister.SendResendResetPasswordRestDTO;
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
	
	@Value("${AUTHENTICATION_DETAILS_NOT_FOUND_BY_ID}")
	private String AUTHENTICATION_DETAILS_NOT_FOUND_BY_ID;
	

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
		//TODO need to handle different scenarios
		
		if(result.getStatusCode()==HttpStatus.CREATED || result.getStatusCode()==HttpStatus.ALREADY_REPORTED||result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else if(result.getStatusCode()==HttpStatus.NOT_ACCEPTABLE)
			throw new ValidationFailedException();
		else if(result.getStatusCode()==HttpStatus.ALREADY_REPORTED)
			throw new QuickRegisterDetailsAlreadyPresentException(result.getBody().getStatus());
		
		throw new ResourceNotFoundException();
	}

	@Override
	public QuickRegisterDTO getByCustomerIdType(CustomerIdTypeDTO customerIdDTO) throws QuickRegisterEntityNotFoundException{

		HttpEntity<CustomerIdTypeDTO> entity=new HttpEntity<CustomerIdTypeDTO>(customerIdDTO);
		
		ResponseEntity<ResponseDTO<QuickRegisterDTO>> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/getByCustomerId",
					HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseDTO<QuickRegisterDTO>>() {});
		
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody().getResult();
		else
			throw new QuickRegisterEntityNotFoundException(result.getBody().getErrorMessage());
	}

	
	@Override
	public Boolean verifyEmail(VerifyEmailDTO emailDTO) throws ResourceNotFoundException {

		HttpEntity<VerifyEmailDTO> entity=new HttpEntity<VerifyEmailDTO>(emailDTO);
		
		ResponseEntity<ResponseDTO<Boolean>> verificationStatus=null;
		
		try{
			verificationStatus=restTemplate.
					exchange(env.getProperty("rest.host")+"/customer/quickregister/verifyEmailHash",HttpMethod.POST, entity, 
							new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(verificationStatus.getStatusCode()==HttpStatus.OK && verificationStatus.getBody().getErrorMessage().equals(""))
			return verificationStatus.getBody().getResult();
		else
			throw new ResourceNotFoundException(verificationStatus.getBody().getErrorMessage());
	}

	
	
	@Override
	public Boolean sendOrResendOrResetEmailHash(SendResendResetEmailHashDTO sendResendResetEmailHashDTO) {

		HttpEntity<SendResendResetEmailHashDTO> entity=new HttpEntity<SendResendResetEmailHashDTO>(sendResendResetEmailHashDTO);
		
		ResponseEntity<ResponseDTO<Boolean>> detailsSentStatus=null;
		
		try{
			
			detailsSentStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/sendOrResendOrResetEmailHash",HttpMethod.POST,
					entity, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		

		if(detailsSentStatus.getStatusCode()==HttpStatus.OK && detailsSentStatus.getBody().getErrorMessage().equals(""))
			return detailsSentStatus.getBody().getResult();
		else
			throw new ResourceNotFoundException(detailsSentStatus.getBody().getErrorMessage());
		
	}

	
	
	@Override
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO) {
		
		HttpEntity<VerifyMobileDTO > entity=new HttpEntity<VerifyMobileDTO>(mobileDTO);

		ResponseEntity<ResponseDTO<Boolean>> verificationStatus=null;
		
		try{
			verificationStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/verifyMobilePin",
					HttpMethod.POST,entity, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(verificationStatus.getStatusCode()==HttpStatus.OK && verificationStatus.getBody().getErrorMessage().equals(""))
			return verificationStatus.getBody().getResult();
		else
			throw new ResourceNotFoundException(verificationStatus.getBody().getErrorMessage());
	}

	

	
	@Override
	public Boolean sendOrResendOrResetMobilePin(
			SendResendResetMobilePinDTO sendResendResetMobilePinDTO) {

		HttpEntity<SendResendResetMobilePinDTO> entity=new HttpEntity<SendResendResetMobilePinDTO>(sendResendResetMobilePinDTO);

		ResponseEntity<ResponseDTO<Boolean>> detailsSentStatus=null;
		
		try{
			detailsSentStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/sendOrResendOrResetMobilePin",
					HttpMethod.POST,entity, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});

		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(detailsSentStatus.getStatusCode()==HttpStatus.OK && detailsSentStatus.getBody().getErrorMessage().equals(""))
			return detailsSentStatus.getBody().getResult();
		else
			throw new ResourceNotFoundException(detailsSentStatus.getBody().getErrorMessage());


	}



	@Override
	public void clearTestData() {
		restTemplate.getForObject(env.getProperty("rest.host")+"/customer/quickregister/cleartestdata", Boolean.class);
		restTemplate.getForObject(env.getProperty("rest.host")+"/customer/quickregister/clearAuthTestData", Boolean.class);
		
	}

	

	@Override
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) {
		
		UpdatePasswordMVCDTO mvcdto=new UpdatePasswordMVCDTO(updatePasswordDTO.getKey().getCustomerId(), 
				updatePasswordDTO.getKey().getCustomerType(), updatePasswordDTO.getPassword(),updatePasswordDTO.getOldPassword(),
				updatePasswordDTO.getIsForcefulChangePassword(),
				updatePasswordDTO.getRequestedBy(),updatePasswordDTO.getRequestedById());
		
		HttpEntity<UpdatePasswordMVCDTO> entity=new HttpEntity<UpdatePasswordMVCDTO>(mvcdto);
		
		ResponseEntity<ResponseDTO<Boolean>> updateStatus=null;
		
		try{
			updateStatus=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/updatePassword",HttpMethod.POST,
					entity,new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
			
		if(updateStatus.getStatusCode()==HttpStatus.OK && updateStatus.getBody().getErrorMessage().equals(""))
			return updateStatus.getBody().getResult();
		
		throw new UpdatePasswordFailedException(updateStatus.getBody().getErrorMessage());
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
		
		ResponseEntity<ResponseDTO<AuthenticationDetailsDTO>> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/verifyLoginDetails",
					HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseDTO<AuthenticationDetailsDTO>>() {});
			
			
						
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}

		if(result.getStatusCode()==HttpStatus.OK && result.getBody().getErrorMessage().equals(""))
		{
			return result.getBody().getResult();
		}
		else
			throw new AuthenticationDetailsNotFoundException(result.getBody().getErrorMessage());
		
		
	}
	
	@Override
	public AuthenticationDetails getAuthenticationDetailsByCustomerIdType(
			Long customerId,Integer customerType) throws AuthenticationDetailsNotFoundException{

		CustomerIdTypeDTO customerIdDTO=new CustomerIdTypeDTO(customerId,customerType);
	
		HttpEntity<CustomerIdTypeDTO> entity=new HttpEntity<CustomerIdTypeDTO>(customerIdDTO);
		
		ResponseEntity<AuthenticationDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/getAuthenticationDetailsById", 
					HttpMethod.POST, entity, AuthenticationDetails.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new AuthenticationDetailsNotFoundException(AUTHENTICATION_DETAILS_NOT_FOUND_BY_ID);
		
				
	}

	@Override
	public AuthenticationDetailsDTO verifyEmailLoginDetails(
			VerifyEmailLoginDetails emailLoginDetails) throws AuthenticationDetailsNotFoundException{

		HttpEntity<VerifyEmailLoginDetails> entity=new HttpEntity<VerifyEmailLoginDetails>(emailLoginDetails);
		
		ResponseEntity<ResponseDTO<AuthenticationDetailsDTO>> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/verifyLoginDefaultEmailPassword",
					HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseDTO<AuthenticationDetailsDTO>>() {});
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.OK && result.getBody().getErrorMessage().equals(""))
			return result.getBody().getResult();
		else
			throw new AuthenticationDetailsNotFoundException(result.getBody().getErrorMessage());
	}

	@Override
	public Boolean sendOrResendOrResetPassword(Long customerId,Integer customerType,Integer emailOrMobile,Integer sendOrResendOrResetFlag,
			Integer requestedBy,Long requestedById) throws PasswordRestFailedException{
	
				
		SendResendResetPasswordRestDTO passwordDTO=new SendResendResetPasswordRestDTO(customerId, customerType, emailOrMobile,
				sendOrResendOrResetFlag, requestedBy, requestedById);
		
		HttpEntity<SendResendResetPasswordRestDTO> entity=new HttpEntity<SendResendResetPasswordRestDTO>(passwordDTO);
		
		ResponseEntity<ResponseDTO<Boolean>> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/sendOrResendOrResetPassword",
					HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
				
		if(result.getStatusCode()==HttpStatus.OK && result.getBody().getErrorMessage().equals(""))
			return result.getBody().getResult();
		else
			throw new PasswordRestFailedException(result.getBody().getErrorMessage());
		
		
	}

	/*
	@Override
	public Boolean resendPassword(Long customerId,Integer customerType,Integer emailOrMobile,
			Integer requestedBy,Long requestedById) throws PasswordRestFailedException{
	
		CustomerIdTypeEmailOrMobileOptionUpdatedBy customerIdDTO=new 
				CustomerIdTypeEmailOrMobileOptionUpdatedBy(customerId,customerType,emailOrMobile,requestedBy,requestedById);
		
		HttpEntity<CustomerIdTypeEmailOrMobileOptionUpdatedBy> entity=new HttpEntity<CustomerIdTypeEmailOrMobileOptionUpdatedBy>(customerIdDTO);
		
		ResponseEntity<ResponseDTO<Boolean>> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/resendPassword",
					HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
				
		if(result.getStatusCode()==HttpStatus.OK && result.getBody().getErrorMessage().equals(""))
			return result.getBody().getResult();
		else
			throw new PasswordRestFailedException(result.getBody().getErrorMessage());
		
		
	}
	*/
	
	@Override
	public ForgetPasswordEntity resetPasswordRedirect(String entityInp,Integer requestedBy,Long requestedById) throws PasswordRestFailedException{
		
		ResetPasswordRedirectDTO resetPasswordRedirectDTO=new ResetPasswordRedirectDTO(entityInp,requestedBy,requestedById);
		
		HttpEntity<ResetPasswordRedirectDTO> entity=new HttpEntity<ResetPasswordRedirectDTO>(resetPasswordRedirectDTO);
		
		ResponseEntity<ResponseDTO<ForgetPasswordEntity>> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/customer/quickregister/resetPasswordRedirect",
					HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseDTO<ForgetPasswordEntity>>() {});
	
		}catch(ValidationFailedException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK && result.getBody().getErrorMessage().equals(""))
			return result.getBody().getResult();
		else
			throw new PasswordRestFailedException(result.getBody().getErrorMessage());
	}
	

	

}
