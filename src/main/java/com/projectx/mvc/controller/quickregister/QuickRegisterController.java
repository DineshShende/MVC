package com.projectx.mvc.controller.quickregister;

import static com.projectx.mvc.fixture.quickregister.QuickRegisterDataConstants.CUST_PASSWORD_TYPE_DEFAULT;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.projectx.mvc.domain.completeregister.ResponseDTO;
import com.projectx.mvc.domain.quickregister.LoginDetailsDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.QuickRegisterMVCDTO;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.mvc.util.validator.QuickRegisterEntityValidator;
import com.projectx.rest.domain.ang.CustomerIdTypeEmailOrMobileOptionUpdatedByAng;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailOrMobileOptionUpdatedBy;
import com.projectx.rest.domain.quickregister.ForgetPasswordEntity;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailLoginDetails;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;


@RestController
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
	
	
	
	@RequestMapping(value="/session",method=RequestMethod.GET)
	public AuthenticationDetailsDTO authenticationDetailsDTO(HttpSession httpSession)
	{
		AuthenticationDetailsDTO detailsDTO=(AuthenticationDetailsDTO)httpSession.getAttribute("currentUser");
	
		return detailsDTO;
	}
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<QuickRegisterSavedEntityDTO> AddNewCustomer(			
			  @RequestBody QuickRegisterEntity customerQuickRegisterEntity,
				BindingResult result,Model model) throws Exception {
		
		if(customerQuickRegisterEntity.getEmail().equals(""))
			customerQuickRegisterEntity.setEmail(null);
	
		try{
			QuickRegisterSavedEntityDTO cutomerQuickRegisterNewDTO=customerQuickRegisterService.addNewCustomer(customerQuickRegisterEntity);
			
			return new ResponseEntity<QuickRegisterSavedEntityDTO>(cutomerQuickRegisterNewDTO, HttpStatus.CREATED);
		}catch(ValidationFailedException | QuickRegisterDetailsAlreadyPresentException | ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		}
		
	
	
	}
	
		
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public ResponseEntity<Boolean> verifyMobilePin(@RequestBody VerifyMobileDTO verifyMobile,Model model)
	{
		
		Boolean result=false;
		try{
			result=customerQuickRegisterService.verifyMobile(verifyMobile);
		}catch(ResourceNotFoundException e)
		{
			result=false;
		}
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value="/verifyEmailHash/{customerId}/{customerType}/{emailType}/{updatedBy}/{updatedById}/{emailHash}",method=RequestMethod.GET)
	public String verifyEmailHash(@PathVariable Long customerId,@PathVariable Integer customerType, @PathVariable Integer emailType,@PathVariable String emailHash
			,@PathVariable Integer updatedBy,@PathVariable Long updatedById,Model model)
	{
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(customerId,customerType,emailType, emailHash,updatedBy,updatedById);
		
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
		
		//TODO how to return view name for api call
		
	}	
	
	
	@RequestMapping(value="/sendMobilePin",method=RequestMethod.POST)
	public Boolean sendMobilePin(@RequestBody CustomerIdTypeMobileTypeRequestedByDTO mobileDTO,Model model)
	{
	//	ResponseDTO responseDTO=null;new ResponseDTO(status, errorMessage);
		
		Boolean result=customerQuickRegisterService.sendMobilePin(mobileDTO);
		
	//	if(result)
	//		r
		
		return result;
	}
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public Boolean resendMobilePin(@RequestBody CustomerIdTypeMobileTypeRequestedByDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.reSendMobilePin(mobileDTO);
		
		return result;
	}
	
	
	@RequestMapping(value="/sendEmailHash",method=RequestMethod.POST)
	public Boolean sendEmailHash(@RequestBody CustomerIdTypeEmailTypeUpdatedByDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.sendEmailHash(mobileDTO);
		
		return result;
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public Boolean resendEmailHash(@RequestBody CustomerIdTypeEmailTypeUpdatedByDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.reSendEmailHash(mobileDTO);
		
		return result;
	}
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationDetailsDTO> verifyLoginDetails(@RequestBody LoginDetailsDTO loginDetailsDTO,HttpSession httpSession)
	{
		LoginVerificationDTO loginVerificationDTO=new LoginVerificationDTO(loginDetailsDTO.getEntity(),loginDetailsDTO.getPassword());
		
		
		
		AuthenticationDetailsDTO result=null;
		
		try{
			
			result=customerQuickRegisterService.verifyLoginDetails(loginVerificationDTO);
			
			httpSession.setAttribute("currentUser", result);
			
			return new ResponseEntity<AuthenticationDetailsDTO>(result, HttpStatus.OK);
			
		}catch(AuthenticationDetailsNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	
	
	@RequestMapping(value="/emailPasswordVerification/{customerId}/{customerType}/{emailPassword}",method=RequestMethod.GET)
	public String verifyLoginDetailsWithEmailPassword(@PathVariable Long customerId,@PathVariable Integer customerType,@PathVariable String emailPassword,Model model)
	{
		VerifyEmailLoginDetails verifyEmailDTO=new VerifyEmailLoginDetails(customerId,customerType,emailPassword);
		
		AuthenticationDetailsDTO result=null;
		
		try{
			result=customerQuickRegisterService.verifyEmailLoginDetails(verifyEmailDTO);
			return "sucess";
		}catch(AuthenticationDetailsNotFoundException e)
		{
			result=new AuthenticationDetailsDTO();
			return "failure";
		}
		
		/*
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
				ModelAndView modelAndView=null;//customerQuickRegisterService
				//		.populateCompleteRegisterRedirect(result);
				
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
		*/		
	}
	
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO)
	{
		
		//TODO change need to negotiate double forceful password update
		Boolean result=customerQuickRegisterService.updatePassword(updatePasswordDTO);
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public ResponseEntity<Boolean> resetPassword(@RequestBody CustomerIdTypeEmailOrMobileOptionUpdatedByAng customerIdDTO)
	{
		Boolean result=customerQuickRegisterService.resetPassword(customerIdDTO.getCustomerId(),
				customerIdDTO.getCustomerType(),customerIdDTO.getEmailOrMobile(),customerIdDTO.getRequestedBy(),
				customerIdDTO.getRequestedById());
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/resetPasswordRedirect",method=RequestMethod.POST)
	public ResponseEntity<QuickRegisterDTO> resetPasswordRedirect(@RequestBody ResetPasswordRedirectDTO resetPasswordRedirectDTO,Model model)
	{
		
		ForgetPasswordEntity fetchedResult=customerQuickRegisterService
				.resetPasswordRedirect(resetPasswordRedirectDTO.getEntity(),resetPasswordRedirectDTO.getRequestedBy(),
						resetPasswordRedirectDTO.getRequestedById());
		
		QuickRegisterDTO quickRegisterDTO=new QuickRegisterDTO(fetchedResult.getCustomerId(), null, null, 
				fetchedResult.getEmail(), fetchedResult.getMobile(), null,
				fetchedResult.getCustomerType(), fetchedResult.getIsEmailVerified(), fetchedResult.getIsMobileVerified(),
				null, new Date(), null,null,null,null);
		
		if(fetchedResult.getCustomerId()!=null)
		{
			
			return new ResponseEntity<QuickRegisterDTO>(quickRegisterDTO, HttpStatus.OK);
			
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}

	@RequestMapping(value="/getTestData",method=RequestMethod.GET)
	public String getTestData(Model model)
	{
		model.addAttribute("list", customerQuickRegisterService.getTestData());
		
		return "showTestData";
	}


}

