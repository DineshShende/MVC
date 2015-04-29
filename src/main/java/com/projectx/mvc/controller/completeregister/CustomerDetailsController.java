package com.projectx.mvc.controller.completeregister;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.completeregister.ResponseDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.mvc.util.validator.CustomerDetailsValidator;
import com.projectx.rest.domain.ang.CustomerDetailsDTOAng;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;

@RestController
@RequestMapping(value = "/customer")
public class CustomerDetailsController {

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	CustomerDetailsValidator customerDetailsValidator;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@InitBinder("customerDetailsDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(customerDetailsValidator);
    }
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	private Integer ENTITY_TYPE_VENDOR=2;
		
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ENTITY_TYPE_SECONDARY=2;
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> save( @Valid @RequestBody CustomerDetailsDTOAng customerDetailsDTO,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<ResponseDTO>(new ResponseDTO("failure", "validation failed"),HttpStatus.NOT_ACCEPTABLE);
		}	
		
		System.out.println(customerDetailsDTO);
		
		customerDetailsDTO.getHomeAddressId().setCustomerType(ENTITY_TYPE_CUSTOMER);
		customerDetailsDTO.getFirmAddressId().setCustomerType(ENTITY_TYPE_CUSTOMER);
		
		
		if(customerDetailsDTO.getEntityType().equals(ENTITY_TYPE_CUSTOMER))
		{
		CustomerDetailsDTO detailsDTO=new CustomerDetailsDTO(customerDetailsDTO.getCustomerId(),
				customerDetailsDTO.getFirstName(),customerDetailsDTO.getMiddleName(), customerDetailsDTO.getLastName(),
				customerDetailsDTO.getDateOfBirth(), customerDetailsDTO.getHomeAddressId(),customerDetailsDTO.getMobile(),
				customerDetailsDTO.getPhoneNumber(), customerDetailsDTO.getIsMobileVerified(),customerDetailsDTO.getEmail(),
				customerDetailsDTO.getIsEmailVerified(),customerDetailsDTO.getLanguage(), customerDetailsDTO.getBusinessDomain(),
				customerDetailsDTO.getNameOfFirm(), customerDetailsDTO.getFirmAddressId(), customerDetailsDTO.getSecondaryMobile(), 
				false, customerDetailsDTO.getSecondaryEmail(), new Date(), new Date(), customerDetailsDTO.getRequestedBy(),
				customerDetailsDTO.getRequestedBy(),customerDetailsDTO.getRequestedById(),customerDetailsDTO.getRequestedById());
		
		detailsDTO=customerDetailsService.InitializeMetaData(detailsDTO);
		
		CustomerDetailsDTO newCustomerDetailsDTO=customerDetailsService
				.merge(detailsDTO);
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("sucess", ""), HttpStatus.OK);
		}
		else if(customerDetailsDTO.getEntityType().equals(ENTITY_TYPE_VENDOR))
		{
			VendorDetailsDTO vendorDetails=new VendorDetailsDTO(customerDetailsDTO.getCustomerId(), customerDetailsDTO.getFirstName(), customerDetailsDTO.getMiddleName(),
					customerDetailsDTO.getLastName(),customerDetailsDTO.getDateOfBirth(), customerDetailsDTO.getNameOfFirm(), 
					customerDetailsDTO.getFirmAddressId(), customerDetailsDTO.getHomeAddressId(), customerDetailsDTO.getMobile(), customerDetailsDTO.getPhoneNumber(),
					customerDetailsDTO.getIsMobileVerified(), customerDetailsDTO.getEmail(), customerDetailsDTO.getIsEmailVerified(), customerDetailsDTO.getLanguage(),
					customerDetailsDTO.getSecondaryMobile(),customerDetailsDTO.getIsSecondaryMobileVerified(), new Date(),new Date(), 
					customerDetailsDTO.getRequestedBy(),customerDetailsDTO.getRequestedBy(),customerDetailsDTO.getRequestedById(),
					customerDetailsDTO.getRequestedById());
			
			if(result.hasErrors())
			{
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
			
			vendorDetails=vendorDetailsService.intializeMetaData(vendorDetails);
		
			VendorDetailsDTO newVendorDetailsDTO=vendorDetailsService
					.update(vendorDetails);
			
			return new ResponseEntity<ResponseDTO>(new ResponseDTO("sucess", ""), HttpStatus.OK);
		
			
		}
		else 
		{
			return new ResponseEntity<ResponseDTO>(new ResponseDTO("failure", "Invalid Entity Type"), HttpStatus.OK);
		}
			
	
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<CustomerDetailsDTOAng> getCustomerDetailsByIdAndType(@RequestBody EntityIdTypeDTO customerIdDTO)
	{
		
		ResponseEntity<CustomerDetailsDTOAng> result=null;
		
		if(customerIdDTO.getEntityType().equals(ENTITY_TYPE_CUSTOMER))
		{
				
			try{
				CustomerDetailsDTO customerDetailsDTO=customerDetailsService.getCustomerDetailsById(customerIdDTO.getEntityId());
				
				CustomerDetailsDTOAng customerDetailsDTOAng=new CustomerDetailsDTOAng
						(customerDetailsDTO.getCustomerId(), customerDetailsDTO.getFirstName(), customerDetailsDTO.getMiddleName(),
								customerDetailsDTO.getLastName(), customerDetailsDTO.getDateOfBirth(),customerDetailsDTO.getHomeAddressId(),
								customerDetailsDTO.getMobile(), customerDetailsDTO.getPhoneNumber(),customerDetailsDTO.getIsMobileVerified(),
								customerDetailsDTO.getEmail(), customerDetailsDTO.getIsEmailVerified(), customerDetailsDTO.getLanguage(), 
								customerDetailsDTO.getBusinessDomain(), customerDetailsDTO.getNameOfFirm(), customerDetailsDTO.getFirmAddressId(),
								customerDetailsDTO.getSecondaryMobile(), customerDetailsDTO.getIsSecondaryMobileVerified(), 
								customerDetailsDTO.getSecondaryEmail(),ENTITY_TYPE_CUSTOMER, customerDetailsDTO.getInsertTime(), customerDetailsDTO.getUpdateTime(), 
								customerDetailsDTO.getUpdatedBy(),customerDetailsDTO.getUpdatedById());
				
				return new ResponseEntity<CustomerDetailsDTOAng>(customerDetailsDTOAng, HttpStatus.OK);
			}catch(ResourceNotFoundException e)
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		else if(customerIdDTO.getEntityType().equals(ENTITY_TYPE_VENDOR))
		{
			try{
				VendorDetailsDTO fetchedEntity=vendorDetailsService.getCustomerDetailsById(customerIdDTO.getEntityId());
				
				CustomerDetailsDTOAng ang=new CustomerDetailsDTOAng(fetchedEntity.getVendorId(), fetchedEntity.getFirstName(), fetchedEntity.getMiddleName(),
						fetchedEntity.getLastName(), fetchedEntity.getDateOfBirth(), fetchedEntity.getHomeAddress(), 
						fetchedEntity.getMobile(), fetchedEntity.getPhoneNumber(), fetchedEntity.getIsMobileVerified(), 
						fetchedEntity.getEmail(), fetchedEntity.getIsEmailVerified(), fetchedEntity.getLaguage(), 
						null, fetchedEntity.getFirmName(), fetchedEntity.getFirmAddress(), 
						fetchedEntity.getSecondaryMobile(), fetchedEntity.getIsSecondaryMobileVerified(),null,ENTITY_TYPE_VENDOR, 
						fetchedEntity.getInsertTime(),fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy(),fetchedEntity.getUpdatedById());
				
				return new ResponseEntity<CustomerDetailsDTOAng>(ang, HttpStatus.OK);
			}catch(ResourceNotFoundException e)
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
		
}

