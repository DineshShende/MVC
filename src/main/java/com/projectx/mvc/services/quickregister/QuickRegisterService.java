package com.projectx.mvc.services.quickregister;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.PasswordRestFailedException;
import com.projectx.mvc.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.mvc.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.ForgetPasswordEntity;
import com.projectx.rest.domain.quickregister.MobilePinPasswordDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStatusDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailLoginDetails;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

@Component
public interface QuickRegisterService {
	
	public String populateMessageForDuplicationField(String duplicationStatus);
	
	public QuickRegisterSavedEntityDTO addNewCustomer(QuickRegisterEntity customerQuickRegisterEntity)
			throws QuickRegisterDetailsAlreadyPresentException;
	
	public QuickRegisterDTO getByCustomerIdType(CustomerIdTypeDTO customerIdDTO) throws QuickRegisterEntityNotFoundException;
	
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO);
	
	public Boolean verifyEmail(VerifyEmailDTO emailDTO);
	
	public Boolean sendMobilePin(CustomerIdTypeMobileTypeRequestedByDTO customerDTO);
	
	public Boolean reSendMobilePin(CustomerIdTypeMobileTypeRequestedByDTO customerDTO);
	
	public Boolean sendEmailHash(CustomerIdTypeEmailTypeUpdatedByDTO customerDTO);
	
	public Boolean reSendEmailHash(CustomerIdTypeEmailTypeUpdatedByDTO customerDTO);
	
	public Boolean reSetMobilePin(CustomerIdTypeMobileTypeRequestedByDTO customerDTO);
	
	public Boolean reSetEmailHash(CustomerIdTypeEmailTypeUpdatedByDTO customerDTO);
	
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO);
	
	public AuthenticationDetailsDTO verifyEmailLoginDetails(VerifyEmailLoginDetails emailLoginDetails) 
			throws AuthenticationDetailsNotFoundException;
	
	public AuthenticationDetailsDTO verifyLoginDetails(LoginVerificationDTO loginVerificationDTO) 
			throws AuthenticationDetailsNotFoundException;
	
	public Boolean resetPassword(Long customerId,Integer customerType,Integer emailOrMobile,Integer requestedBy,Long requestedById) throws PasswordRestFailedException;
	
	public Boolean resendPassword(Long customerId,Integer customerType,Integer emailOrMobile,Integer requestedBy,Long requestedById) throws PasswordRestFailedException;
	
	public ForgetPasswordEntity resetPasswordRedirect(String entity,Integer requestedBy,Long requestedById) throws PasswordRestFailedException;
	
	public void clearTestData();


	
	public EmailVerificationDetailsDTO getEmailVerificationDetailsByCustomerIdTypeAndEmail
		(Long customerId,Integer customerType,Integer emailType) throws EmailVerificationDetailNotFoundException;

	public MobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdTypeAndMobile
		(Long customerId,Integer customerType,Integer mobileType) throws MobileVerificationDetailsNotFoundException;
	
	public AuthenticationDetails getAuthenticationDetailsByCustomerIdType(Long customerId,Integer customerType)
		throws AuthenticationDetailsNotFoundException;
	
		
}
