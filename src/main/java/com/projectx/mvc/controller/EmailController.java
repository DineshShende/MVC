package com.projectx.mvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectx.mvc.domain.Email;
import com.projectx.mvc.services.EmailService;

@Controller
@RequestMapping(value="/email")

public class EmailController {
		
	
	@Autowired
	EmailService customerQuickRegisterService;

    @RequestMapping(value="/addemail", method=RequestMethod.GET)
    public String showEmailForm(Model model) {
        model.addAttribute("email", new Email());
             
        return "emailForm";
    }
    
    @RequestMapping(value="/addemail",method=RequestMethod.POST)
    public String AddEmail(@ModelAttribute  Email email,Model model) throws Exception
    {
    	   	
    	Email redirectEmail=customerQuickRegisterService.addEmail(email);
    	
    	model.addAttribute("email",redirectEmail);
    	
    	
    	return "emailForm";
    }

}