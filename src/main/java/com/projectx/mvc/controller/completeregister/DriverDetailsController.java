package com.projectx.mvc.controller.completeregister;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.util.validator.DriverDetailsValidator;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;

@RestController
@RequestMapping(value = "/driver")
public class DriverDetailsController {
	
	@Autowired
	VendorDetailsService vendorDetailsService;

	
	@Autowired
	DriverDetailsValidator driverDetailsValidator;

	@InitBinder("driverDetailsDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(driverDetailsValidator);
    }

	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<DriverDetailsDTO> addDriver(@Valid @RequestBody DriverDetailsDTO driverDetailsDTO,BindingResult result)
	{
		
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		DriverDetailsDTO detailsDTOInitialized=vendorDetailsService.initializeDriverDetails(driverDetailsDTO);
		
		DriverDetailsDTO detailsDTO=vendorDetailsService.addDriver(detailsDTOInitialized);
		
		return new ResponseEntity<DriverDetailsDTO>(detailsDTO, HttpStatus.OK);
	
	}
	
	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<DriverDetailsDTO> getById(@RequestBody EntityIdDTO entityIdDTO)
	{
		try{
			DriverDetailsDTO fetchedEntity=vendorDetailsService.getDriverById(entityIdDTO.getEntityId());
			return new ResponseEntity<DriverDetailsDTO>(fetchedEntity, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/getByVendorId",method=RequestMethod.POST)
	public List<DriverDetailsDTO> showDriverDetails(@RequestBody EntityIdDTO entityIdDTO,Model model)
	{
		List<DriverDetailsDTO> driverList=vendorDetailsService.getDriversByVendor(entityIdDTO.getEntityId());
		
		
		return driverList;
	}
	
	
	
	
	@RequestMapping(value="/deleteById",method=RequestMethod.POST)
	public Boolean  deleteDriver(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		Boolean detailsDTO=vendorDetailsService.deleteDriverById(entityIdDTO.getEntityId());
		
		return detailsDTO;
	}

	

}
/*
@RequestMapping(value="/updateDriver",method=RequestMethod.POST)
public ResponseEntity<DriverDetailsDTO>  updateDriver(@Valid @ModelAttribute DriverDetailsDTO driverDetailsDTO,BindingResult result,Model model)
{
	if(result.hasErrors())
	{
		//model.addAttribute("driverDetails", driverDetailsDTO);
		
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	DriverDetailsDTO detailsDTOInitialized=vendorDetailsService.initializeDriverDetails(driverDetailsDTO);
	
	DriverDetailsDTO detailsDTO=vendorDetailsService.update(driverDetailsDTO);
	
	return new ResponseEntity<DriverDetailsDTO>(detailsDTO, HttpStatus.OK);
}
*/

/*
@RequestMapping(value="/driverDetailsForm",method=RequestMethod.POST)
public String driverForn(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
{
	DriverDetailsDTO detailsDTO=new  DriverDetailsDTO();
	
	detailsDTO.setVendorId(entityIdDTO.getEntityId());
	
	model.addAttribute("driverDetails", detailsDTO);
	
	return "completeregister/driverDetailsForm";
}


@RequestMapping(value="/updateDriverDetails",method=RequestMethod.POST)
public String updateDriverForn(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
{
	DriverDetailsDTO detailsDTO=vendorDetailsService.getDriverById(entityIdDTO.getEntityId());
	
	model.addAttribute("driverDetails", detailsDTO);
	
	return "completeregister/driverDetailsUpdateForm";
}
*/