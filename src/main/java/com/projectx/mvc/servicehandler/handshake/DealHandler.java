package com.projectx.mvc.servicehandler.handshake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.handshake.DealService;
import com.projectx.rest.domain.handshake.DealInfoAndVendorContactDetailsDTO;
import com.projectx.rest.domain.handshake.TriggerDealDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;

@Component
@Profile(value={"Dev","Prod","Test"})
@PropertySource(value="classpath:/application.properties")
public class DealHandler implements DealService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public DealInfoAndVendorContactDetailsDTO triggerDeal(
			TriggerDealDTO triggerDealDTO) {
		
		HttpEntity<TriggerDealDTO> entity=new HttpEntity<TriggerDealDTO>(triggerDealDTO);
		
		ResponseEntity<DealInfoAndVendorContactDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/deal/triggerdeal",
					HttpMethod.POST, entity, DealInfoAndVendorContactDetailsDTO.class);
		}catch(ValidationFailedException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
		{
			return result.getBody();
		}
		
		throw new ValidationFailedException(); 
		
	}

}
