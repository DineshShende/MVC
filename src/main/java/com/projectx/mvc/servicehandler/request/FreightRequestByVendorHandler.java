package com.projectx.mvc.servicehandler.request;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.quickregister.QuickRegisterStatusDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomerList;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorList;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class FreightRequestByVendorHandler implements
		FreightRequestByVendorService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public FreightRequestByVendorDTO save(
			FreightRequestByVendorDTO freightRequestByCustomer) throws ResourceAlreadyPresentException,ValidationFailedException {

		
		if(freightRequestByCustomer.getInsertTime()==null)
			freightRequestByCustomer.setInsertTime(new Date());
		
		freightRequestByCustomer.setUpdateTime(new Date());
		
		HttpEntity<FreightRequestByVendorDTO> entity=new HttpEntity<FreightRequestByVendorDTO>(freightRequestByCustomer);
		
		ResponseEntity<FreightRequestByVendorDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/request/freightRequestByVendor",
					HttpMethod.POST,entity, FreightRequestByVendorDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		
		throw new ResourceAlreadyPresentException();
		
		
	}

	@Override
	public FreightRequestByVendorDTO getRequestById(Long requestId) throws ValidationFailedException{

		ResponseEntity<FreightRequestByVendorDTO> status=restTemplate
				.exchange(env.getProperty("rest.host")+"/request/freightRequestByVendor/getById/"+requestId,HttpMethod.GET,null,FreightRequestByVendorDTO.class);
		
		if(status.getStatusCode()==HttpStatus.FOUND)
			return status.getBody();
		else
			throw new ValidationFailedException();
		
	}

	@Override
	public List<FreightRequestByVendorDTO> getAllRequestForVendor(Long vendorId) {

		FreightRequestByVendorList status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/findByVendorId/"+vendorId, FreightRequestByVendorList.class);
		
		return status.getRequestList();


		
	}
	
	@Override
	public List<FreightRequestByVendorDTO> getMatchingVendorReqForCustReq(
			FreightRequestByCustomerDTO freightRequestByCustomer) {
	
		FreightRequestByVendorList status=null;
		
		try{
			status=restTemplate.postForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/getMatchingVendorReqForCustomerReq",
					freightRequestByCustomer, FreightRequestByVendorList.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		return status.getRequestList();
		
		
		
		
	}

	@Override
	public Boolean deleteRequestById(Long requestId) {
		
		Boolean status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/deleteById/"+requestId, Boolean.class);
		
		return status;

	}

	@Override
	public Boolean clearTestData() {

		Boolean status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/clearTestData", Boolean.class);
		
		return status;

	}

	@Override
	public Integer count() {

		Integer status=restTemplate
				.getForObject(env.getProperty("rest.host")+"/request/freightRequestByVendor/count", Integer.class);
		
		return status;
		
	}



}
