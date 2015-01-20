package com.projectx.mvc.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@Controller
@RequestMapping(value = "/vendor")
public class VendorDetailsController {

	@Autowired
	VendorDetailsService vendorDetailsService;
	
	
	
	
	@RequestMapping(value="/createCustomerDetailsFromQuickRegisterEntity",method=RequestMethod.POST)
	public String createCustomerDetailsFromQuickRegisterEntity(@ModelAttribute QuickRegisterDTO quickRegisterDTO,Model model)
	{
		VendorDetailsDTO vendorDetailsDTO=vendorDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterDTO);
		
		model.addAttribute("vendorDetails", vendorDetailsDTO);
		
		return "vendorDetailsForm";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@ModelAttribute VendorDetailsDTO vendorDetailsDTO,Model model)
	{
		vendorDetailsDTO=vendorDetailsService.intializeMetaData(vendorDetailsDTO);
	
		System.out.println(vendorDetailsDTO);
				
		VendorDetailsDTO newVendorDetailsDTO=vendorDetailsService
				.update(vendorDetailsDTO);
		
		model.addAttribute("vendorDetails", newVendorDetailsDTO);
		
		return "documentUploadVendor";
	
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(@ModelAttribute VendorDetailsDTO vendorDetailsDTO,Model model)
	{
		vendorDetailsDTO=vendorDetailsService.intializeMetaData(vendorDetailsDTO);
		
		VendorDetailsDTO newVendorDetailsDTO=vendorDetailsService
				.update(vendorDetailsDTO);
		
		model.addAttribute("vendorDetails", newVendorDetailsDTO);
		
		model=vendorDetailsService.initialiseShowVendorDetails(vendorDetailsDTO.getVendorId(), model);
		
		return "showVendorDetails";
	
	}

	@RequestMapping(value="/editForm",method=RequestMethod.POST)
	public String editForm(@ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		
		VendorDetailsDTO newVendorDetailsDTO=vendorDetailsService
				.getCustomerDetailsById(entityIdDTO.getEntityId());
		
		model.addAttribute("vendorDetails", newVendorDetailsDTO);
		
		return "vendorDetailsForm";
	
	}
	
	@RequestMapping(value="/verifyMobileDetails",method=RequestMethod.POST)
	public String verifyMobileDetails(@ModelAttribute VerifyMobileDTO verifyMobileDTO,Model model)
	{
		Boolean result=vendorDetailsService.verifyMobileDetails(verifyMobileDTO );
		
		VendorDetailsDTO updatedVendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(verifyMobileDTO.getEntityId());
		
		model.addAttribute("vendorDetails", updatedVendorDetailsDTO);
		
		if(result)
		{
			model.addAttribute("mobileVrificationStatus", "sucess");
						
		}
		else
		{
			model.addAttribute("mobileVrificationStatus", "failure");
		}
		
		model=vendorDetailsService.initialiseShowVendorDetails(verifyMobileDTO.getEntityId(), model);
		return "showVendorDetails";
	}
	
	@RequestMapping(value="/sendMobileVerificationDetails",method=RequestMethod.POST)
	public String sendMobileVerificationDetails(@ModelAttribute CustomerIdTypeMobileTypeDTO customerIdTypeMobileDTO,Model model)
	{
		Boolean result=vendorDetailsService.sendMobileVerificationDetails(customerIdTypeMobileDTO);
		
		VendorDetailsDTO updatedVendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(customerIdTypeMobileDTO.getCustomerId());
		
		model.addAttribute("vendorDetails", updatedVendorDetailsDTO);
		
		if(result)
		{
			model.addAttribute("sendMobileVerificationStatus", "sucess");
						
		}
		else
		{
			model.addAttribute("sendMobileVerificationStatus", "failure");
		}
		
		model=vendorDetailsService.initialiseShowVendorDetails(customerIdTypeMobileDTO.getCustomerId(), model);
		return "showVendorDetails";
	}
	
	@RequestMapping(value="/verifyEmailDetails/{customerId}/{customerType}/{emailType}/{emailHash}",method=RequestMethod.GET)
	public String verifyEmailDetails(@PathVariable Long customerId,@PathVariable Integer customerType,@PathVariable Integer emailType,@PathVariable String emailHash ,Model model)
	{
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(customerId, customerType, emailType, emailHash);
		
		Boolean result=vendorDetailsService.verifyEmailDetails(verifyEmailDTO);
		
		VendorDetailsDTO updatedVendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(verifyEmailDTO.getEntityId());
		
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
		return "showVendorDetails";
	}
	
	@RequestMapping(value="/sendEmailVerificationDetails",method=RequestMethod.POST)
	public String sendEmailVerificationDetails(@ModelAttribute CustomerIdTypeEmailTypeDTO customerIdTypeEmailDTO,Model model)
	{
		Boolean result=vendorDetailsService.sendEmailVerificationDetails(customerIdTypeEmailDTO);
		
		VendorDetailsDTO updatedVendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(customerIdTypeEmailDTO.getCustomerId());
		
		model.addAttribute("vendorDetails", updatedVendorDetailsDTO);
		
		if(result)
		{
			model.addAttribute("sendEmailVerificationStatus", "sucess");
						
		}
		else
		{
			model.addAttribute("sendEmailVerificationStatus", "failure");
		}
		model=vendorDetailsService.initialiseShowVendorDetails(customerIdTypeEmailDTO.getCustomerId(), model);
		return "showVendorDetails";
	}
	
	
}
