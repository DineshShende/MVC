package com.projectx.mvc.services;


import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.projectx.mvc.domain.CustomerDocumetDTO;
import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.domain.UpdatePasswordDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetailsDTO;
import com.projectx.rest.domain.CustomerEmailVerificationDetailsDTO;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerIdEmailDTO;
import com.projectx.rest.domain.CustomerIdMobileDTO;
import com.projectx.rest.domain.CustomerMobileVerificationDetailsDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.CustomerQuickRegisterStringStatusDTO;
import com.projectx.rest.domain.LoginVerificationDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyEmailLoginDetails;
import com.projectx.rest.domain.VerifyMobileDTO;

@Component
public interface CustomerQuickRegisterService {
	
	public CustomerQuickRegisterStringStatusDTO checkIfAlreadyExist(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	
	public String populateMessageForDuplicationField(String duplicationStatus);
	
	public CustomerQuickRegisterSavedEntityDTO addNewCustomer(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	
	//public ModelAndView handleNewCustomer(CustomerQuickRegisterEntity customer);
	
	public CustomerQuickRegisterDTO getByCustomerId(CustomerIdDTO customerIdDTO);
	
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO);
	
	public Boolean verifyEmail(VerifyEmailDTO emailDTO);
	
	public Boolean reSendMobilePin(CustomerIdMobileDTO customerDTO);
	
	public Boolean reSendEmailHash(CustomerIdEmailDTO customerDTO);
	
	public Boolean reSetMobilePin(CustomerIdMobileDTO customerDTO);
	
	public Boolean reSetEmailHash(CustomerIdEmailDTO customerDTO);
	
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO);
	
	//TODO
	//public Boolean forgotPassword(String entity);
	
	public CustomerAuthenticationDetailsDTO verifyEmailLoginDetails(VerifyEmailLoginDetails emailLoginDetails);
	
	public CustomerAuthenticationDetailsDTO verifyLoginDetails(LoginVerificationDTO loginVerificationDTO);
	
	public Boolean resetPassword(Long customerId);
	
	public CustomerQuickRegisterDTO resetPasswordRedirect(String entity);
	
	public void clearTestData();

	//Getter
	
	public CustomerEmailVerificationDetailsDTO getEmailVerificationDetailsByCustomerIdAndEmail(Long customerId,String email);

	public CustomerMobileVerificationDetailsDTO getMobileVerificationDetailsByCustomerIdAndMobile(Long customerId,Long mobile);
	
	public CustomerAuthenticationDetailsDTO getAuthenticationDetailsByCustomerId(Long customerId);
	
	
	//Document
	
	public CustomerDocumetDTO saveCustomerDocumet(CustomerDocumetDTO customerDocumetDTO);
	
	public CustomerDocumetDTO getCustomerDocumetById(Long customerId);
	
	//public String setStatus(CustomerQuickRegisterEntity customerQuickRegisterEntity) throws Exception;
	
	//public CustomerQuickRegisterDTO getByEmail(String email);
	
	//public CustomerQuickRegisterDTO getByMobile(Long mobile);
}
