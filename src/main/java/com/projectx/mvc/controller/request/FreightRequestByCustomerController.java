package com.projectx.mvc.controller.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.commn.ResponseDTO;
import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.handshake.DealService;
import com.projectx.mvc.services.request.FreightRequestByCustomerService;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.ang.FreightRequestByCustomerAngDTO;
import com.projectx.rest.domain.ang.FreightRequestByVendorAngDTO;
import com.projectx.rest.domain.ang.FreightRequestByVendorCustomAngDTO;
import com.projectx.rest.domain.ang.VehicleDetailsCustomAngDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.handshake.DealInfoAndVendorContactDetailsDTO;
import com.projectx.rest.domain.handshake.TriggerDealDTO;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;


@RestController
@RequestMapping(value = "/request/freightrequestByCustomer")
public class FreightRequestByCustomerController {

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	DealService dealService;
	
	@Value("${FREIGHTALLOCATIONSTATUS_NEW}")
	private String FREIGHTALLOCATIONSTATUS_NEW;
	
	@Value("${FREIGHT_REQUEST_BY_CUSTOMER_NOT_FOUND_BY_ID}")
	private String FREIGHT_REQUEST_BY_CUSTOMER_NOT_FOUND_BY_ID;
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<EntityIdDTO>> save(@Valid @RequestBody FreightRequestByCustomerAngDTO freightRequestByCustomerDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		freightRequestByCustomerDTO.setStatus(FREIGHTALLOCATIONSTATUS_NEW);
		
		FreightRequestByCustomer freightRequestByCustomer=freightRequestByCustomerDTO.toFreightRequestByCustomer();
			
		try{
		
			FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(freightRequestByCustomer);
			
			return new ResponseEntity<ResponseDTO<EntityIdDTO>>(new ResponseDTO<EntityIdDTO>(new EntityIdDTO(savedEntity.getRequestId()),""), HttpStatus.OK);
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<EntityIdDTO>>(new ResponseDTO<EntityIdDTO>(new EntityIdDTO(null),e.getMessage()), HttpStatus.OK);
		}
		
		
	}
	
	
	@RequestMapping(value="/getMatchingVendorRequestsByCustomerRequestId",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<List<FreightRequestByVendorCustomAngDTO>>> getMatchingVendorRequestsByCustomerRequestId(@Valid @RequestBody EntityIdDTO entityIdDTO,BindingResult bindingResult)
	{
		List<FreightRequestByVendorCustomAngDTO> angList=new ArrayList<FreightRequestByVendorCustomAngDTO>();
		
		FreightRequestByCustomer freightRequestByCustomer=null;
		
		try{
			freightRequestByCustomer=freightRequestByCustomerService.getRequestById(entityIdDTO.getEntityId());
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<List<FreightRequestByVendorCustomAngDTO>>>(
					new ResponseDTO<List<FreightRequestByVendorCustomAngDTO>>(null,FREIGHT_REQUEST_BY_CUSTOMER_NOT_FOUND_BY_ID), HttpStatus.OK);
			
		}
		
		
		List<FreightRequestByVendor> requestList=freightRequestByVendorService
				.getMatchingVendorReqForCustReq(freightRequestByCustomer.toFreightRequestByCustomerDTO());
		
		
		for(int i=0;i<requestList.size();i++)
		{
			FreightRequestByVendor freightRequestByVendorDTO=requestList.get(i);
			
			FreightRequestByVendorCustomAngDTO freightRequestByVendor=
					FreightRequestByVendorCustomAngDTO.fromFreightRequestByVendor(freightRequestByVendorDTO);
			
			angList.add(freightRequestByVendor);
		}
		
		return new ResponseEntity<ResponseDTO<List<FreightRequestByVendorCustomAngDTO>>>(
				new ResponseDTO<List<FreightRequestByVendorCustomAngDTO>>(angList,""), HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<FreightRequestByCustomerAngDTO>> getById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			FreightRequestByCustomer fetchedEntity=freightRequestByCustomerService.getRequestById(entityIdDTO.getEntityId());
			
			FreightRequestByCustomerAngDTO angDTO=FreightRequestByCustomerAngDTO.fromFreightRequestByCustomer(fetchedEntity);
					
			return new ResponseEntity<ResponseDTO<FreightRequestByCustomerAngDTO>>
					(new ResponseDTO<FreightRequestByCustomerAngDTO>(angDTO,""), HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<FreightRequestByCustomerAngDTO>>
			(new ResponseDTO<FreightRequestByCustomerAngDTO>(null,FREIGHT_REQUEST_BY_CUSTOMER_NOT_FOUND_BY_ID), HttpStatus.OK);
		}
		
		
		
	}
	
	@RequestMapping(value="/getByCustomerId",method=RequestMethod.POST)
	public List<FreightRequestByCustomerAngDTO> showRequestForCustomer(@RequestBody EntityIdDTO entityIdDTO ,Model model)
	{
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerService.getAllRequestForCustomer(entityIdDTO.getEntityId());
		
		List<FreightRequestByCustomerAngDTO> angList=new ArrayList<FreightRequestByCustomerAngDTO>();
		
		for(int i=0;i<requestList.size();i++)
		{
			FreightRequestByCustomer fetchedEntity=requestList.get(i);
			
			FreightRequestByCustomerAngDTO angDTO=FreightRequestByCustomerAngDTO.fromFreightRequestByCustomer(fetchedEntity);
					
			angList.add(angDTO);
		}
		
		return angList;
	}
	
	@RequestMapping(value="/triggerdeal",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<DealInfoAndVendorContactDetailsDTO>> triggerDeal(@RequestBody TriggerDealDTO triggerDealDTO)
	{
		DealInfoAndVendorContactDetailsDTO contactDetailsDTO=dealService.triggerDeal(triggerDealDTO);
		
		return new ResponseEntity<ResponseDTO<DealInfoAndVendorContactDetailsDTO>>
		(new ResponseDTO<DealInfoAndVendorContactDetailsDTO>(contactDetailsDTO,""),HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteRequestById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> deleteRequestById(@RequestBody EntityIdTypeDTO entityIdDTO)
	{
		try{
			Boolean status=freightRequestByCustomerService.deleteRequestById(entityIdDTO.getEntityId());
		
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""),HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()),HttpStatus.OK);
		}
		
			
	}
}
