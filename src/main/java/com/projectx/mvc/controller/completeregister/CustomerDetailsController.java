package com.projectx.mvc.controller.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@Controller
@RequestMapping(value = "/customer")
public class CustomerDetailsController {

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@RequestMapping(value="/createCustomerDetailsFromQuickRegisterEntity",method=RequestMethod.POST)
	public String createCustomerDetailsFromQuickRegisterEntity(@ModelAttribute QuickRegisterDTO quickRegisterDTO,Model model)
	{
		CustomerDetailsDTO customerDetailsDTO=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterDTO);
		
		model.addAttribute("customerDetails", customerDetailsDTO);
		
		return "customerDetailsForm";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@ModelAttribute CustomerDetailsDTO customerDetailsDTO,Model model)
	{
		customerDetailsDTO=customerDetailsService.InitializeMetaData(customerDetailsDTO);
		
		System.out.println(customerDetailsDTO);
		
		CustomerDetailsDTO newCustomerDetailsDTO=customerDetailsService
				.merge(customerDetailsDTO);
		
		model.addAttribute("customerDetails", newCustomerDetailsDTO);
		
		return "showCustomerDetails";
	
	}
	
	@RequestMapping(value="/verifyMobileDetails",method=RequestMethod.POST)
	public String verifyMobileDetails(@ModelAttribute VerifyMobileDTO verifyMobileDTO,Model model)
	{
		Boolean result=customerDetailsService.verifyMobileDetails(verifyMobileDTO );
		
		if(result)
		{
			CustomerDetailsDTO updatedCustomerDetailsDTO=customerDetailsService.getCustomerDetailsById(verifyMobileDTO.getCustomerId());
			
			model.addAttribute("customerDetails", updatedCustomerDetailsDTO);
			
			model.addAttribute("mobileVrificationStatus", "sucess");
						
		}
		else
		{
			model.addAttribute("mobileVrificationStatus", "failure");
		}
		
		return "showCustomerDetails";
	}
	
	@RequestMapping(value="/sendMobileVerificationDetails",method=RequestMethod.POST)
	public String sendMobileVerificationDetails(@ModelAttribute CustomerIdTypeMobileDTO customerIdTypeMobileDTO,Model model)
	{
		Boolean result=customerDetailsService.sendMobileVerificationDetails(customerIdTypeMobileDTO);
		
		if(result)
		{
			CustomerDetailsDTO updatedCustomerDetailsDTO=customerDetailsService.getCustomerDetailsById(customerIdTypeMobileDTO.getCustomerId());
			
			model.addAttribute("customerDetails", updatedCustomerDetailsDTO);
			
			model.addAttribute("sendMobileVerificationStatus", "sucess");
						
		}
		else
		{
			model.addAttribute("sendMobileVerificationStatus", "failure");
		}
		
		return "showCustomerDetails";
	}
	
	@RequestMapping(value="/verifyEmailDetails/{customerId}/{customerType}/{email}/{emailHash}",method=RequestMethod.GET)
	public String verifyEmailDetails(@PathVariable Long customerId,@PathVariable Integer customerType,@PathVariable String email,@PathVariable String emailHash ,Model model)
	{
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(customerId, customerType, email, emailHash);
		
		Boolean result=customerDetailsService.verifyEmailDetails(verifyEmailDTO );
		
		if(result)
		{
			CustomerDetailsDTO updatedCustomerDetailsDTO=customerDetailsService.getCustomerDetailsById(verifyEmailDTO.getCustomerId());
			
			model.addAttribute("customerDetails", updatedCustomerDetailsDTO);
			
			model.addAttribute("emailVrificationStatus", "sucess");
						
		}
		else
		{
			model.addAttribute("emailVrificationStatus", "failure");
		}
		
		return "showCustomerDetails";
	}
	
	@RequestMapping(value="/sendEmailVerificationDetails",method=RequestMethod.POST)
	public String sendEmailVerificationDetails(@ModelAttribute CustomerIdTypeEmailDTO customerIdTypeEmailDTO,Model model)
	{
		Boolean result=customerDetailsService.sendEmailVerificationDetails(customerIdTypeEmailDTO);
		
		if(result)
		{
			CustomerDetailsDTO updatedCustomerDetailsDTO=customerDetailsService.getCustomerDetailsById(customerIdTypeEmailDTO.getCustomerId());
			
			model.addAttribute("customerDetails", updatedCustomerDetailsDTO);
			
			model.addAttribute("sendEmailVerificationStatus", "sucess");
						
		}
		else
		{
			model.addAttribute("sendEmailVerificationStatus", "failure");
		}
		
		return "showCustomerDetails";
	}
}
