package com.projectx.mvc.services;

import org.springframework.stereotype.Component;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;

@Component
public interface CustomerQuickRegisterService {
	
	public String setStatus(CustomerQuickRegisterEntity customerQuickRegisterEntity) throws Exception;
	
	public String checkIfAlreadyExist(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	

}
