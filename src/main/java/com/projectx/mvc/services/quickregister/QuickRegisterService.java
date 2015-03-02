package com.projectx.mvc.services.quickregister;


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
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
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
	
	public QuickRegisterStatusDTO checkIfAlreadyExist(QuickRegisterEntity customerQuickRegisterEntity);
	
	public String populateMessageForDuplicationField(String duplicationStatus);
	
	public QuickRegisterSavedEntityDTO addNewCustomer(QuickRegisterEntity customerQuickRegisterEntity)
			throws QuickRegisterDetailsAlreadyPresentException;
	
	public QuickRegisterDTO getByCustomerIdType(CustomerIdTypeDTO customerIdDTO) throws QuickRegisterEntityNotFoundException;
	
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO);
	
	public Boolean verifyEmail(VerifyEmailDTO emailDTO);
	
	public Boolean reSendMobilePin(CustomerIdTypeMobileTypeDTO customerDTO);
	
	public Boolean reSendEmailHash(CustomerIdTypeEmailTypeDTO customerDTO);
	
	public Boolean reSetMobilePin(CustomerIdTypeMobileTypeDTO customerDTO);
	
	public Boolean reSetEmailHash(CustomerIdTypeEmailTypeDTO customerDTO);
	
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO);
	
	public ModelAndView populateCompleteRegisterRedirect(AuthenticationDetailsDTO authenticationDetailsDTO);
	
	public ModelAndView initialiseShowDetails(Long entityId,Integer entityType,ModelAndView map);
	
	public AuthenticationDetailsDTO verifyEmailLoginDetails(VerifyEmailLoginDetails emailLoginDetails) 
			throws AuthenticationDetailsNotFoundException;
	
	public AuthenticationDetailsDTO verifyLoginDetails(LoginVerificationDTO loginVerificationDTO) 
			throws AuthenticationDetailsNotFoundException;
	
	public Boolean resetPassword(Long customerId,Integer customerType) throws PasswordRestFailedException;
	
	public QuickRegisterDTO resetPasswordRedirect(String entity) throws PasswordRestFailedException;
	
	public void clearTestData();


	
	public EmailVerificationDetailsDTO getEmailVerificationDetailsByCustomerIdTypeAndEmail
		(Long customerId,Integer customerType,Integer emailType) throws EmailVerificationDetailNotFoundException;

	public MobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdTypeAndMobile
		(Long customerId,Integer customerType,Integer mobileType) throws MobileVerificationDetailsNotFoundException;
	
	public AuthenticationDetailsDTO getAuthenticationDetailsByCustomerIdType(Long customerId,Integer customerType)
		throws AuthenticationDetailsNotFoundException;
	
	
}
