package com.projectx.mvc.controller.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.completeregister.ResponseDTO;
import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.request.FreightRequestByCustomerService;
import com.projectx.mvc.services.request.FreightRequestByVendorService;
import com.projectx.rest.domain.ang.FreightRequestByCustomerAngDTO;
import com.projectx.rest.domain.ang.FreightRequestByVendorAngDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;


@RestController
@RequestMapping(value = "/request/freightrequestByCustomer")
public class FreightRequestByCustomerController {

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<List<FreightRequestByVendorAngDTO>> save(@Valid @RequestBody FreightRequestByCustomerAngDTO freightRequestByCustomerDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		freightRequestByCustomerDTO.setStatus("New");
		
		FreightRequestByCustomer freightRequestByCustomer=new FreightRequestByCustomer(freightRequestByCustomerDTO.getRequestId(),
				freightRequestByCustomerDTO.getSource(),freightRequestByCustomerDTO.getDestination(), freightRequestByCustomerDTO.getPickupDate(),
				freightRequestByCustomerDTO.getNoOfVehicles(), freightRequestByCustomerDTO.getLoadType(), freightRequestByCustomerDTO.getCapacity(),
				freightRequestByCustomerDTO.getBodyType(),freightRequestByCustomerDTO.getGrossWeight(), freightRequestByCustomerDTO.getLength(),
				freightRequestByCustomerDTO.getWidth(), freightRequestByCustomerDTO.getHeight(), freightRequestByCustomerDTO.getVehicleBrand(), 
				freightRequestByCustomerDTO.getModel(), freightRequestByCustomerDTO.getCommodity(), freightRequestByCustomerDTO.getPickupTime(),
				freightRequestByCustomerDTO.getCustomerId(), freightRequestByCustomerDTO.getStatus(),null, new Date(), freightRequestByCustomerDTO.getUpdatedBy(),
				freightRequestByCustomerDTO.getUpdateTime());
		
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(freightRequestByCustomer);
		
		if(savedEntity.getRequestId()!=null)
		{	
		
			List<FreightRequestByVendorDTO> requestList=freightRequestByVendorService
					.getMatchingVendorReqForCustReq(savedEntity.toFreightRequestByCustomerDTO());
			
			List<FreightRequestByVendorAngDTO> angList=new ArrayList<FreightRequestByVendorAngDTO>();
			
			for(int i=0;i<angList.size();i++)
			{
				FreightRequestByVendorDTO freightRequestByVendorDTO=requestList.get(i);
				
				FreightRequestByVendorAngDTO freightRequestByVendor=new FreightRequestByVendorAngDTO(freightRequestByVendorDTO.getRequestId(),freightRequestByVendorDTO.getVehicleRegistrationNumber(),
						freightRequestByVendorDTO.getSource(), freightRequestByVendorDTO.getDestination(), freightRequestByVendorDTO.getDriverId(),
						freightRequestByVendorDTO.getAvailableDate(), freightRequestByVendorDTO.getAvailableTime(),freightRequestByVendorDTO.getPickupRangeInKm(),
						freightRequestByVendorDTO.getVendorId(), freightRequestByVendorDTO.getStatus(), freightRequestByVendorDTO.getReservedBy(),
						freightRequestByVendorDTO.getInsertTime(), freightRequestByVendorDTO.getUpdateTime(), freightRequestByVendorDTO.getUpdatedBy());
				
				angList.add(freightRequestByVendor);
				
			}
			
		
			return new ResponseEntity<List<FreightRequestByVendorAngDTO>>(angList, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<FreightRequestByCustomerAngDTO> getById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			FreightRequestByCustomer fetchedEntity=freightRequestByCustomerService.getRequestById(entityIdDTO.getEntityId());
			
			FreightRequestByCustomerAngDTO angDTO=new FreightRequestByCustomerAngDTO(fetchedEntity.getRequestId(), fetchedEntity.getSource(),
					fetchedEntity.getDestination(),fetchedEntity.getPickupDate(), fetchedEntity.getNoOfVehicles(), fetchedEntity.getLoadType(),
					fetchedEntity.getCapacity(), fetchedEntity.getBodyType(),fetchedEntity.getGrossWeight(), fetchedEntity.getLength(),
					fetchedEntity.getWidth(),fetchedEntity.getHeight(), fetchedEntity.getVehicleBrand(), fetchedEntity.getModel(),
					fetchedEntity.getCommodity(), fetchedEntity.getPickupTime(), fetchedEntity.getCustomerId(), fetchedEntity.getStatus(),
					fetchedEntity.getAllocatedFor(),fetchedEntity.getInsertTime(),fetchedEntity.getUpdatedBy(), fetchedEntity.getPickupDate());
			
			return new ResponseEntity<FreightRequestByCustomerAngDTO>(angDTO, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
		
	}
	
	@RequestMapping(value="/getByCustomerId",method=RequestMethod.POST)
	public List<FreightRequestByCustomerAngDTO> showRequestForCustomer(@RequestBody EntityIdDTO entityIdDTO ,Model model)
	{
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerService.getAllRequestForCustomer(entityIdDTO.getEntityId());
		
		List<FreightRequestByCustomerAngDTO> angList=new ArrayList<FreightRequestByCustomerAngDTO>();
		
		for(int i=0;i<angList.size();i++)
		{
			FreightRequestByCustomer fetchedEntity=requestList.get(i);
			
			FreightRequestByCustomerAngDTO angDTO=new FreightRequestByCustomerAngDTO(fetchedEntity.getRequestId(), fetchedEntity.getSource(),
					fetchedEntity.getDestination(),fetchedEntity.getPickupDate(), fetchedEntity.getNoOfVehicles(), fetchedEntity.getLoadType(),
					fetchedEntity.getCapacity(), fetchedEntity.getBodyType(),fetchedEntity.getGrossWeight(), fetchedEntity.getLength(),
					fetchedEntity.getWidth(),fetchedEntity.getHeight(), fetchedEntity.getVehicleBrand(), fetchedEntity.getModel(),
					fetchedEntity.getCommodity(), fetchedEntity.getPickupTime(), fetchedEntity.getCustomerId(), fetchedEntity.getStatus(),
					fetchedEntity.getAllocatedFor(),fetchedEntity.getInsertTime(),fetchedEntity.getUpdatedBy(), fetchedEntity.getPickupDate());
			
			angList.add(angDTO);
		}
		
		return angList;
	}
	
	@RequestMapping(value="/deleteRequestById",method=RequestMethod.POST)
	public ResponseDTO deleteRequestById(@RequestBody EntityIdTypeDTO entityIdDTO)
	{
		Boolean status=freightRequestByCustomerService.deleteRequestById(entityIdDTO.getEntityId());
		
		if(status)
			return new ResponseDTO("sucess", "");
		else
			return new ResponseDTO("failure", "Error");
	}
}
