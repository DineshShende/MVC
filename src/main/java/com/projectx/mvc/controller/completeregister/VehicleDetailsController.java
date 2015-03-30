package com.projectx.mvc.controller.completeregister;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleDetailsController {
	
	@Autowired
	VendorDetailsService vendorDetailsService; 

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<VehicleDetailsDTO> addVehicle(@Valid @ModelAttribute VehicleDetailsDTO vehicleDetailsDTO,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		VehicleDetailsDTO detailsDTO=vendorDetailsService.save(vehicleDetailsDTO);
		
		return new ResponseEntity<VehicleDetailsDTO>(detailsDTO, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getByVendorId",method=RequestMethod.POST)
	public List<VehicleDetailsDTO> showVehicleDetails(@RequestBody EntityIdDTO entityIdDTO,Model model)
	{
		List<VehicleDetailsDTO> vehicleList=vendorDetailsService.getVehiclesByvendor(entityIdDTO.getEntityId());
		
				
		return vehicleList;	
		
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<VehicleDetailsDTO> getVehicleDetailsById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			VehicleDetailsDTO fetchedEntity=vendorDetailsService.getVehicleById(entityIdDTO.getEntityId());
			return new ResponseEntity<VehicleDetailsDTO>(fetchedEntity, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
	}

	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public Boolean  deleteVehicle(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		Boolean detailsDTO=vendorDetailsService.deleteVehicleById(entityIdDTO.getEntityId());
		
		return detailsDTO;
	}

	
}
