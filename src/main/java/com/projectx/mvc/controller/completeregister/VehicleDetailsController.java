package com.projectx.mvc.controller.completeregister;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.commn.ResponseDTO;
import com.projectx.mvc.domain.completeregister.L1VehicleCompleteRegistration;
import com.projectx.mvc.domain.completeregister.VehicleSimplified;
import com.projectx.mvc.exception.repository.completeregister.ResourceAlreadyPresentException;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.rest.domain.ang.VehicleDetailsAngDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleDetailsController {
	
	@Autowired
	VendorDetailsService vendorDetailsService; 
	
	@Value("${VEHICLE_DETAILS_NOT_FOUND_BY_ID}")
	private String VEHICLE_DETAILS_NOT_FOUND_BY_ID;

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> addVehicle(@Valid @RequestBody VehicleDetailsAngDTO vehicleDTO,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		VehicleDetailsDTO vehicleDetails=vehicleDTO.toVehicleDetailsDTO();
						
		try{
			VehicleDetailsDTO detailsDTO=vendorDetailsService.save(vehicleDetails);
		
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/save/simplified",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> addVehicleSimplified(@Valid @RequestBody VehicleSimplified vehicleSimplified,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		try{
			VehicleDetailsDTO detailsDTO=vendorDetailsService.saveSimplified(vehicleSimplified);
		
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/save/l1dataentry",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> l1DataEntry(@Valid @RequestBody L1VehicleCompleteRegistration l1VehicleCompleteRegistration,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		try{
			VehicleDetailsDTO detailsDTO=vendorDetailsService.l1VehicleDataEntry(l1VehicleCompleteRegistration);
		
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
		}catch(ResourceAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()), HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getByVendorId",method=RequestMethod.POST)
	public List<VehicleDetailsAngDTO> showVehicleDetails(@RequestBody EntityIdDTO entityIdDTO,Model model)
	{
		List<VehicleDetailsAngDTO> angList=new ArrayList<VehicleDetailsAngDTO>();
		
		List<VehicleDetailsDTO> vehicleList=vendorDetailsService.getVehiclesByvendor(entityIdDTO.getEntityId());
		
		for(int i=0;i<vehicleList.size();i++)
		{
			VehicleDetailsDTO fetchedEntity=vehicleList.get(i);
			
			VehicleDetailsAngDTO angDTO=VehicleDetailsAngDTO.fromVehicleDetailsDTO(fetchedEntity);
							
			angList.add(angDTO);
			
		}
		
				
		return angList;	
		
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleDetailsAngDTO>> getVehicleDetailsById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			VehicleDetailsDTO fetchedEntity=vendorDetailsService.getVehicleById(entityIdDTO.getEntityId());
			
			VehicleDetailsAngDTO angDTO=VehicleDetailsAngDTO.fromVehicleDetailsDTO(fetchedEntity);
							
			return new ResponseEntity<ResponseDTO<VehicleDetailsAngDTO>>(new ResponseDTO<VehicleDetailsAngDTO>(angDTO,"sucess"), HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleDetailsAngDTO>>(new ResponseDTO<VehicleDetailsAngDTO>(null,VEHICLE_DETAILS_NOT_FOUND_BY_ID), HttpStatus.OK);
		}
		
		
	}

	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>>  deleteVehicle(@RequestBody EntityIdDTO entityIdDTO,Model model)
	{
		Boolean detailsDTO=vendorDetailsService.deleteVehicleById(entityIdDTO.getEntityId());
		
		if(detailsDTO)
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
		
		else
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", VEHICLE_DETAILS_NOT_FOUND_BY_ID), HttpStatus.OK);
	}

	
}
