package com.projectx.mvc.servicehandler.completeregister;

import java.util.ArrayList;
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
import org.springframework.ui.Model;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.DriverList;
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
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	
	
	@Override
	public VendorDetailsDTO createCustomerDetailsFromQuickRegisterEntity(
			QuickRegisterDTO quickRegisterEntity) {
		
		if(quickRegisterEntity.getInsertTime()==null)
			quickRegisterEntity.setInsertTime(new Date());
			
		if(quickRegisterEntity.getUpdateTime()==null)	
			quickRegisterEntity.setUpdateTime(new Date());
		
		if(quickRegisterEntity.getEmail()!=null && quickRegisterEntity.getIsEmailVerified()==null)
			quickRegisterEntity.setIsEmailVerified(false);
		
		if(quickRegisterEntity.getMobile()!=null && quickRegisterEntity.getIsMobileVerified()==null)
			quickRegisterEntity.setIsMobileVerified(false);
	
		HttpEntity<QuickRegisterDTO> entity=new HttpEntity<QuickRegisterDTO>(quickRegisterEntity);
		
		ResponseEntity<VendorDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/createFromQuickRegister",
					HttpMethod.POST, entity, VendorDetailsDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new DeleteQuickCreateDetailsEntityFailedException();
		
	}

	@Override
	public VendorDetailsDTO update(VendorDetailsDTO vendorDetails) {
		
		if(vendorDetails.getEmail()!=null && vendorDetails.getIsEmailVerified()==null)
			vendorDetails.setIsEmailVerified(false);
		
		if(vendorDetails.getMobile()!=null && vendorDetails.getIsMobileVerified()==null)
			vendorDetails.setIsMobileVerified(false);
		
		HttpEntity<VendorDetailsDTO> entity=new HttpEntity<VendorDetailsDTO>(vendorDetails);
		
		ResponseEntity<VendorDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/update",
					HttpMethod.POST, entity, VendorDetailsDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
				
		if(result.getStatusCode()==HttpStatus.OK)		
			return result.getBody();
		else
			throw new VendorDetailsTransactionalUpdateFailedException();
		
		
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
	public Boolean verifyMobileDetails(VerifyMobileDTO verifyMobileDTO) {

		HttpEntity<VerifyMobileDTO> entity=new HttpEntity<VerifyMobileDTO>(verifyMobileDTO);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			result=restTemplate
					.exchange(env.getProperty("rest.host")+"/vendor/verifyMobileDetails",HttpMethod.POST, entity, Boolean.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		
		throw new ResourceNotFoundException();
		
	}

	@Override
	public Boolean verifyEmailDetails(VerifyEmailDTO verifyEmailDTO) {
	
		
		HttpEntity<VerifyEmailDTO> entity=new HttpEntity<VerifyEmailDTO>(verifyEmailDTO);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			result=restTemplate
					.exchange(env.getProperty("rest.host")+"/vendor/verifyEmailDetails",HttpMethod.POST, entity, Boolean.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		
		throw new ResourceNotFoundException();
		
		
		
	}

	@Override
	public Boolean sendMobileVerificationDetails(
			CustomerIdTypeMobileTypeUpdatedByDTO customerIdTypeMobileDTO) {
	
		
		HttpEntity<CustomerIdTypeMobileTypeUpdatedByDTO> entity=new HttpEntity<CustomerIdTypeMobileTypeUpdatedByDTO>(customerIdTypeMobileDTO);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			result=restTemplate
					.exchange(env.getProperty("rest.host")+"/vendor/sendMobileVerificationDetails",HttpMethod.POST, entity, Boolean.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		
		throw new ResourceNotFoundException();
	
		

	}

	@Override
	public Boolean sendEmailVerificationDetails(
			CustomerIdTypeEmailTypeUpdatedByDTO customerIdTypeEmailDTO) {

		
		HttpEntity<CustomerIdTypeEmailTypeUpdatedByDTO> entity=new HttpEntity<CustomerIdTypeEmailTypeUpdatedByDTO>(customerIdTypeEmailDTO);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			result=restTemplate
					.exchange(env.getProperty("rest.host")+"/vendor/sendEmailVerificationDetails",HttpMethod.POST, entity, Boolean.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		
		throw new ResourceNotFoundException();
	
		
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
			vendorDetails.setUpdatedBy("CUST_ONLINE");
		
		vendorDetails.setUpdateTime(new Date());
			
		if(vendorDetails.getInsertTime()==null)
			vendorDetails.setInsertTime(new Date());
		
		if(vendorDetails.getEmail().equals(""))
			vendorDetails.setEmail(null);
		
		if(vendorDetails.getFirmAddress()!=null)
		{
			if(vendorDetails.getFirmAddress().getUpdatedBy()==null)
				vendorDetails.getFirmAddress().setUpdatedBy("CUST_ONLINE");
			
			vendorDetails.getFirmAddress().setUpdateTime(new Date());
			
			if(vendorDetails.getFirmAddress().getInsertTime()==null)
				vendorDetails.getFirmAddress().setInsertTime(new Date());
			
			
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
	public DriverDetailsDTO addDriver(DriverDetailsDTO driverDetailsDTO) {
	
		driverDetailsDTO.setInsertTime(new Date());
		
		driverDetailsDTO.setUpdateTime(new Date());
		
		driverDetailsDTO.getHomeAddress().setInsertTime(new Date());
		
		driverDetailsDTO.getHomeAddress().setUpdateTime(new Date());
		
		driverDetailsDTO.setIsMobileVerified(false);
		
		driverDetailsDTO.getHomeAddress().setUpdatedBy(driverDetailsDTO.getUpdatedBy());
		
		HttpEntity<DriverDetailsDTO> entity=new HttpEntity<DriverDetailsDTO>(driverDetailsDTO);
		
		ResponseEntity<DriverDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/driver", HttpMethod.POST,
					entity, DriverDetailsDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.CREATED)		
			return result.getBody();
		else
			throw new DriverDetailsAlreadyPresentException();
		
	}

	@Override
	public DriverDetailsDTO update(DriverDetailsDTO driverDetailsDTO) {
		
		if(driverDetailsDTO.getInsertTime()==null)
			driverDetailsDTO.setInsertTime(new Date());
		
		driverDetailsDTO.setUpdateTime(new Date());
		
		if(driverDetailsDTO.getHomeAddress().getInsertTime()==null)
			driverDetailsDTO.getHomeAddress().setInsertTime(new Date());
		
		driverDetailsDTO.getHomeAddress().setUpdateTime(new Date());
		
		driverDetailsDTO.getHomeAddress().setUpdatedBy(driverDetailsDTO.getUpdatedBy());
		
		if(driverDetailsDTO.getIsMobileVerified()==null)
		driverDetailsDTO.setIsMobileVerified(false);
		
		HttpEntity<DriverDetailsDTO> entity=new HttpEntity<DriverDetailsDTO>(driverDetailsDTO);
		
		ResponseEntity<DriverDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/vendor/driver/update", HttpMethod.POST,
					entity, DriverDetailsDTO.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.OK)	
			return result.getBody();
		else
			throw new DriverDetailsUpdateFailedException();

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
	public Boolean deleteDriverById(Long driverId) {
		
		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/vendor/driver/deleteByDriverId/"+driverId, Boolean.class);
		
		return result;
		
	}

	@Override
	public ArrayList<DriverDetailsDTO> getDriversByVendor(Long venorId) {

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
		
		ResponseEntity<VehicleDetailsDTO> result=null;
		
		try{
			result=restTemplate
					.exchange(env.getProperty("rest.host")+"/vendor/vehicle",HttpMethod.POST, entity, VehicleDetailsDTO.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		
		throw new ResourceAlreadyPresentException(); 

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
