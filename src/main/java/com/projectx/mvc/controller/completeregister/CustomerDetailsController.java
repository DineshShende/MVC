package com.projectx.mvc.controller.completeregister;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.mvc.util.validator.CustomerDetailsValidator;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.EntityIdDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;

@Controller
@RequestMapping(value = "/customer")
public class CustomerDetailsController {

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	CustomerDetailsValidator customerDetailsValidator;
	
	@InitBinder("customerDetailsDTO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(customerDetailsValidator);
    }
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ENTITY_TYPE_SECONDARY=2;
	
	@RequestMapping(value="/createCustomerDetailsFromQuickRegisterEntity",method=RequestMethod.POST)
	public String createCustomerDetailsFromQuickRegisterEntity(@ModelAttribute QuickRegisterDTO quickRegisterDTO,Model model)
	{
		CustomerDetailsDTO customerDetailsDTO=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterDTO);
		
		model.addAttribute("customerDetails", customerDetailsDTO);
		
		return "completeregister/customerDetailsForm";
	}
		
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute CustomerDetailsDTO customerDetailsDTO,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			model.addAttribute("customerDetails", customerDetailsDTO);
			
			return "completeregister/customerDetailsForm";
		}	
		
		customerDetailsDTO=customerDetailsService.InitializeMetaData(customerDetailsDTO);
		
		CustomerDetailsDTO newCustomerDetailsDTO=customerDetailsService
				.merge(customerDetailsDTO);
		
		model.addAttribute("customerDetails", newCustomerDetailsDTO);
		
		return "completeregister/documentUpload";
	
	}
	
	@RequestMapping(value="/editForm",method=RequestMethod.POST)
	public String editForm( @ModelAttribute EntityIdDTO entityIdDTO,Model model)
	{
		
		CustomerDetailsDTO newCustomerDetailsDTO=customerDetailsService
				.getCustomerDetailsById(entityIdDTO.getEntityId());
		
		model.addAttribute("customerDetails", newCustomerDetailsDTO);
		
		return "completeregister/customerDetailsForm";
	
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(@Valid @ModelAttribute CustomerDetailsDTO customerDetailsDTO,BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			model.addAttribute("customerDetails", customerDetailsDTO);
			
			return "completeregister/customerDetailsForm";
		}	
		
		customerDetailsDTO=customerDetailsService.InitializeMetaData(customerDetailsDTO);
		
		CustomerDetailsDTO newCustomerDetailsDTO=customerDetailsService
				.merge(customerDetailsDTO);
		
		model.addAttribute("customerDetails", newCustomerDetailsDTO);
		
		model=customerDetailsService.initialiseShowCustomerDetails(customerDetailsDTO.getCustomerId(), model);
		
		return "completeregister/showCustomerDetails";
	
	}
	
	@RequestMapping(value="/verifyMobileDetails",method=RequestMethod.POST)
	@ResponseBody
	public Boolean verifyMobileDetails(@ModelAttribute VerifyMobileDTO verifyMobileDTO,Model model)
	{
		Boolean result=customerDetailsService.verifyMobileDetails(verifyMobileDTO );
		
		
		return result;
	}
	
	@RequestMapping(value="/sendMobileVerificationDetails",method=RequestMethod.POST)
	@ResponseBody
	public Boolean sendMobileVerificationDetails(@ModelAttribute CustomerIdTypeMobileTypeUpdatedByDTO customerIdTypeMobileDTO,Model model)
	{
		Boolean result=customerDetailsService.sendMobileVerificationDetails(customerIdTypeMobileDTO);
		
		
		return result;
	}
	
	@RequestMapping(value="/verifyEmailDetails/{customerId}/{customerType}/{emailType}/{updatedBy}/{emailHash}",method=RequestMethod.GET)
	public String verifyEmailDetails(@PathVariable Long customerId,@PathVariable Integer customerType,@PathVariable Integer emailType,
			@PathVariable String updatedBy,@PathVariable String emailHash ,Model model)
	{
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(customerId, customerType, emailType, emailHash,updatedBy);
		
		Boolean result=customerDetailsService.verifyEmailDetails(verifyEmailDTO );
		
		CustomerDetailsDTO updatedCustomerDetailsDTO=customerDetailsService.getCustomerDetailsById(verifyEmailDTO.getCustomerId());
		
		model.addAttribute("customerDetails", updatedCustomerDetailsDTO);
		
		if(result)
		{
			model.addAttribute("emailVrificationStatus", "sucess");
						
		}
		else
		{
			model.addAttribute("emailVrificationStatus", "failure");
		}
		
		model=customerDetailsService.initialiseShowCustomerDetails(customerId, model);
		
		return "completeregister/showCustomerDetails";
	}
	
	@RequestMapping(value="/sendEmailVerificationDetails",method=RequestMethod.POST)
	@ResponseBody
	public Boolean sendEmailVerificationDetails(@ModelAttribute CustomerIdTypeEmailTypeUpdatedByDTO customerIdTypeEmailDTO,Model model)
	{
		Boolean result=customerDetailsService.sendEmailVerificationDetails(customerIdTypeEmailDTO);
		
		
		return result;
	}
	
	
}
