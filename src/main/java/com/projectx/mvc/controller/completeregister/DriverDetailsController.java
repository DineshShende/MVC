package com.projectx.mvc.controller.completeregister;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.util.validator.DriverDetailsValidator;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;

@Controller
@RequestMapping(value = "/vendor")
public class DriverDetailsController {
	
	@Autowired
	VendorDetailsService vendorDetailsService;

	
	@Autowired
	DriverDetailsValidator driverDetailsValidator;

	@InitBinder("driverDetailsDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(driverDetailsValidator);
    }

	
	@RequestMapping(value="/driverDetailsForm",method=RequestMethod.POST)
	public String driverForn(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		DriverDetailsDTO detailsDTO=new  DriverDetailsDTO();
		
		detailsDTO.setVendorId(entityIdDTO.getEntityId());
		
		model.addAttribute("driverDetails", detailsDTO);
		
		return "driverDetailsForm";
	}
	
	@RequestMapping(value="/updateDriverDetails",method=RequestMethod.POST)
	public String updateDriverForn(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		DriverDetailsDTO detailsDTO=vendorDetailsService.getDriverById(entityIdDTO.getEntityId());
		
		model.addAttribute("driverDetails", detailsDTO);
		
		return "driverDetailsUpdateForm";
	}
	
	@RequestMapping(value="/addDriver",method=RequestMethod.POST)
	public String addDriver(@Valid @ModelAttribute DriverDetailsDTO driverDetailsDTO,BindingResult result,Model model)
	{
		
		if(result.hasErrors())
		{
			model.addAttribute("driverDetails", driverDetailsDTO);
			
			return "driverDetailsForm";
		}
		
		DriverDetailsDTO detailsDTO=vendorDetailsService.addDriver(driverDetailsDTO);
		
		if(detailsDTO.getDriverId()!=null)
			model.addAttribute("addDriverStatus","Driver Added Sucessfully");
		
		
		VendorDetailsDTO updatedVendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(driverDetailsDTO.getVendorId());
		
		model.addAttribute("vendorDetails", updatedVendorDetailsDTO);
		
		
		model=vendorDetailsService.initialiseShowVendorDetails(driverDetailsDTO.getVendorId(), model);
		return "showVendorDetails";
	}
	
	@RequestMapping(value="/updateDriver",method=RequestMethod.POST)
	public String  updateDriver(@Valid @ModelAttribute DriverDetailsDTO driverDetailsDTO,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			model.addAttribute("driverDetails", driverDetailsDTO);
			
			return "driverDetailsUpdateForm";
		}
		
		DriverDetailsDTO detailsDTO=vendorDetailsService.update(driverDetailsDTO);
		
		model.addAttribute("vendorDetails", vendorDetailsService.getCustomerDetailsById(driverDetailsDTO.getVendorId()));
		
		if(detailsDTO.getDriverId()!=null)
			model.addAttribute("updateDriverStatus","Driver Updated Sucessfully");
		
				
		model=vendorDetailsService.initialiseShowVendorDetails(driverDetailsDTO.getVendorId(), model);
		return "showVendorDetails";
	}
	
	@RequestMapping(value="/deleteDriver",method=RequestMethod.POST)
	@ResponseBody
	public Boolean  deleteDriver(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		Boolean detailsDTO=vendorDetailsService.deleteDriverById(entityIdDTO.getEntityId());
		
		
		return detailsDTO;
	}

	@RequestMapping(value="/showDriverDetails",method=RequestMethod.POST)
	public String showDriverDetails(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		List<DriverDetailsDTO> driverList=vendorDetailsService.getDriversByVendor(entityIdDTO.getEntityId());
		
			
		model.addAttribute("driverList", driverList);
		
		return "showDriverDetails";
	}

}
