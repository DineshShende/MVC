package com.projectx.mvc.services.quickregister;


import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
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
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailLoginDetails;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

@Component
public interface QuickRegisterService {
	
	public QuickRegisterStringStatusDTO checkIfAlreadyExist(QuickRegisterEntity customerQuickRegisterEntity);
	
	public String populateMessageForDuplicationField(String duplicationStatus);
	
	public QuickRegisterSavedEntityDTO addNewCustomer(QuickRegisterEntity customerQuickRegisterEntity);
	
	//public ModelAndView handleNewCustomer(CustomerQuickRegisterEntity customer);
	
	public QuickRegisterDTO getByCustomerIdType(CustomerIdTypeDTO customerIdDTO);
	
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO);
	
	public Boolean verifyEmail(VerifyEmailDTO emailDTO);
	
	public Boolean reSendMobilePin(CustomerIdTypeMobileTypeDTO customerDTO);
	
	public Boolean reSendEmailHash(CustomerIdTypeEmailTypeDTO customerDTO);
	
	public Boolean reSetMobilePin(CustomerIdTypeMobileTypeDTO customerDTO);
	
	public Boolean reSetEmailHash(CustomerIdTypeEmailTypeDTO customerDTO);
	
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO);
	
	public ModelAndView populateCompleteRegisterRedirect(AuthenticationDetailsDTO authenticationDetailsDTO);
	
	public ModelAndView initialiseShowDetails(Long entityId,Integer entityType,ModelAndView map);
	
	//TODO
	//public Boolean forgotPassword(String entity);
	
	public AuthenticationDetailsDTO verifyEmailLoginDetails(VerifyEmailLoginDetails emailLoginDetails);
	
	public AuthenticationDetailsDTO verifyLoginDetails(LoginVerificationDTO loginVerificationDTO);
	
	public Boolean resetPassword(Long customerId,Integer customerType);
	
	public QuickRegisterDTO resetPasswordRedirect(String entity);
	
	public void clearTestData();

	//Getter
	
	public EmailVerificationDetailsDTO getEmailVerificationDetailsByCustomerIdTypeAndEmail(Long customerId,Integer customerType,Integer emailType);

	public MobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdTypeAndMobile(Long customerId,Integer customerType,Integer mobileType);
	
	public AuthenticationDetailsDTO getAuthenticationDetailsByCustomerIdType(Long customerId,Integer customerType);
	
	
	//Document
	
	public CustomerDocumetDTO saveCustomerDocumet(CustomerDocumetDTO customerDocumetDTO);
	
	public CustomerDocumetDTO getCustomerDocumetById(Long customerId);
	
	//public String setStatus(CustomerQuickRegisterEntity customerQuickRegisterEntity) throws Exception;
	
	//public CustomerQuickRegisterDTO getByEmail(String email);
	
	//public CustomerQuickRegisterDTO getByMobile(Long mobile);
}