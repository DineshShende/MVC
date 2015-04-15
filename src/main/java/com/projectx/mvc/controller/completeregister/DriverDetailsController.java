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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.completeregister.ResponseDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.util.validator.DriverDetailsValidator;
import com.projectx.rest.domain.ang.DriverDetailsAngDTO;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.EntityIdTypeDTO;

@RestController
@RequestMapping(value = "/driver")
public class DriverDetailsController {
	
	@Autowired
	VendorDetailsService vendorDetailsService;


	/*
	@Autowired
	DriverDetailsValidator driverDetailsValidator;

	@InitBinder("driverDetailsDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(driverDetailsValidator);
    }
*/
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> addDriver(@Valid @RequestBody DriverDetailsAngDTO driverDetailsDTO,BindingResult result)
	{
		
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		DriverDetailsDTO driverDetails=new DriverDetailsDTO(driverDetailsDTO.getDriverId(), driverDetailsDTO.getFirstName(), driverDetailsDTO.getMiddleName(),
				driverDetailsDTO.getLastName(), driverDetailsDTO.getDateOfBirth(), driverDetailsDTO.getBloodGroup(), driverDetailsDTO.getHomeAddress(),
				driverDetailsDTO.getMobile(), driverDetailsDTO.getIsMobileVerified(), driverDetailsDTO.getHomeContactNumber(),
				driverDetailsDTO.getLicenceNumber(), driverDetailsDTO.getDrivingSince(),driverDetailsDTO.getEmployedSince(),
				driverDetailsDTO.getIsFreightRequestPermissionGiven(), driverDetailsDTO.getIsDealFinalizationPermissionGiven(),
				driverDetailsDTO.getLanguage(), driverDetailsDTO.getVendorId(), new Date(), new Date(), driverDetailsDTO.getUpdatedBy());
		
		DriverDetailsDTO detailsDTOInitialized=vendorDetailsService.initializeDriverDetails(driverDetails);
		
		DriverDetailsDTO detailsDTO=vendorDetailsService.addDriver(detailsDTOInitialized);
		
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("sucess", ""), HttpStatus.OK);
	
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<DriverDetailsAngDTO> getById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			DriverDetailsDTO fetchedEntity=vendorDetailsService.getDriverById(entityIdDTO.getEntityId());
			
			DriverDetailsAngDTO detailsAngDTO=new DriverDetailsAngDTO(fetchedEntity.getDriverId(), fetchedEntity.getFirstName(), fetchedEntity.getMiddleName(),
					fetchedEntity.getLastName(), fetchedEntity.getDateOfBirth(), fetchedEntity.getBloodGroup(), 
					fetchedEntity.getHomeAddress(), fetchedEntity.getMobile(), fetchedEntity.getIsMobileVerified(),
					fetchedEntity.getHomeContactNumber(), fetchedEntity.getLicenceNumber(), fetchedEntity.getDrivingSince(),
					fetchedEntity.getEmployedSince(), fetchedEntity.getIsFreightRequestPermissionGiven(), fetchedEntity.getIsDealFinalizationPermissionGiven(),
					fetchedEntity.getLanguage(), fetchedEntity.getVendorId(), fetchedEntity.getInsertTime(), fetchedEntity.getUpdateTime(),fetchedEntity.getUpdatedBy());
			
			return new ResponseEntity<DriverDetailsAngDTO>(detailsAngDTO, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/getByVendorId",method=RequestMethod.POST)
	public List<DriverDetailsDTO> showDriverDetails(@RequestBody EntityIdDTO entityIdDTO)
	{
		List<DriverDetailsDTO> driverList=vendorDetailsService.getDriversByVendor(entityIdDTO.getEntityId());
		
		List<DriverDetailsAngDTO> driverListAng=new ArrayList<DriverDetailsAngDTO>();
		
		for(int i=0;i<driverList.size();i++)
		{
			DriverDetailsDTO fetchedEntity=driverList.get(i);
			
			DriverDetailsAngDTO detailsAngDTO=new DriverDetailsAngDTO(fetchedEntity.getDriverId(), fetchedEntity.getFirstName(), fetchedEntity.getMiddleName(),
					fetchedEntity.getLastName(), fetchedEntity.getDateOfBirth(), fetchedEntity.getBloodGroup(), 
					fetchedEntity.getHomeAddress(), fetchedEntity.getMobile(), fetchedEntity.getIsMobileVerified(),
					fetchedEntity.getHomeContactNumber(), fetchedEntity.getLicenceNumber(), fetchedEntity.getDrivingSince(),
					fetchedEntity.getEmployedSince(), fetchedEntity.getIsFreightRequestPermissionGiven(), fetchedEntity.getIsDealFinalizationPermissionGiven(),
					fetchedEntity.getLanguage(), fetchedEntity.getVendorId(), fetchedEntity.getInsertTime(), fetchedEntity.getUpdateTime(),fetchedEntity.getUpdatedBy());
			
			driverListAng.add(detailsAngDTO);
			
		}
		
		return driverList;
	}
	
	
	
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseDTO  deleteDriver(@RequestBody EntityIdDTO entityIdDTO,Model model)
	{
		Boolean detailsDTO=vendorDetailsService.deleteDriverById(entityIdDTO.getEntityId());
		
		if(detailsDTO)
			return new ResponseDTO("sucess", "");
		else		
			return new ResponseDTO("failure", "Error");
	}

	

}
