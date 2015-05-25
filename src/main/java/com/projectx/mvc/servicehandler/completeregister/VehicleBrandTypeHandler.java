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
import com.projectx.mvc.domain.completeregister.VehicleBrand;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.completeregister.VehicleBrandTypeService;
import com.projectx.rest.domain.completeregister.VehicleBrandTypeList;


@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class VehicleBrandTypeHandler implements VehicleBrandTypeService {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	
	
	@Override
	public VehicleBrand save(VehicleBrand vehicleBrand) {

		HttpEntity<VehicleBrand> entity=new HttpEntity<VehicleBrand>(vehicleBrand);
		
		ResponseEntity<ResponseDTO<VehicleBrand>> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclebrand", HttpMethod.POST,
					entity, new ParameterizedTypeReference<ResponseDTO<VehicleBrand>>() {});
			
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
	public VehicleBrand getById(Long vehicleBrandId) {
		
		ResponseEntity<ResponseDTO<VehicleBrand>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclebrand/getById/"+vehicleBrandId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<VehicleBrand>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK)
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException();

		
		
	}

	@Override
	public Boolean deleteById(Long vehicleBrandId) {
		
		ResponseEntity<ResponseDTO<Boolean>> savedEntity=restTemplate.exchange(env.getProperty("rest.host")+"/vehicle/vehiclebrand/deleteById/"+vehicleBrandId, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		
	
		if(savedEntity.getStatusCode()==HttpStatus.OK && savedEntity.getBody().getErrorMessage().equals(""))
				return savedEntity.getBody().getResult();
		else
			throw new ResourceNotFoundException(savedEntity.getBody().getErrorMessage());

	}
	
	@Override
	public Boolean deleteAll() {
		
		Boolean result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehiclebrand/clearTestData", Boolean.class);
		
		return result;
		
	}

	@Override
	public List<VehicleBrand> getAll() {

		VehicleBrandTypeList result=restTemplate.getForObject(env.getProperty("rest.host")+"/vehicle/vehiclebrand/getAll", VehicleBrandTypeList.class);
		
		return result.getList();

		
	}

}
