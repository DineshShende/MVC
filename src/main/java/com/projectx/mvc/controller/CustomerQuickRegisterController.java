package com.projectx.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;



@Controller
@RequestMapping(value="/customer")

public class CustomerQuickRegisterController {

	@RequestMapping(value="/quickregister", method=RequestMethod.GET)
    public String showEmailForm(Model model) {
        model.addAttribute("email", new CustomerQuickRegisterEntity());
            
        return "customerQuickRegister";
    }
	
	@RequestMapping(value="/quickregister",method=RequestMethod.POST)
    public String AddEmail(@ModelAttribute  CustomerQuickRegisterEntity customerQuickRegisterEntity,Model model) throws Exception
    {
    	  	
    	   	
    	return "verifyDetails";
    }
	
}
