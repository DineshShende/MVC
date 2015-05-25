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
import com.projectx.mvc.domain.completeregister.VehiclePermitType;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.completeregister.VehiclePermitTypeService;
import com.projectx.rest.domain.completeregister.VehiclePermitTypeList;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class VehiclePermitTypeHandler implements VehiclePermitTypeService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public VehiclePermitType save(VehiclePermitType vehiclePermitType) {

		HttpEntity<VehiclePermitType> entity=new HttpEntity<VehiclePermitType>(vehiclePermitType);
		
		ResponseEntity<ResponseDTO<VehiclePermitType>> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclepermittype", HttpMethod.POST,
					entity, new ParameterizedTypeReference<ResponseDTO<VehiclePermitType>>() {});
			
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
	public VehiclePermitType getById(Long vehiclePermitTypeId) {

		ResponseEntity<ResponseDTO<VehiclePermitType>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclepermittype/getById/"+vehiclePermitTypeId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<VehiclePermitType>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK)
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException();
	
		
		
	}

	@Override
	public Boolean deleteById(Long vehiclePermitTypeId) {

		ResponseEntity<ResponseDTO<Boolean>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclepermittype/deleteById/"+vehiclePermitTypeId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK && savedEntity.getBody().getErrorMessage().equals(""))
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException(savedEntity.getBody().getErrorMessage());
	
		
		
	}
	
	@Override
	public Boolean deleteAll() {
		
		Boolean result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehiclepermittype/clearTestData", Boolean.class);
		
		return result;
		
	}

	@Override
	public List<VehiclePermitType> getAll() {

		VehiclePermitTypeList result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehiclepermittype/getAll", VehiclePermitTypeList.class);
		
		return result.getList();
	}

}
