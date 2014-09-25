package com.projectx.mvc.services;

import org.springframework.stereotype.Component;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;

@Component
public interface CustomerQuickRegisterService {
	
	//public String setStatus(CustomerQuickRegisterEntity customerQuickRegisterEntity) throws Exception;
	
	public String checkIfAlreadyExist(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	
	public CustomerQuickRegisterDTO addNewCustomer(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	
	public Boolean verifyMobile(VerifyMobileDTO mobileDTO);
	
	public Boolean verifyEmail(VerifyEmailDTO emailDTO);
	
	public CustomerQuickRegisterDTO getByEmail(String email);
	
	public CustomerQuickRegisterDTO getByMobile(Long mobile);

}
