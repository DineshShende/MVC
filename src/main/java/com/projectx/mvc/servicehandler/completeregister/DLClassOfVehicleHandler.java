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
import com.projectx.mvc.domain.completeregister.DLClassOfVehicle;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.completeregister.DLClassOfVehicleService;
import com.projectx.rest.domain.completeregister.CommodityList;
import com.projectx.rest.domain.completeregister.DLClassOfVehicleList;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class DLClassOfVehicleHandler implements DLClassOfVehicleService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public DLClassOfVehicle save(DLClassOfVehicle dLClassOfVehicle) {
		
		HttpEntity<DLClassOfVehicle> entity=new HttpEntity<DLClassOfVehicle>(dLClassOfVehicle);
		
		ResponseEntity<ResponseDTO<DLClassOfVehicle>> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/driver/classOfVehicle", HttpMethod.POST,
					entity, new ParameterizedTypeReference<ResponseDTO<DLClassOfVehicle>>() {});
			
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
	public DLClassOfVehicle getById(Long dLClassOfVehicleId) {
	
		ResponseEntity<ResponseDTO<DLClassOfVehicle>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/driver/classOfVehicle/getById/"+dLClassOfVehicleId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<DLClassOfVehicle>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK)
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException();
	
		
	}

	@Override
	public List<DLClassOfVehicle> getAll() {

		DLClassOfVehicleList result=restTemplate.getForObject(env.getProperty("rest.host")+"/driver/classOfVehicle/getAll", DLClassOfVehicleList.class);
		
		return result.getList();
		
	}

	@Override
	public Boolean deleteById(Long dLClassOfVehicleId) {

		ResponseEntity<ResponseDTO<Boolean>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/driver/classOfVehicle/deleteById/"+dLClassOfVehicleId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK && savedEntity.getBody().getErrorMessage().equals(""))
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException(savedEntity.getBody().getErrorMessage());
	
		
	}

	@Override
	public Boolean deleteAll() {

		Boolean result=restTemplate.getForObject(env.getProperty("rest.host")+"/driver/classOfVehicle/clearTestData", Boolean.class);
		
		return result;
		
	}

}
