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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.commn.ResponseDTO;
import com.projectx.mvc.exception.repository.completeregister.DriverDetailsAlreadyPresentException;
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

	@Value("${DRIVER_DETAILS_NOT_FOUND_BY_ID}")
	private String DRIVER_DETAILS_NOT_FOUND_BY_ID;
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>> addDriver(@Valid @RequestBody DriverDetailsAngDTO driverDetailsDTO,BindingResult result)
	{
		
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		DriverDetailsDTO driverDetails=driverDetailsDTO.toDriverDetailsDTO();
		
		DriverDetailsDTO detailsDTOInitialized=vendorDetailsService.initializeDriverDetails(driverDetails);
		
		try{
			DriverDetailsDTO detailsDTO=vendorDetailsService.addDriver(detailsDTOInitialized);
			
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
		}catch(DriverDetailsAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()), HttpStatus.OK);
		}
	
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<DriverDetailsAngDTO>> getById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			DriverDetailsDTO fetchedEntity=vendorDetailsService.getDriverById(entityIdDTO.getEntityId());
			
			DriverDetailsAngDTO detailsAngDTO=DriverDetailsAngDTO.fromDriverDetailsDTO(fetchedEntity);
					
			return new ResponseEntity<ResponseDTO<DriverDetailsAngDTO>>(new ResponseDTO<DriverDetailsAngDTO>(detailsAngDTO,""), HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<DriverDetailsAngDTO>>(new ResponseDTO<DriverDetailsAngDTO>(null,DRIVER_DETAILS_NOT_FOUND_BY_ID), HttpStatus.OK);
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
			
			DriverDetailsAngDTO detailsAngDTO=DriverDetailsAngDTO.fromDriverDetailsDTO(fetchedEntity);
					
			driverListAng.add(detailsAngDTO);
			
		}
		
		return driverList;
	}
	
	
	
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<String>>  deleteDriver(@RequestBody EntityIdDTO entityIdDTO,Model model)
	{
		Boolean detailsDTO=vendorDetailsService.deleteDriverById(entityIdDTO.getEntityId());
		
		if(detailsDTO)
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
		else		
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", DRIVER_DETAILS_NOT_FOUND_BY_ID), HttpStatus.OK);
	}

	

}
