package com.projectx.mvc.servicehandler.completeregister;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.ui.Model;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.commn.ResponseDTO;
import com.projectx.mvc.exception.repository.completeregister.DriverDetailsAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.DriverDetailsNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.DriverDetailsUpdateFailedException;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.exception.repository.completeregister.VehicleDetailsNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.VendorDetailsTransactionalUpdateFailedException;
import com.projectx.mvc.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.DriverList;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.completeregister.VehicleList;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class VendorDetailsHandler implements VendorDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private Integer ENTITY_TYPE_DRIVER=3;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	
	
	@Override
	public VendorDetailsDTO update(VendorDetailsDTO vendorDetails) {
		
		if(vendorDetails.getEmail()!=null && vendorDetails.getIsEmailVerified()==null)
			vendorDetails.setIsEmailVerified(false);
		
		if(vendorDetails.getMobile()!=null && vendorDetails.getIsMobileVerified()==null)
			vendorDetails.setIsMobileVerified(false);
		
		HttpEntity<VendorDetailsDTO> entity=new HttpEntity<VendorDetailsDTO>(vendorDetails);
		
		ResponseEntity<ResponseDTO<VendorDetailsDTO>> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/update",
					HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseDTO<VendorDetailsDTO>>() {});
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
				
		if(result.getStatusCode()==HttpStatus.OK && result.getBody().getErrorMessage().equals(""))		
			return result.getBody().getResult();
		else
			throw new VendorDetailsTransactionalUpdateFailedException(result.getBody().getErrorMessage());
		
		
	}

	@Override
	public VendorDetailsDTO getCustomerDetailsById(Long vendorId) {

		ResponseEntity<VendorDetailsDTO> result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/getVendorDetailsById/"+vendorId,
				HttpMethod.GET, null, VendorDetailsDTO.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new VendorDetailsNotFoundException();

		
	}


	@Override
	public Integer count() {

		Integer count=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/count", Integer.class);
		
		return count;
		
	}

	@Override
	public Boolean clearTestData() {
	
		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/clearTestData", Boolean.class);
		
		return result;
		
		
	}

	@Override
	public VendorDetailsDTO intializeMetaData(VendorDetailsDTO vendorDetails) {
		
		if(vendorDetails.getUpdatedBy()==null)
			vendorDetails.setUpdatedBy(vendorDetails.getUpdatedBy());
		
		if(vendorDetails.getInsertedBy()==null)
			vendorDetails.setInsertedBy(vendorDetails.getUpdatedBy());
		
		if(vendorDetails.getUpdatedById()==null)
			vendorDetails.setUpdatedById(vendorDetails.getUpdatedById());
		
		if(vendorDetails.getInsertedById()==null)
			vendorDetails.setInsertedById(vendorDetails.getUpdatedById());
		
		vendorDetails.setUpdateTime(new Date());
			
		if(vendorDetails.getInsertTime()==null)
			vendorDetails.setInsertTime(new Date());
		
		if(vendorDetails.getEmail().equals(""))
			vendorDetails.setEmail(null);
		
		if(vendorDetails.getFirmAddress()!=null)
		{
			if(vendorDetails.getFirmAddress().getUpdatedBy()==null)
				vendorDetails.getFirmAddress().setUpdatedBy(vendorDetails.getUpdatedBy());
			
			if(vendorDetails.getFirmAddress().getInsertedBy()==null)
				vendorDetails.getFirmAddress().setInsertedBy(vendorDetails.getUpdatedBy());
			
			if(vendorDetails.getFirmAddress().getUpdatedById()==null)
				vendorDetails.getFirmAddress().setUpdatedById(vendorDetails.getUpdatedById());
			
			if(vendorDetails.getFirmAddress().getInsertedById()==null)
				vendorDetails.getFirmAddress().setInsertedById(vendorDetails.getUpdatedById());
			
			vendorDetails.getFirmAddress().setUpdateTime(new Date());
			
			if(vendorDetails.getFirmAddress().getInsertTime()==null)
				vendorDetails.getFirmAddress().setInsertTime(new Date());
			
			
		}
		
		if(vendorDetails.getHomeAddress()!=null)
		{
			if(vendorDetails.getHomeAddress().getUpdatedBy()==null)
				vendorDetails.getHomeAddress().setUpdatedBy(vendorDetails.getUpdatedBy());
			
			if(vendorDetails.getHomeAddress().getInsertedBy()==null)
				vendorDetails.getHomeAddress().setInsertedBy(vendorDetails.getUpdatedBy());
			
			if(vendorDetails.getHomeAddress().getUpdatedById()==null)
				vendorDetails.getHomeAddress().setUpdatedById(vendorDetails.getUpdatedById());
			
			if(vendorDetails.getHomeAddress().getInsertedById()==null)
				vendorDetails.getHomeAddress().setInsertedById(vendorDetails.getUpdatedById());
			
			
			vendorDetails.getHomeAddress().setUpdateTime(new Date());
			
			if(vendorDetails.getHomeAddress().getInsertTime()==null)
				vendorDetails.getHomeAddress().setInsertTime(new Date());
			
			
		}
		
		return vendorDetails;

		
	}

	@Override
	public Model initialiseShowVendorDetails(Long entityId, Model model) {
		
		
		
		EmailVerificationDetailsDTO emailVerificationDetails=null;
		
		try{
			emailVerificationDetails=quickRegisterService
					.getEmailVerificationDetailsByCustomerIdTypeAndEmail(entityId,ENTITY_TYPE_VENDOR , ENTITY_TYPE_PRIMARY);
		}catch(ResourceNotFoundException e)
		{
			emailVerificationDetails=new EmailVerificationDetailsDTO();
		}
		
		
		MobileVerificationDetailsDTO mobileVerificationDetailsPrimary;
		
		try{
			mobileVerificationDetailsPrimary=quickRegisterService
					.getMobileVerificationDetailsByCustomerIdTypeAndMobile(entityId, ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY);
			
		}catch(ResourceNotFoundException e)
		{
			mobileVerificationDetailsPrimary=new MobileVerificationDetailsDTO();
		}
		
		
		
		model.addAttribute("emailVerificationDetails", emailVerificationDetails);
		model.addAttribute("mobileVerificationDetailsPrimary", mobileVerificationDetailsPrimary);
		
		
		return model;
		
		
	}

	@Override
	public DriverDetailsDTO initializeDriverDetails(
			DriverDetailsDTO driverDetailsDTO) {
	
		if(driverDetailsDTO.getUpdatedBy()==null)
			driverDetailsDTO.setUpdatedBy(driverDetailsDTO.getUpdatedBy());
		
		if(driverDetailsDTO.getInsertedBy()==null)
			driverDetailsDTO.setInsertedBy(driverDetailsDTO.getUpdatedBy());
		
		if(driverDetailsDTO.getUpdatedById()==null)
			driverDetailsDTO.setUpdatedById(driverDetailsDTO.getUpdatedById());
		
		if(driverDetailsDTO.getInsertedById()==null)
			driverDetailsDTO.setInsertedById(driverDetailsDTO.getUpdatedById());
		
		if(driverDetailsDTO.getInsertTime()==null)
			driverDetailsDTO.setInsertTime(new Date());
		
		driverDetailsDTO.setUpdateTime(new Date());
		
		if(driverDetailsDTO.getHomeAddress().getInsertTime()==null)
			driverDetailsDTO.getHomeAddress().setInsertTime(new Date());
	
		if(driverDetailsDTO.getHomeAddress().getUpdatedBy()==null)
			driverDetailsDTO.getHomeAddress().setUpdatedBy(driverDetailsDTO.getUpdatedBy());
		
		if(driverDetailsDTO.getHomeAddress().getInsertedBy()==null)
			driverDetailsDTO.getHomeAddress().setInsertedBy(driverDetailsDTO.getUpdatedBy());
	
		if(driverDetailsDTO.getHomeAddress().getUpdatedById()==null)
			driverDetailsDTO.getHomeAddress().setUpdatedById(driverDetailsDTO.getUpdatedById());
		
		if(driverDetailsDTO.getHomeAddress().getInsertedById()==null)
			driverDetailsDTO.getHomeAddress().setInsertedById(driverDetailsDTO.getUpdatedById());
		
		driverDetailsDTO.getHomeAddress().setCustomerType(ENTITY_TYPE_DRIVER);
		
		driverDetailsDTO.getHomeAddress().setUpdateTime(new Date());
		
		driverDetailsDTO.getHomeAddress().setUpdatedBy(driverDetailsDTO.getUpdatedBy());
		
		if(driverDetailsDTO.getIsMobileVerified()==null)
		driverDetailsDTO.setIsMobileVerified(false);
		
		return driverDetailsDTO;
	
	}
	
	@Override
	public DriverDetailsDTO addDriver(DriverDetailsDTO driverDetailsDTO) {
	
			
		HttpEntity<DriverDetailsDTO> entity=new HttpEntity<DriverDetailsDTO>(driverDetailsDTO);
		
		ResponseEntity<ResponseDTO<DriverDetailsDTO>> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/driver", HttpMethod.POST,
					entity, new ParameterizedTypeReference<ResponseDTO<DriverDetailsDTO>>() {});
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.CREATED)		
			return result.getBody().getResult();
		else
			throw new DriverDetailsAlreadyPresentException(result.getBody().getErrorMessage());
		
	}


	@Override
	public DriverDetailsDTO getDriverById(Long driverId) {
		
		ResponseEntity<DriverDetailsDTO> result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/driver/getByDriverId/"+driverId, 
				HttpMethod.GET, null, DriverDetailsDTO.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new DriverDetailsNotFoundException();
		
		
	}
	
	@Override
	public DriverDetailsDTO getDriverByLicenceNumber(String licenceNumber) {
		
		ResponseEntity<DriverDetailsDTO> result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/driver/getByLicenceNumber/"+licenceNumber, 
				HttpMethod.GET, null, DriverDetailsDTO.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new DriverDetailsNotFoundException();
		
		
	}

	@Override
	public Boolean deleteDriverById(Long driverId) {
		
		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/driver/deleteByDriverId/"+driverId, Boolean.class);
		
		return result;
		
	}

	@Override
	public List<DriverDetailsDTO> getDriversByVendor(Long venorId) {

		DriverList result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/driver/getDriversByVendor/"+venorId, DriverList.class);
		
		return result.getDriverList();

		
	}

	@Override
	public Integer vehicleCount() {
		
		Integer result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/vehicle/count", Integer.class);
		
		return result;

		
	}

	@Override
	public Boolean vehicleClearTestData() {

		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/vehicle/clearTestData", Boolean.class);
		
		return result;

	}

	@Override
	public VehicleDetailsDTO save(VehicleDetailsDTO vehicleDetailsDTO) {
		
		if(vehicleDetailsDTO.getInsertTime()==null)
			vehicleDetailsDTO.setInsertTime(new Date());
		
		vehicleDetailsDTO.setUpdateTime(new Date());
		
		HttpEntity<VehicleDetailsDTO> entity=new HttpEntity<VehicleDetailsDTO>(vehicleDetailsDTO);
		
		ResponseEntity<ResponseDTO<VehicleDetailsDTO>> result=null;
		
		try{
			result=restTemplate
					.exchange(env.getProperty("rest.host")+"/vendor/vehicle",HttpMethod.POST, entity,
							new ParameterizedTypeReference<ResponseDTO<VehicleDetailsDTO>>() {});
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody().getResult();
		
		throw new ResourceAlreadyPresentException(result.getBody().getErrorMessage()); 

	}

	@Override
	public VehicleDetailsDTO getVehicleById(Long vehicleId) {
		
		ResponseEntity<VehicleDetailsDTO> result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/vehicle/getByVehicleId/"+vehicleId,
					HttpMethod.GET, null, VehicleDetailsDTO.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new VehicleDetailsNotFoundException();

		
	}

	@Override
	public Boolean deleteVehicleById(Long vehicleId) {
	
		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/vehicle/deleteByVehicleId/"+vehicleId, Boolean.class);
		
		return result;
		
	}

	@Override
	public List<VehicleDetailsDTO> getVehiclesByvendor(Long vendorId) {
		
		VehicleList result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/vehicle/getVehicleByVendor/"+vendorId, VehicleList.class);
		
		return result.getVehicleList();

	}

	@Override
	public Integer driverCount() {
		
		Integer result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/driver/count", Integer.class);
		
		return result;
	}

	@Override
	public Boolean driverClearTestData() {
		
		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/driver/clearTestData", Boolean.class);
		
		return result;

	}

	

}
