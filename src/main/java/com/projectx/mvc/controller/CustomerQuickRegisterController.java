package com.projectx.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.*;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.*;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.VerifyMobileDTO;

@Controller
@RequestMapping(value = "/customer/quickregister")
public class CustomerQuickRegisterController {

	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String showEmailForm(Model model) {
		model.addAttribute("customerQuick", new CustomerQuickRegisterEntity());

		//model.addAttribute("statusMessage", "");
		return "customerQuickRegister";
	}
	
	
	
	@RequestMapping( method = RequestMethod.POST)
	public String AddNewCustomer(
			@ModelAttribute CustomerQuickRegisterEntity customerQuickRegisterEntity,
			Model model) throws Exception {

		String status=customerQuickRegisterService.checkIfAlreadyExist(customerQuickRegisterEntity);
		
		if(status.equals(REGISTER_NOT_REGISTERED))
		{
			CustomerQuickRegisterDTO newCustomer=customerQuickRegisterService.addNewCustomer(customerQuickRegisterEntity);
			
			model.addAttribute("newAddedCustomer", newCustomer);
			
			if(newCustomer.getStatus().equals(CUST_STATUS_EMAIL) || newCustomer.getStatus().equals(STATUS_MOBILE_VERFIED_EMAIL_PENDING))
				return "verifyEmail";
			
			if(newCustomer.getStatus().equals(CUST_STATUS_MOBILE)|| newCustomer.getStatus().equals(STATUS_EMAIL_VERFIED_MOBILE_PENDING) )
				return "verifyMobile";
			
			
			return "verifyEmailMobile";
						
		}
		else if(status.equals(REGISTER_EMAIL_ALREADY_REGISTERED))
		{
			model.addAttribute("statusMessage", "Email Already Registered");
		}
		else if(status.equals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED))
		{
			model.addAttribute("statusMessage", "Mobile Already Reistered");
		}
		else if(status.equals(REGISTER_MOBILE_ALREADY_REGISTERED))
		{
			model.addAttribute("statusMessage", "Email and Mobile lready Registred");
		}
		
	
		
		return "customerQuickRegister";
		
		
	}
	
	
	@RequestMapping(value="/verifyMobilePin")
	public String verifyMobilePin(@ModelAttribute VerifyMobileDTO verifyMobile,Model model)
	{
		
		Boolean result=customerQuickRegisterService.verifyMobile(verifyMobile);
		
		System.out.println(result);
		
		if(result)
		{
			
			model.addAttribute("message", "Verification Sucess");
		}
		else
		{
			model.addAttribute("message", "Verification Failed");
		}	
		
		return "verificationStatus";
	}
	
	

}
