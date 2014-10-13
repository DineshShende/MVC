package com.projectx.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.*;
import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.*;

import com.projectx.mvc.domain.CustomerId;
import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.VerifyMobileDTO;

@Controller
@RequestMapping(value = "/customer/quickregister")
public class CustomerQuickRegisterController {

	@Autowired
	CustomerQuickRegisterDTO customerQuickRegisterDTO;
		
	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String showEmailForm(Model model) {
		model.addAttribute("customerQuick", new CustomerQuickRegisterEntity());

		model.addAttribute("statusMessage", "");
		return "customerQuickRegister";
	}
	
	@RequestMapping( method = RequestMethod.POST)
	public String AddNewCustomer(
			CustomerQuickRegisterEntity customerQuickRegisterEntity,
			Model model) throws Exception {

			
	}
	
	
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public String verifyMobilePin(@ModelAttribute VerifyMobileDTO verifyMobile,Model model)
	{
		
		Boolean result=customerQuickRegisterService.verifyMobile(verifyMobile);
		
		System.out.println(result);
		
		if(result)
			model.addAttribute("mobileVerificationStatus", "Mobile Verification Sucess");
		else
			model.addAttribute("mobileVerificationStatus", "Mobile Verification Failed");
		
		return "verifyEmailMobile";
	}
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public String resendEmailPin(@ModelAttribute UpdateMobilePinDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.ResendMobilePin(mobileDTO);
		
		if(result)
			model.addAttribute("mobileVerificationStatus", "Mobile Pin is sent.Please Enter that code");
		else
			model.addAttribute("mobileVerificationStatus", "Error will sending Pin.Please Try again");
		
		return "verifyEmailMobile";
	}
	

	/*@RequestMapping(value="/add")
	public String add()
	{
		customerId.setCustomerId(customerQuickRegisterService.addNewCustomer(null).getCustomerId());
		
		return "verifyEmail";
	}
	*/
	@ModelAttribute("customerQuickRegisterDTO")
	private CustomerQuickRegisterDTO getCustomerQuickRegisterDTO()
	{
		return customerQuickRegisterDTO;
	}
	
}
