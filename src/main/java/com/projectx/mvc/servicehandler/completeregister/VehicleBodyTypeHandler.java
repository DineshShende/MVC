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
import com.projectx.mvc.domain.completeregister.VehicleBodyType;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.completeregister.VehicleBodyTypeService;
import com.projectx.rest.domain.completeregister.VehicleBodyTypeList;


@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class VehicleBodyTypeHandler implements VehicleBodyTypeService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public VehicleBodyType save(VehicleBodyType vehicleBodyType) {
		
		HttpEntity<VehicleBodyType> entity=new HttpEntity<VehicleBodyType>(vehicleBodyType);
		
		ResponseEntity<ResponseDTO<VehicleBodyType>> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclebodytype", HttpMethod.POST,
					entity, new ParameterizedTypeReference<ResponseDTO<VehicleBodyType>>() {});
			
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
	public VehicleBodyType getById(Long vehicleBodyTypeId) {

		ResponseEntity<ResponseDTO<VehicleBodyType>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclebodytype/getById/"+vehicleBodyTypeId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<VehicleBodyType>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK)
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException();
		
	}

	@Override
	public Boolean deleteById(Long vehicleBodyTypeId) {

		ResponseEntity<ResponseDTO<Boolean>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclebodytype/deleteById/"+vehicleBodyTypeId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK && savedEntity.getBody().getErrorMessage().equals(""))
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException(savedEntity.getBody().getErrorMessage());
		
		
	}
	
	@Override
	public Boolean deleteAll() {
		
		Boolean result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehiclebodytype/clearTestData", Boolean.class);
		
		return result;
		
	}

	@Override
	public List<VehicleBodyType> getAll() {
		
		VehicleBodyTypeList result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehiclebodytype/getAll", VehicleBodyTypeList.class);
		
		return result.getList();
	}

}
