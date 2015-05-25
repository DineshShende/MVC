package com.projectx.mvc.servicehandler.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.projectx.mvc.domain.completeregister.Commodity;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.completeregister.CommodityService;
import com.projectx.rest.domain.completeregister.CommodityList;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class CommodityHandler implements CommodityService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
		
	@Override
	public Commodity save(Commodity commodity) {

		HttpEntity<Commodity> entity=new HttpEntity<Commodity>(commodity);
		
		ResponseEntity<ResponseDTO<Commodity>> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/commodity", HttpMethod.POST,
					entity, new ParameterizedTypeReference<ResponseDTO<Commodity>>() {});
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
				
		if(savedEntity.getStatusCode()==HttpStatus.OK)		
			return savedEntity.getBody().getResult();
		else
			throw new ResourceAlreadyPresentException(savedEntity.getBody().getErrorMessage());
		
	}

	@Override
	public Commodity getById(Long CommodityId) {

		ResponseEntity<ResponseDTO<Commodity>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/commodity/getById/"+CommodityId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<Commodity>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK)
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException();
	
		
		
	}

	@Override
	public Boolean deleteById(Long CommodityId) {

		ResponseEntity<ResponseDTO<Boolean>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/commodity/deleteById/"+CommodityId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK && savedEntity.getBody().getErrorMessage().equals(""))
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException(savedEntity.getBody().getErrorMessage());
	
		
		
		
	}

	@Override
	public Boolean deleteAll() {
		
		Boolean result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/commodity/clearTestData", Boolean.class);
		
		return result;
		
	}

	@Override
	public List<Commodity> getAll() {

		CommodityList result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/commodity/getAll", CommodityList.class);
		
		return result.getList();
	}


}
