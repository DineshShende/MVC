package com.projectx.mvc.controller.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.completeregister.ResponseDTO;
import com.projectx.mvc.services.request.FreightRequestByCustomerService;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.ang.FreightRequestByVendorAngDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

@RestController
@RequestMapping(value = "/request/freightrequestByVendor")
public class FreightRequestByVendorController {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> save(@Valid @RequestBody FreightRequestByVendorAngDTO freightRequestByVendorDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<ResponseDTO>(new ResponseDTO("failure", "Error"),HttpStatus.NOT_ACCEPTABLE);
		
		freightRequestByVendorDTO.setStatus("New");
		
		FreightRequestByVendorDTO requestByVendorDTO=new FreightRequestByVendorDTO(freightRequestByVendorDTO.getVehicleRegistrationNumber(),
				freightRequestByVendorDTO.getSource(),freightRequestByVendorDTO.getDestination(),freightRequestByVendorDTO.getDriverId(),
				freightRequestByVendorDTO.getAvailableDate(), freightRequestByVendorDTO.getAvailableTime(), freightRequestByVendorDTO.getPickupRangeInKm(),
				freightRequestByVendorDTO.getVendorId(), freightRequestByVendorDTO.getStatus(), freightRequestByVendorDTO.getReservedBy(), 
				new Date(), new Date(), freightRequestByVendorDTO.getUpdatedBy());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(requestByVendorDTO);
		
		if(savedEntity.getRequestId()!=null)
		{
			
			return new ResponseEntity<ResponseDTO>(new ResponseDTO("sucess", ""), HttpStatus.OK);
			
		}
		else
			return new ResponseEntity<ResponseDTO>(new ResponseDTO("failure", "Error"),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public FreightRequestByVendorAngDTO getById(@RequestBody EntityIdTypeDTO entityIdDTO)
	{
		
		
		FreightRequestByVendorDTO fetchedEntity=freightRequestByVendorService.getRequestById(entityIdDTO.getEntityId());
		
		FreightRequestByVendorAngDTO byVendorAngDTO=null;
		
		if(fetchedEntity.getRequestId()!=null)
		{
			byVendorAngDTO=new FreightRequestByVendorAngDTO(fetchedEntity.getRequestId(),
					fetchedEntity.getVehicleRegistrationNumber(),fetchedEntity.getSource(), fetchedEntity.getDestination(),
					fetchedEntity.getDriverId(), fetchedEntity.getAvailableDate(),fetchedEntity.getAvailableTime(), fetchedEntity.getPickupRangeInKm(),
					fetchedEntity.getVendorId(), fetchedEntity.getStatus(),fetchedEntity.getReservedBy(), fetchedEntity.getInsertTime(),
					fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy());
		}
		
		return byVendorAngDTO;
	}
	
	@RequestMapping(value="/getByVendorId",method=RequestMethod.POST)
	public List<FreightRequestByVendorAngDTO> showRequestForCustomer(@RequestBody EntityIdDTO entityIdDTO ,Model model)
	{
		List<FreightRequestByVendorDTO> requestList=freightRequestByVendorService.getAllRequestForVendor(entityIdDTO.getEntityId());
		
		List<FreightRequestByVendorAngDTO> angList=new ArrayList<FreightRequestByVendorAngDTO>();
		
		for(int i=0;i<requestList.size();i++)
		{
			FreightRequestByVendorDTO fetchedEntity=requestList.get(i);
			
			FreightRequestByVendorAngDTO byVendorAngDTO=new FreightRequestByVendorAngDTO(fetchedEntity.getRequestId(),
					fetchedEntity.getVehicleRegistrationNumber(),fetchedEntity.getSource(), fetchedEntity.getDestination(),
					fetchedEntity.getDriverId(), fetchedEntity.getAvailableDate(),fetchedEntity.getAvailableTime(), fetchedEntity.getPickupRangeInKm(),
					fetchedEntity.getVendorId(), fetchedEntity.getStatus(),fetchedEntity.getReservedBy(), fetchedEntity.getInsertTime(),
					fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy());
			
			angList.add(byVendorAngDTO);
			
		}
		
		
		return angList;
	}
	
	@RequestMapping(value="/deleteRequestById",method=RequestMethod.POST)
	public ResponseDTO deleteRequestById(@RequestBody EntityIdDTO entityIdDTO)
	{
		Boolean status=freightRequestByVendorService.deleteRequestById(entityIdDTO.getEntityId());
		
		if(status)
			return new ResponseDTO("sucess", "");
		else
			return new ResponseDTO("failure", "error");
		
		
	}
	
}

