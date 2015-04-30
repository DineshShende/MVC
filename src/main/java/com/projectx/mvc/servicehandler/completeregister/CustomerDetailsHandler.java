package com.projectx.mvc.servicehandler.completeregister;

import java.util.Date;

import javax.validation.ValidationException;

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
import com.projectx.mvc.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;




@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class CustomerDetailsHandler implements CustomerDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ENTITY_TYPE_SECONDARY=2;

	/*
	@Override
	public CustomerDetailsDTO createCustomerDetailsFromQuickRegisterEntity(
			Long entityId) {
		
		
		HttpEntity<EntityIdDTO> entity=new HttpEntity<EntityIdDTO>(new EntityIdDTO(entityId));
		
		ResponseEntity<CustomerDetailsDTO> result=
				restTemplate.exchange(env.getProperty("rest.host")+"/customer/createFromQuickRegister", HttpMethod.POST, 
						entity, CustomerDetailsDTO.class);
				
		if(result.getStatusCode()==HttpStatus.OK)
				return result.getBody();
		else
			throw new DeleteQuickCreateDetailsEntityFailedException();
		
		
	}
	*/

	@Override
	public CustomerDetailsDTO merge(CustomerDetailsDTO customerDetails) {
		
		HttpEntity<CustomerDetailsDTO> entity=new HttpEntity<CustomerDetailsDTO>(customerDetails);
		
		if(customerDetails.getEmail()!=null && customerDetails.getIsEmailVerified()==null)
			customerDetails.setIsEmailVerified(false);
		
		if(customerDetails.getMobile()==null && customerDetails.getIsMobileVerified()==null)
			customerDetails.setIsMobileVerified(false);
		
		ResponseEntity<ResponseDTO<CustomerDetailsDTO>> result=null;
		
		try{
		result=restTemplate.exchange(env.getProperty("rest.host")+"/customer",
				HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseDTO<CustomerDetailsDTO>>() {});
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK && result.getBody().getErrorMessage().equals(""))
			return result.getBody().getResult();
		else
			throw new CustomerDetailsNotFoundException(result.getBody().getErrorMessage());
		
		
	}



	@Override
	public CustomerDetailsDTO InitializeMetaData(
			CustomerDetailsDTO customerDetails) {
		
		if(customerDetails.getInsertedBy()==null)
			customerDetails.setInsertedBy(customerDetails.getUpdatedBy());
		
		if(customerDetails.getUpdatedBy()==null)
			customerDetails.setUpdatedBy(customerDetails.getUpdatedBy());
		
		if(customerDetails.getInsertedById()==null)
			customerDetails.setInsertedById(customerDetails.getUpdatedById());
		
		if(customerDetails.getUpdatedById()==null)
			customerDetails.setUpdatedById(customerDetails.getUpdatedById());
		
		if(customerDetails.getEmail().equals(""))
			customerDetails.setEmail(null);
		
		customerDetails.setUpdateTime(new Date());
			
		if(customerDetails.getInsertTime()==null)
			customerDetails.setInsertTime(new Date());
		
		if(customerDetails.getHomeAddressId()!=null)
		{
			if(customerDetails.getHomeAddressId().getUpdatedBy()==null)
				customerDetails.getHomeAddressId().setUpdatedBy(customerDetails.getUpdatedBy());
			
			if(customerDetails.getHomeAddressId().getInsertedBy()==null)
				customerDetails.getHomeAddressId().setInsertedBy(customerDetails.getUpdatedBy());
			
			if(customerDetails.getHomeAddressId().getUpdatedById()==null)
				customerDetails.getHomeAddressId().setUpdatedById(customerDetails.getUpdatedById());
			
			if(customerDetails.getHomeAddressId().getInsertedById()==null)
				customerDetails.getHomeAddressId().setInsertedById(customerDetails.getUpdatedById());
			
			customerDetails.getHomeAddressId().setUpdateTime(new Date());
			
			if(customerDetails.getHomeAddressId().getInsertTime()==null)
				customerDetails.getHomeAddressId().setInsertTime(new Date());
			
		}
		
		if(customerDetails.getFirmAddressId()!=null)
		{
			if(customerDetails.getFirmAddressId().getUpdatedBy()==null)
				customerDetails.getFirmAddressId().setUpdatedBy(customerDetails.getUpdatedBy());
			
			if(customerDetails.getFirmAddressId().getInsertedBy()==null)
				customerDetails.getFirmAddressId().setInsertedBy(customerDetails.getUpdatedBy());
			
			if(customerDetails.getFirmAddressId().getUpdatedById()==null)
				customerDetails.getFirmAddressId().setUpdatedById(customerDetails.getUpdatedById());
			
			if(customerDetails.getFirmAddressId().getInsertedById()==null)
				customerDetails.getFirmAddressId().setInsertedById(customerDetails.getUpdatedById());
			
			customerDetails.getFirmAddressId().setUpdateTime(new Date());
			
			if(customerDetails.getFirmAddressId().getInsertTime()==null)
				customerDetails.getFirmAddressId().setInsertTime(new Date());
			
			
		}
		
		/*
		if(customerDetails.getEmail()!=null && customerDetails.getIsEmailVerified()==null)
			customerDetails.setIsEmailVerified(false);
		
		if(customerDetails.getMobile()!=null && customerDetails.getIsMobileVerified()==null)
			customerDetails.setIsMobileVerified(false);
		*/
		
		return customerDetails;
	}

	@Override
	public Model initialiseShowCustomerDetails(Long customerId,Model model)
	{
		EmailVerificationDetailsDTO emailVerificationDetails=null;
		
		try{
			emailVerificationDetails=quickRegisterService
					.getEmailVerificationDetailsByCustomerIdTypeAndEmail(customerId,ENTITY_TYPE_CUSTOMER , ENTITY_TYPE_PRIMARY);
		}catch(ResourceNotFoundException e)
		{
			emailVerificationDetails=new EmailVerificationDetailsDTO();
		}
		
		
		MobileVerificationDetailsDTO mobileVerificationDetailsPrimary=null;
		
		try{
			mobileVerificationDetailsPrimary=quickRegisterService
					.getMobileVerificationDetailsByCustomerIdTypeAndMobile(customerId, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		}catch(ResourceNotFoundException e)
		{
			mobileVerificationDetailsPrimary=new MobileVerificationDetailsDTO();
		}
		
		
		MobileVerificationDetailsDTO mobileVerificationDetailsSeconadry=null;
		
		try{
			mobileVerificationDetailsSeconadry=quickRegisterService
					.getMobileVerificationDetailsByCustomerIdTypeAndMobile(customerId, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
			
		}catch(ResourceNotFoundException e)
		{
			mobileVerificationDetailsSeconadry=new MobileVerificationDetailsDTO();
		}
		
		model.addAttribute("emailVerificationDetails", emailVerificationDetails);
		model.addAttribute("mobileVerificationDetailsPrimary", mobileVerificationDetailsPrimary);
		model.addAttribute("mobileVerificationDetailsSeconadry", mobileVerificationDetailsSeconadry);
		
		return model;
	}
	



	@Override
	public Boolean clearTestData() {
		
		Boolean result=restTemplate
				.getForObject(env.getProperty("rest.host")+"/customer/clearTestData", Boolean.class);
		
		return result;
		
		
	}

	@Override
	public Integer count() {

		Integer count=restTemplate
				.getForObject(env.getProperty("rest.host")+"/customer/count", Integer.class);
		
		return count;
		
	}

	@Override
	public CustomerDetailsDTO getCustomerDetailsById(Long customerId) {

		ResponseEntity<CustomerDetailsDTO> responseEntity=restTemplate.exchange(env.getProperty("rest.host")+"/customer/getCustomerDetailsById/"+customerId,
					HttpMethod.GET, null, CustomerDetailsDTO.class);
		
		if(responseEntity.getStatusCode()==HttpStatus.FOUND)
			return responseEntity.getBody();
		else
			throw new CustomerDetailsNotFoundException();

	}

	@Override
	public String awsTest() {

		String result=restTemplate.getForObject(env.getProperty("rest.host")+"/test/aws", String.class);
		
		return result;
	}
}
