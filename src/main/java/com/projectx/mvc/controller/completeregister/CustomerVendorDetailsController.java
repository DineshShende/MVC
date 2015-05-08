package com.projectx.mvc.controller.completeregister;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.commn.ResponseDTO;
import com.projectx.mvc.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.VendorDetailsTransactionalUpdateFailedException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.mvc.util.validator.CustomerDetailsValidator;
import com.projectx.rest.domain.ang.CustomerDetailsDTOAng;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;

@RestController
@RequestMapping(value = "/entity")
public class CustomerVendorDetailsController {

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	CustomerDetailsValidator customerDetailsValidator;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Value("${ENTITY_TYPE_INVALID}")
	private String ENTITY_TYPE_INVALID;
	
	@Value("${CUSTOMER_DETAILS_NOT_FOUND_BY_ID}")
	private String CUSTOMER_DETAILS_NOT_FOUND_BY_ID;
	
	@Value("${VENDOR_DETAILS_NOT_FOUND_BY_ID}")
	private String VENDOR_DETAILS_NOT_FOUND_BY_ID;
	
	@InitBinder("customerDetailsDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(customerDetailsValidator);
    }
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	private Integer ENTITY_TYPE_VENDOR=2;
		
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ENTITY_TYPE_SECONDARY=2;
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> save( @Valid @RequestBody CustomerDetailsDTOAng customerDetailsDTO,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", "validation failed"),HttpStatus.NOT_ACCEPTABLE);
		}	
		
		customerDetailsDTO.getHomeAddressId().setCustomerType(ENTITY_TYPE_CUSTOMER);
		customerDetailsDTO.getFirmAddressId().setCustomerType(ENTITY_TYPE_CUSTOMER);
		
		
		if(customerDetailsDTO.getEntityType().equals(ENTITY_TYPE_CUSTOMER))
		{
			CustomerDetailsDTO detailsDTO=customerDetailsDTO.toCustomerDetailsDTO();
					
			detailsDTO=customerDetailsService.InitializeMetaData(detailsDTO);
			
			try{
				CustomerDetailsDTO newCustomerDetailsDTO=customerDetailsService
						.merge(detailsDTO);
				
				return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
			}catch(CustomerDetailsNotFoundException e)
			{
				return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()), HttpStatus.OK);
			}
			
		}
		else if(customerDetailsDTO.getEntityType().equals(ENTITY_TYPE_VENDOR))
		{
			VendorDetailsDTO vendorDetails=customerDetailsDTO.toVendorDetailsDTO();
			
			if(result.hasErrors())
			{
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
			
			vendorDetails=vendorDetailsService.intializeMetaData(vendorDetails);
		
			try{
				VendorDetailsDTO newVendorDetailsDTO=vendorDetailsService
						.update(vendorDetails);
				
				return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
			}catch(VendorDetailsTransactionalUpdateFailedException e)
			{
				return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()), HttpStatus.OK);
			}
			
		
			
		}
		else 
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", ENTITY_TYPE_INVALID), HttpStatus.OK);
		}
			
	
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<CustomerDetailsDTOAng>> getCustomerDetailsByIdAndType(@RequestBody EntityIdTypeDTO customerIdDTO)
	{
		
		ResponseEntity<ResponseDTO<CustomerDetailsDTOAng>> result=null;
		
		if(customerIdDTO.getEntityType().equals(ENTITY_TYPE_CUSTOMER))
		{
				
			try{
				CustomerDetailsDTO customerDetailsDTO=customerDetailsService.getCustomerDetailsById(customerIdDTO.getEntityId());
				
				CustomerDetailsDTOAng customerDetailsDTOAng=CustomerDetailsDTOAng.fromCustomerDetailsDTO(customerDetailsDTO);
						
				return new ResponseEntity<ResponseDTO<CustomerDetailsDTOAng>>(new ResponseDTO<CustomerDetailsDTOAng>(customerDetailsDTOAng,"sucess"), HttpStatus.OK);
			}catch(ResourceNotFoundException e)
			{
				return new ResponseEntity<ResponseDTO<CustomerDetailsDTOAng>>(new ResponseDTO<CustomerDetailsDTOAng>(null,CUSTOMER_DETAILS_NOT_FOUND_BY_ID), HttpStatus.OK);
			}
		}
		else if(customerIdDTO.getEntityType().equals(ENTITY_TYPE_VENDOR))
		{
			try{
				VendorDetailsDTO fetchedEntity=vendorDetailsService.getCustomerDetailsById(customerIdDTO.getEntityId());
				
				CustomerDetailsDTOAng ang=CustomerDetailsDTOAng.fromVendorDetailsDTO(fetchedEntity);
						
				return new ResponseEntity<ResponseDTO<CustomerDetailsDTOAng>>(new ResponseDTO<CustomerDetailsDTOAng>(ang,"sucess"), HttpStatus.OK);
			}catch(ResourceNotFoundException e)
			{
				return new ResponseEntity<ResponseDTO<CustomerDetailsDTOAng>>(new ResponseDTO<CustomerDetailsDTOAng>(null,VENDOR_DETAILS_NOT_FOUND_BY_ID), HttpStatus.OK);
			}
		}
		else
		{
			return new ResponseEntity<ResponseDTO<CustomerDetailsDTOAng>>(new ResponseDTO<CustomerDetailsDTOAng>(null,ENTITY_TYPE_INVALID), HttpStatus.OK);
		}
	}
	
		
}

