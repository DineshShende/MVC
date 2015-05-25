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
import com.projectx.mvc.domain.completeregister.VehicleType;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.completeregister.VehicleTypeService;
import com.projectx.rest.domain.completeregister.VehicleTypeList;


@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class VehicleTypeHandler implements VehicleTypeService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public VehicleType save(VehicleType vehicleType) {
	
		HttpEntity<VehicleType> entity=new HttpEntity<VehicleType>(vehicleType);
		
		ResponseEntity<ResponseDTO<VehicleType>> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehicletype", HttpMethod.POST,
					entity, new ParameterizedTypeReference<ResponseDTO<VehicleType>>() {});
			
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
	public VehicleType getById(Long vehicleTypeId) {

		ResponseEntity<ResponseDTO<VehicleType>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehicletype/getById/"+vehicleTypeId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<VehicleType>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK)
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException();

		

		
	}

	@Override
	public Boolean deleteById(Long vehicleTypeId) {
		
		ResponseEntity<ResponseDTO<Boolean>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehicletype/deleteById/"+vehicleTypeId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK && savedEntity.getBody().getErrorMessage().equals(""))
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException(savedEntity.getBody().getErrorMessage());

		
	}

	@Override
	public Boolean deleteAll() {
		
		Boolean result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehicletype/clearTestData", Boolean.class);
		
		return result;
		
	}

	@Override
	public List<VehicleType> getAll() {
		
		VehicleTypeList result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehicletype/getAll", VehicleTypeList.class);
		
		return result.getList();
	}

}
