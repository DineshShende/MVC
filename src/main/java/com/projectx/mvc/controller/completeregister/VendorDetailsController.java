package com.projectx.mvc.controller.completeregister;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.util.validator.DriverDetailsValidator;
import com.projectx.mvc.util.validator.VendorDetailsValidator;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@RestController
@RequestMapping(value = "/vendor")
public class VendorDetailsController {

	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	VendorDetailsValidator vendorDetailsValidator;
	
	private static final Logger logger = Logger.getLogger(VendorDetailsController.class);
	
	@InitBinder("vendorDetailsDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(vendorDetailsValidator);
    }
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResponseEntity<VendorDetailsDTO> save(@Valid @RequestBody VendorDetailsDTO vendorDetailsDTO,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		vendorDetailsDTO=vendorDetailsService.intializeMetaData(vendorDetailsDTO);
	
		VendorDetailsDTO newVendorDetailsDTO=vendorDetailsService
				.update(vendorDetailsDTO);
		
		
		return new ResponseEntity<VendorDetailsDTO>(newVendorDetailsDTO, HttpStatus.OK);
	
	}
	

	@RequestMapping(value="/getById",method=RequestMethod.POST)
	public ResponseEntity<VendorDetailsDTO> getById(@RequestBody EntityIdDTO entityIdDTO)
	{
	
		try{
			VendorDetailsDTO fetchedEntity=vendorDetailsService.getCustomerDetailsById(entityIdDTO.getEntityId());
			return new ResponseEntity<VendorDetailsDTO>(fetchedEntity, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/verifyMobileDetails",method=RequestMethod.POST)
	public Boolean verifyMobileDetails(@ModelAttribute VerifyMobileDTO verifyMobileDTO,Model model)
	{
		Boolean result=vendorDetailsService.verifyMobileDetails(verifyMobileDTO );
		
		return result;
	}
	
	@RequestMapping(value="/sendMobileVerificationDetails",method=RequestMethod.POST)
	public Boolean sendMobileVerificationDetails(@ModelAttribute CustomerIdTypeMobileTypeUpdatedByDTO customerIdTypeMobileDTO,Model model)
	{
		Boolean result=vendorDetailsService.sendMobileVerificationDetails(customerIdTypeMobileDTO);
				
		return result;
	}
	
	@RequestMapping(value="/verifyEmailDetails/{customerId}/{customerType}/{emailType}/{updatedBy}/{emailHash}",method=RequestMethod.GET)
	public String verifyEmailDetails(@PathVariable Long customerId,@PathVariable Integer customerType,@PathVariable Integer emailType,
			@PathVariable String updatedBy ,@PathVariable String emailHash ,Model model)
	{
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(customerId, customerType, emailType, emailHash,updatedBy);
		
		Boolean result=vendorDetailsService.verifyEmailDetails(verifyEmailDTO);
		
		VendorDetailsDTO updatedVendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(verifyEmailDTO.getCustomerId());
		
		model.addAttribute("vendorDetails", updatedVendorDetailsDTO);
		
		if(result)
		{
			model.addAttribute("emailVrificationStatus", "sucess");
						
		}
		else
		{
			model.addAttribute("emailVrificationStatus", "failure");
		}
		model=vendorDetailsService.initialiseShowVendorDetails(customerId, model);
		return "completeregister/showVendorDetails";
	}
	
	@RequestMapping(value="/sendEmailVerificationDetails",method=RequestMethod.POST)
	public Boolean sendEmailVerificationDetails(@ModelAttribute CustomerIdTypeEmailTypeUpdatedByDTO customerIdTypeEmailDTO,Model model)
	{
		Boolean result=vendorDetailsService.sendEmailVerificationDetails(customerIdTypeEmailDTO);
		
		return result;
	}
	
	
		
		
}
/*
	
	@RequestMapping(value="/updateVehicleDetails",method=RequestMethod.POST)
	public String updateVehicleForn(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.getVehicleById(entityIdDTO.getEntityId());
		
		model.addAttribute("vehicleDetails", vehicleDetailsDTO);
		
		return "completeregister/vehicleDetailsForm";
	}
	
	
	@RequestMapping(value="/updateVehicle",method=RequestMethod.POST)
	public String  updateVehicle(@ModelAttribute VehicleDetailsDTO vehicleDetailsDTO,Model model)
	{
		VehicleDetailsDTO vehicleDetails=vendorDetailsService.update(vendorDetails) 
		
		model.addAttribute("vendorDetails", vendorDetailsService.getCustomerDetailsById(driverDetailsDTO.getVendorId()));
		
		if(detailsDTO.getDriverId()!=null)
			model.addAttribute("updateDriverStatus","Driver Updated Sucessfully");
		
				
		model=vendorDetailsService.initialiseShowVendorDetails(driverDetailsDTO.getVendorId(), model);
		return "showVendorDetails";
	}
	
	@RequestMapping(value="/vehicleDetailsForm",method=RequestMethod.POST)
	public String vehicleDetailsForn(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		VehicleDetailsDTO vehicleDetailsDTO=new VehicleDetailsDTO();
		
		vehicleDetailsDTO.setVendorId(entityIdDTO.getEntityId());
		
		model.addAttribute("vehicleDetails", vehicleDetailsDTO);
		
		return "completeregister/vehicleDetailsForm";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(@Valid @ModelAttribute VendorDetailsDTO vendorDetailsDTO,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			model.addAttribute("vendorDetails", vendorDetailsDTO);
			
			return "completeregister/vendorDetailsForm";
		}
		
		vendorDetailsDTO=vendorDetailsService.intializeMetaData(vendorDetailsDTO);
		
		VendorDetailsDTO newVendorDetailsDTO=vendorDetailsService
				.update(vendorDetailsDTO);
		
		model.addAttribute("vendorDetails", newVendorDetailsDTO);
		
		model=vendorDetailsService.initialiseShowVendorDetails(vendorDetailsDTO.getVendorId(), model);
		
		return "completeregister/showVendorDetails";
	
	}

	@RequestMapping(value="/editForm",method=RequestMethod.POST)
	public String editForm(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		
		VendorDetailsDTO newVendorDetailsDTO=vendorDetailsService
				.getCustomerDetailsById(entityIdDTO.getEntityId());
		
		model.addAttribute("vendorDetails", newVendorDetailsDTO);
		
		return "completeregister/vendorDetailsForm";
	
	}


	@RequestMapping(value="/createCustomerDetailsFromQuickRegisterEntity",method=RequestMethod.POST)
	public String createCustomerDetailsFromQuickRegisterEntity(@ModelAttribute QuickRegisterDTO quickRegisterDTO,Model model)
	{
		
		VendorDetailsDTO vendorDetailsDTO=vendorDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterDTO);
		
		model.addAttribute("vendorDetails", vendorDetailsDTO);
		
		return "completeregister/vendorDetailsForm";
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

*/