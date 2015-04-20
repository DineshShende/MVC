package com.projectx.mvc.controller.completeregister;

import java.util.ArrayList;
import java.util.Date;
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

import com.projectx.mvc.domain.completeregister.ResponseDTO;
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

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> addVehicle(@Valid @RequestBody VehicleDetailsAngDTO vehicleDTO,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		VehicleDetailsDTO vehicleDetails=new VehicleDetailsDTO(vehicleDTO.getVehicleId(), vehicleDTO.getOwnerFirstName(),vehicleDTO.getOwnerMiddleName(),
				vehicleDTO.getOwnerLastName(), vehicleDTO.getVehicleBrandId(), vehicleDTO.getVehicleBodyType(), vehicleDTO.getIsBodyTypeFlexible(),
				vehicleDTO.getRegistrationNumber(), vehicleDTO.getChassisNumber(), vehicleDTO.getLoadCapacityInTons(), vehicleDTO.getLength(),
				vehicleDTO.getWidth(), vehicleDTO.getHeight(), vehicleDTO.getNumberOfWheels(), vehicleDTO.getPermitType(), vehicleDTO.getInsuranceStatus(),
				vehicleDTO.getInsuranceNumber(),vehicleDTO.getInsuranceCompany(), vehicleDTO.getVendorId(), vehicleDTO.getCommodityList(),
				new Date(), new Date(), vehicleDTO.getRequestedBy(),vehicleDTO.getRequestedBy(),
				vehicleDTO.getRequestedById(),vehicleDTO.getRequestedById());
		
		VehicleDetailsDTO detailsDTO=vendorDetailsService.save(vehicleDetails);
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("sucess", ""), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/getByVendorId",method=RequestMethod.POST)
	public List<VehicleDetailsAngDTO> showVehicleDetails(@RequestBody EntityIdDTO entityIdDTO,Model model)
	{
		List<VehicleDetailsAngDTO> angList=new ArrayList<VehicleDetailsAngDTO>();
		
		List<VehicleDetailsDTO> vehicleList=vendorDetailsService.getVehiclesByvendor(entityIdDTO.getEntityId());
		
		for(int i=0;i<vehicleList.size();i++)
		{
			VehicleDetailsDTO fetchedEntity=vehicleList.get(i);
			
			VehicleDetailsAngDTO angDTO=new VehicleDetailsAngDTO(fetchedEntity.getVehicleId(), fetchedEntity.getOwnerFirstName(),fetchedEntity.getOwnerMiddleName(),
					fetchedEntity.getOwnerLastName(), fetchedEntity.getVehicleBrandId(), fetchedEntity.getVehicleBodyType(), fetchedEntity.getIsBodyTypeFlexible(),
					fetchedEntity.getRegistrationNumber(), fetchedEntity.getChassisNumber(), fetchedEntity.getLoadCapacityInTons(),fetchedEntity.getLength(), 
					fetchedEntity.getWidth(), fetchedEntity.getHeight(), fetchedEntity.getNumberOfWheels(), fetchedEntity.getPermitType(),fetchedEntity.getInsuranceStatus(),
					fetchedEntity.getInsuranceNumber(), fetchedEntity.getInsuranceCompany(), fetchedEntity.getVendorId(), fetchedEntity.getCommodityList(),
					fetchedEntity.getInsertTime(), fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy(),fetchedEntity.getUpdatedById());
			
			
			angList.add(angDTO);
			
		}
		
				
		return angList;	
		
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<VehicleDetailsAngDTO> getVehicleDetailsById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			VehicleDetailsDTO fetchedEntity=vendorDetailsService.getVehicleById(entityIdDTO.getEntityId());
			
			VehicleDetailsAngDTO angDTO=new VehicleDetailsAngDTO(fetchedEntity.getVehicleId(), fetchedEntity.getOwnerFirstName(),fetchedEntity.getOwnerMiddleName(),
					fetchedEntity.getOwnerLastName(), fetchedEntity.getVehicleBrandId(), fetchedEntity.getVehicleBodyType(), fetchedEntity.getIsBodyTypeFlexible(),
					fetchedEntity.getRegistrationNumber(), fetchedEntity.getChassisNumber(), fetchedEntity.getLoadCapacityInTons(),fetchedEntity.getLength(), 
					fetchedEntity.getWidth(), fetchedEntity.getHeight(), fetchedEntity.getNumberOfWheels(), fetchedEntity.getPermitType(),fetchedEntity.getInsuranceStatus(),
					fetchedEntity.getInsuranceNumber(), fetchedEntity.getInsuranceCompany(), fetchedEntity.getVendorId(), fetchedEntity.getCommodityList(),
					fetchedEntity.getInsertTime(), fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy(),fetchedEntity.getUpdatedById());
			
			return new ResponseEntity<VehicleDetailsAngDTO>(angDTO, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
	}

	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseDTO  deleteVehicle(@RequestBody EntityIdDTO entityIdDTO,Model model)
	{
		Boolean detailsDTO=vendorDetailsService.deleteVehicleById(entityIdDTO.getEntityId());
		
		if(detailsDTO)
			return new ResponseDTO("sucess", "");
		
		else
			return new ResponseDTO("failure", "error message");
	}

	
}
