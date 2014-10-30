package com.projectx.mvc.controller;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataFixture.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.domain.CustomerQuickRegisterMVCDTO;

import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyMobileDTO;

@Controller
@RequestMapping(value = "/customer/quickregister")
public class CustomerQuickRegisterController {

	@Autowired
	CustomerQuickRegisterMVCDTO customerQuickRegisterDTO;
		
	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService;


	
	@RequestMapping(method = RequestMethod.GET)
	public String showEmailForm(Model model) {
		model.addAttribute("customerQuickRegisterEntity", new CustomerQuickRegisterEntity());
		
		return "customerQuickRegister";
	}
	
	
	@RequestMapping( method = RequestMethod.POST)
	public String AddNewCustomer(
			@Valid CustomerQuickRegisterEntity customerQuickRegisterEntity,
			BindingResult result,Model model) throws Exception {
		
		if(customerQuickRegisterEntity.getEmail().equals(""))
			customerQuickRegisterEntity.setEmail(null);
			
		if(result.hasErrors())
		{
			return "customerQuickRegister";
		}

		String status=customerQuickRegisterService.checkIfAlreadyExist(customerQuickRegisterEntity);
		
		if(status.equals(REGISTER_NOT_REGISTERED))
		{
			CustomerQuickRegisterSavedEntityDTO cutomerQuickRegisterNewDTO=customerQuickRegisterService.addNewCustomer(customerQuickRegisterEntity);
			
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(cutomerQuickRegisterNewDTO.getCustomer());
			
			return "verifyEmailMobile";
			
		}
		else
		{
			String message=customerQuickRegisterService.populateMessageForDuplicationField(status);
			model.addAttribute("message", message);
			
			return "alreadyRegistered";
		}
		
		
			
	}
	
	
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public String verifyMobilePin(@ModelAttribute VerifyMobileDTO verifyMobile,Model model)
	{
		
		Boolean result=customerQuickRegisterService.verifyMobile(verifyMobile);
		
		//System.out.println(result);
		
		if(result)
		{
			model.addAttribute("mobileVerificationStatus", "Mobile Verification Sucess");
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(verifyMobile.getCustomerId())));;
			
		}	
		else
			model.addAttribute("mobileVerificationStatus", "Mobile Verification Failed");
		
		return "verifyEmailMobile";
	}
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public String resendMobilePin(@ModelAttribute CustomerIdDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.reSendMobilePin(mobileDTO);
		
		if(result)
		{	
			model.addAttribute("mobileVerificationStatus", "Mobile Pin is sent.Please Enter that code");
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(mobileDTO.getCustomerId())));;
		}	
		else
			model.addAttribute("mobileVerificationStatus", "Error will sending Pin.Please Try again");
		
		return "verifyEmailMobile";
	}
	
	
	@RequestMapping(value="/verifyEmailHash/{customerId}/{emailHash}",method=RequestMethod.GET)
	public String verifyEmailHash(@PathVariable Long customerId,@PathVariable String emailHash,Model model)
	{
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(customerId, emailHash);
		
		Boolean result=customerQuickRegisterService.verifyEmail(verifyEmailDTO);
		
		System.out.println(result);
		
		if(result)
		{
			model.addAttribute("emailVerificationStatus", "Email Verification Sucess");
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(verifyEmailDTO.getCustomerId())));;
		}
		else
			model.addAttribute("emailVerificationStatus", "Email Verification Failed");
		
		return "verifyEmailMobile";
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public String resendEmailHash(@ModelAttribute CustomerIdDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.reSendEmailHash(mobileDTO);
		
		if(result)
		{
			model.addAttribute("emailVerificationStatus", "Verification Email Sent");
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(mobileDTO.getCustomerId())));;
		}
		else
			model.addAttribute("emailVerificationStatus", "Error will sending Email.Please Try again");
		
		return "verifyEmailMobile";
	}
	
	

	@RequestMapping(value="/cleartestdata")
	public void clearTestData()
	{
		customerQuickRegisterService.clearTestData();
		
	}
	
	@ModelAttribute("customerQuickRegisterDTO")
	private CustomerQuickRegisterMVCDTO getCustomerQuickRegisterDTO()
	{
		return customerQuickRegisterDTO;
	}
	
	
}
