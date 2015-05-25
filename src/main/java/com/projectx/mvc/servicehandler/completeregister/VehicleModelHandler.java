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
import com.projectx.mvc.domain.completeregister.VehicleModel;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.completeregister.VehicleModelService;
import com.projectx.rest.domain.completeregister.VehicleModelList;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class VehicleModelHandler implements VehicleModelService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public VehicleModel save(VehicleModel vehicleModel) {
		
		HttpEntity<VehicleModel> entity=new HttpEntity<VehicleModel>(vehicleModel);
		
		ResponseEntity<ResponseDTO<VehicleModel>> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclemodel", HttpMethod.POST,
					entity, new ParameterizedTypeReference<ResponseDTO<VehicleModel>>() {});
			
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
	public VehicleModel getById(Long vehicleModelId) {
		
		ResponseEntity<ResponseDTO<VehicleModel>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclemodel/getById/"+vehicleModelId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<VehicleModel>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK)
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException();



		
	}

	@Override
	public Boolean deleteById(Long vehicleModelId) {
		
		ResponseEntity<ResponseDTO<Boolean>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclemodel/deleteById/"+vehicleModelId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK && savedEntity.getBody().getErrorMessage().equals(""))
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException(savedEntity.getBody().getErrorMessage());


	}

	@Override
	public Boolean deleteAll() {
		
		Boolean result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehiclemodel/clearTestData", Boolean.class);
		
		return result;
		
	}

	@Override
	public List<VehicleModel> getAll() {
	
		VehicleModelList result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehiclemodel/getAll", VehicleModelList.class);
		
		return result.getList();

	}

}
