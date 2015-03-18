package com.projectx.mvc.controller.quickregister;

import static com.projectx.mvc.fixture.quickregister.QuickRegisterDataConstants.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.QuickRegisterMVCDTO;
import com.projectx.mvc.domain.quickregister.LoginDetailsDTO;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.mvc.util.validator.QuickRegisterEntityValidator;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailOrMobileOptionUpdatedBy;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeUpdatedBy;
import com.projectx.rest.domain.quickregister.ForgetPasswordEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStatusDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailLoginDetails;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;


@Controller
@RequestMapping(value = "/quickregister")
@PropertySource(value="classpath:/application.properties")
public class QuickRegisterController {

	@Autowired
	Environment env;

	
	@Autowired
	QuickRegisterMVCDTO customerQuickRegisterDTO;
		
	@Autowired
	QuickRegisterService customerQuickRegisterService;


	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
    private QuickRegisterEntityValidator validator;
 	
	@InitBinder("customerQuickRegisterEntity")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
	
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ENTITY_TYPE_SECONDARY=2;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String showEmailForm(Model model) {
		model.addAttribute("customerQuickRegisterEntity", new QuickRegisterEntity());
		
		return "quickregister/quickRegister";
	}
	
	
	@RequestMapping( method = RequestMethod.POST)
	public String AddNewCustomer(
			
			 @Valid @ModelAttribute("customerQuickRegisterEntity")QuickRegisterEntity customerQuickRegisterEntity,
				BindingResult result,Model model) throws Exception {
		
		if(customerQuickRegisterEntity.getEmail().equals(""))
			customerQuickRegisterEntity.setEmail(null);
			
		
		if(result.hasErrors())
		{
			model.addAttribute("customerQuickRegisterEntity", new QuickRegisterEntity());
			return "quickregister/quickRegister";
		}

		QuickRegisterStatusDTO status=customerQuickRegisterService.checkIfAlreadyExist(customerQuickRegisterEntity);
		
		if(status.getStatus().equals(REGISTER_NOT_REGISTERED))
		{
			QuickRegisterSavedEntityDTO cutomerQuickRegisterNewDTO=customerQuickRegisterService.addNewCustomer(customerQuickRegisterEntity);
			
			customerQuickRegisterDTO.setQuickRegisterDTO(cutomerQuickRegisterNewDTO.getCustomer());
			
			model.addAttribute("customerQuickRegisterDTO", cutomerQuickRegisterNewDTO.getCustomer());
			
			return "quickregister/verifyEmailMobile";
			
		}
		else
		{
			customerQuickRegisterDTO.setQuickRegisterDTO(status.getCustomer());
			
			model.addAttribute("customerQuickRegisterDTO", status.getCustomer());
							
			String message=customerQuickRegisterService.populateMessageForDuplicationField(status.getStatus());
			model.addAttribute("message", message);
			
			return "quickregister/alreadyRegistered";
		}
		
		
			
	}
	
	
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public String verifyMobilePin(@ModelAttribute VerifyMobileDTO verifyMobile,Model model)
	{
		
		Boolean result=false;
		try{
			result=customerQuickRegisterService.verifyMobile(verifyMobile);
		}catch(ResourceNotFoundException e)
		{
			result=false;
		}
		
		if(result)
		{
			model.addAttribute("mobileVerificationStatus", "Mobile Verification Sucess");
			return "quickregister/loginForm";
		}	
		else
		{	
			model.addAttribute("mobileVerificationStatus", "Mobile Verification Failed");
			model.addAttribute("customerQuickRegisterDTO", customerQuickRegisterDTO.getQuickRegisterDTO());
			return "quickregister/verifyEmailMobile";
		}
		
	}
	
	@RequestMapping(value="/verifyEmailHash/{customerId}/{customerType}/{emailType}/{updatedBy}/{emailHash}",method=RequestMethod.GET)
	public String verifyEmailHash(@PathVariable Long customerId,@PathVariable Integer customerType, @PathVariable Integer emailType,@PathVariable String emailHash
			,@PathVariable String updatedBy,Model model)
	{
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(customerId,customerType,emailType, emailHash,updatedBy);
		
		Boolean result=false;
		
		try{
			result=customerQuickRegisterService.verifyEmail(verifyEmailDTO);
		}catch(ResourceNotFoundException e)
		{
			result=false;
		}
		
		if(result)
		{
			model.addAttribute("emailVerificationStatus", "Email Verification Sucess");
			return "quickregister/loginForm";
		}	
		else
		{	
			model.addAttribute("emailVerificationStatus", "Email Verification Failed");
			model.addAttribute("customerQuickRegisterDTO", customerQuickRegisterDTO.getQuickRegisterDTO());
			return "quickregister/verifyEmailMobile";
		}	
		
	}	
		
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	@ResponseBody
	public String resendMobilePin(@ModelAttribute CustomerIdTypeMobileTypeUpdatedByDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.reSendMobilePin(mobileDTO);
		String status=null;
		
		if(result)
			status= "Sucess";
		else
			status= "Failed";
		
		return status;
	}
	
	
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	@ResponseBody
	public String resendEmailHash(@ModelAttribute CustomerIdTypeEmailTypeUpdatedByDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.reSendEmailHash(mobileDTO);
		
		String status=null;
		
		if(result)
			status="Sucess";
		else
			status="Failed";
		
		return status;
	}
	
	
	@RequestMapping(value="/loginForm")
	public String loginForm()
	{
		return "quickregister/loginForm";
	}
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public String verifyLoginDetails(@ModelAttribute LoginDetailsDTO loginDetailsDTO,Model model)
	{
		LoginVerificationDTO loginVerificationDTO=new LoginVerificationDTO(loginDetailsDTO.getEntity(),loginDetailsDTO.getPassword());
		
		AuthenticationDetailsDTO result=null;
		
		try{
			
			result=customerQuickRegisterService.verifyLoginDetails(loginVerificationDTO);
			
		}catch(AuthenticationDetailsNotFoundException e)
		{
			result=new AuthenticationDetailsDTO();
		}
		
		
		if(result.getKey()==null )
		{
			return "quickregister/loginForm";
		}
		else
		{
			if(result.getPasswordType().equals(CUST_PASSWORD_TYPE_DEFAULT))
			{
				model.addAttribute("loginDetails", result);
				return "quickregister/forcePasswordChange";
			}
			else
			{
				ModelAndView modelAndView=customerQuickRegisterService
						.populateCompleteRegisterRedirect(result);
				
				if(result.getKey().getCustomerType().equals(ENTITY_TYPE_CUSTOMER))
				{	
					model.addAttribute("customerDetails", modelAndView.getModel().get("customerDetails"));
					//TODO cleanup
					model.addAttribute("mobileVerificationDetailsPrimary", modelAndView.getModel().get("mobileVerificationDetailsPrimary"));
					model.addAttribute("emailVerificationDetails", modelAndView.getModel().get("emailVerificationDetails"));
					model.addAttribute("mobileVerificationDetailsSeconadry", modelAndView.getModel().get("mobileVerificationDetailsSeconadry"));
					
				}
				else
				{
					model.addAttribute("vendorDetails", modelAndView.getModel().get("vendorDetails"));
					//TODO cleanup					
					model.addAttribute("mobileVerificationDetailsPrimary", modelAndView.getModel().get("mobileVerificationDetailsPrimary"));
					model.addAttribute("emailVerificationDetails", modelAndView.getModel().get("emailVerificationDetails"));
				}
				model.addAttribute("documentDetails", modelAndView.getModel().get("documentDetails"));
				
				return modelAndView.getViewName();
			}
		}
		
		
	}
	
	@RequestMapping(value="/emailPasswordVerification/{customerId}/{customerType}/{emailPassword}",method=RequestMethod.GET)
	public String verifyLoginDetailsWithEmailPassword(@PathVariable Long customerId,@PathVariable Integer customerType,@PathVariable String emailPassword,Model model)
	{
		VerifyEmailLoginDetails verifyEmailDTO=new VerifyEmailLoginDetails(customerId,customerType,emailPassword);
		
		AuthenticationDetailsDTO result=null;
		
		try{
			result=customerQuickRegisterService.verifyEmailLoginDetails(verifyEmailDTO);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			result=new AuthenticationDetailsDTO();
		}
		
		
		if(result.getKey()==null)
		{
			model.addAttribute("verificationStatus","Sucess" );
			return "quickregister/loginForm";
		}
		else
		{
			if(result.getPasswordType().equals(CUST_PASSWORD_TYPE_DEFAULT))
			{
				model.addAttribute("loginDetails", result);
				return "quickregister/forcePasswordChange";
			}
			else
			{
				ModelAndView modelAndView=customerQuickRegisterService
						.populateCompleteRegisterRedirect(result);
				
				if(result.getKey().getCustomerType().equals(ENTITY_TYPE_CUSTOMER))
				{	
					model.addAttribute("customerDetails", modelAndView.getModel().get("customerDetails"));
					
					model.addAttribute("mobileVerificationDetailsPrimary", modelAndView.getModel().get("mobileVerificationDetailsPrimary"));
					model.addAttribute("emailVerificationDetails", modelAndView.getModel().get("emailVerificationDetails"));
					model.addAttribute("mobileVerificationDetailsSeconadry", modelAndView.getModel().get("mobileVerificationDetailsSeconadry"));
					//model=customerDetailsService.initialiseShowCustomerDetails(customerId, model);
				}
				else
				{
					model.addAttribute("vendorDetails", modelAndView.getModel().get("vendorDetails"));
					
					model.addAttribute("mobileVerificationDetailsPrimary", modelAndView.getModel().get("mobileVerificationDetailsPrimary"));
					model.addAttribute("emailVerificationDetails", modelAndView.getModel().get("emailVerificationDetails"));
					//model=vendorDetailsService.initialiseShowVendorDetails(result.getKey().getCustomerId(), model);
				}
				model.addAttribute("documentDetails", modelAndView.getModel().get("documentDetails"));
				
				return modelAndView.getViewName();
				
			}
		}	
				
	}
	
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public String updatePassword(@ModelAttribute UpdatePasswordDTO updatePasswordDTO)
	{
		Boolean result=customerQuickRegisterService.updatePassword(updatePasswordDTO);
		
		if(result)
			return "quickregister/loginForm";
		else
			return "quickregister/forcePasswordChange";
		
	}
	
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	@ResponseBody
	public String resetPassword(@ModelAttribute CustomerIdTypeEmailOrMobileOptionUpdatedBy customerIdDTO)
	{
		Boolean result=customerQuickRegisterService.resetPassword(customerIdDTO.getCustomerId(),
				customerIdDTO.getCustomerType(),customerIdDTO.getEmailOrMobile(),customerIdDTO.getUpdatedBy());
		
		if(result)
			return "sucess";
		else
			return "failue";
	}
	
	@RequestMapping(value="/forgotPassword")
	public String forgotPassword()
	{
		return "quickregister/forgotPasswordForm";
	}
	
	@RequestMapping(value="/resetPasswordRedirect",method=RequestMethod.POST)
	public String resetPasswordRedirect(@ModelAttribute ResetPasswordRedirectDTO resetPasswordRedirectDTO,Model model)
	{
		
		ForgetPasswordEntity fetchedResult=customerQuickRegisterService
				.resetPasswordRedirect(resetPasswordRedirectDTO.getEntity(),resetPasswordRedirectDTO.getRequestedBy());
		
		QuickRegisterDTO quickRegisterDTO=new QuickRegisterDTO(fetchedResult.getCustomerId(), null, null, 
				fetchedResult.getEmail(), fetchedResult.getMobile(), null,
				fetchedResult.getCustomerType(), fetchedResult.getIsEmailVerified(), fetchedResult.getIsMobileVerified(),
				null, new Date(), null);
		
		if(fetchedResult.getCustomerId()!=null)
		{
			
			if(fetchedResult.getIsPasswordSent())
			{
				return "quickregister/loginForm";
			}
			else
			{
				model.addAttribute("customerQuickRegisterDTO", quickRegisterDTO);
			
				//customerQuickRegisterDTO.setQuickRegisterDTO(quickRegisterDTO);			
					
				return "quickregister/alreadyRegistered";
			}
			
		}
		else
		{
			model.addAttribute("message", "No matching Registration Found");
			return "quickregister/forgotPasswordForm";
		}
		
	}

	@RequestMapping(value="/getTestData",method=RequestMethod.GET)
	public String getTestData(Model model)
	{
		model.addAttribute("list", customerQuickRegisterService.getTestData());
		
		return "showTestData";
	}

}
