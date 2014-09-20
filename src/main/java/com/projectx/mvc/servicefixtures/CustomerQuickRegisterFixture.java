package com.projectx.mvc.servicefixtures;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.services.CustomerQuickRegisterService;

@Component
public class CustomerQuickRegisterFixture implements
		CustomerQuickRegisterService {

	@Override
	public String setStatus(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) throws Exception {
		String status = null;

		if(customerQuickRegisterEntity.getEmail().equalsIgnoreCase("") && customerQuickRegisterEntity.getMobile()==null)
			throw new Exception();
		
		if (!customerQuickRegisterEntity.getEmail().equalsIgnoreCase("")
				&& customerQuickRegisterEntity.getMobile() != null) {
			status = "EmailMobileVerificationPending";
		} else if (!customerQuickRegisterEntity.getEmail().equalsIgnoreCase("")) {
			status = "EmailVerificationPending";
		} else if (customerQuickRegisterEntity.getMobile() != null) {
			status = "MobileVerificationPending";
		}

		customerQuickRegisterEntity.setStatus(status);

		return status;

	}

	@Override
	public String checkIfAlreadyExist(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		RestTemplate rest=new RestTemplate();
		
		rest.postForObject("http://localhost:9080/customer/quickregister/checkifexist", customerQuickRegisterEntity, String.class);

		
		return "";
	}

	

}
