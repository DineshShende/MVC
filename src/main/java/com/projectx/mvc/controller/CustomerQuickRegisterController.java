package com.projectx.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.services.CustomerQuickRegisterService;



@Controller
@RequestMapping(value="/customer")

public class CustomerQuickRegisterController {

	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService; 
	
	@RequestMapping(value="/quickregister", method=RequestMethod.GET)
    public String showEmailForm(Model model) {
        model.addAttribute("customerQuick", new CustomerQuickRegisterEntity());
            
        return "customerQuickRegister";
    }
	
	@RequestMapping(value="/quickregister",method=RequestMethod.POST)
    public String AddEmail(@ModelAttribute  CustomerQuickRegisterEntity customerQuickRegisterEntity,Model model) throws Exception
    {
    	  	
		String status=customerQuickRegisterService.setStatus(customerQuickRegisterEntity);
		
		if(status=="EmailMobileVerificationPending")
		{
			return "verifyEmailMobile";
		}
		else if(status=="EmailVerificationPending")
		{
			return "verifyEmail";
		}
		else if(status=="MobileVerificationPending")
		{
			return "verifyMobile";
		}
    	   	
    	return "customerQuickRegister";
    }
	
}
