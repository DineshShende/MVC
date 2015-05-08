package com.projectx.mvc.controller.quickregister;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.commn.ResponseDTO;
import com.projectx.mvc.domain.quickregister.ForgetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.LoginDetailsDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.QuickRegisterMVCDTO;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.completeregister.ResourceNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.UpdatePasswordFailedException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.mvc.exception.repository.quickregister.PasswordRestFailedException;
import com.projectx.mvc.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.mvc.util.validator.QuickRegisterEntityValidator;
import com.projectx.rest.domain.ang.CustomerIdTypeEmailOrMobileOptionUpdatedByAng;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.ForgetPasswordEntity;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.SendResendResetEmailHashDTO;
import com.projectx.rest.domain.quickregister.SendResendResetMobilePinDTO;
import com.projectx.rest.domain.quickregister.SendResendResetPasswordDTO;
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
			return new ResponseEntity<QuickRegisterSavedEntityDTO>(new QuickRegisterSavedEntityDTO(e.getMessage(), null),HttpStatus.OK); 
		}
		
	
	
	}
	
		
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> verifyMobilePin(@RequestBody VerifyMobileDTO verifyMobile,Model model)
	{
		
		Boolean result=false;
		try{
			result=customerQuickRegisterService.verifyMobile(verifyMobile);
			
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,""), HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,e.getMessage()), HttpStatus.OK);
		}
		
		
		
			
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
	
	
	@RequestMapping(value="/sendOrResendOrResetMobilePin",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> sendMobilePin(@RequestBody SendResendResetMobilePinDTO sendResendResetMobilePinDTO,Model model)
	{
		
		Boolean result=false;
		try{
			result=customerQuickRegisterService.sendOrResendOrResetMobilePin(sendResendResetMobilePinDTO);
		
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result, ""), HttpStatus.OK);
					
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result, e.getMessage()), HttpStatus.OK);
		}
		
		
	}
	
	
	
	@RequestMapping(value="/sendOrResendOrResetEmailHash",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> sendEmailHash(@RequestBody SendResendResetEmailHashDTO sendResendResetEmailHashDTO,Model model)
	{
		
		Boolean result=false;
		
		try{
			result=customerQuickRegisterService.sendOrResendOrResetEmailHash(sendResendResetEmailHashDTO);
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result, ""), HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result, e.getMessage()), HttpStatus.OK);
		}
		
	}
	
	
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<AuthenticationDetailsDTO>> verifyLoginDetails(@RequestBody LoginDetailsDTO loginDetailsDTO,HttpSession httpSession)
	{
		LoginVerificationDTO loginVerificationDTO=new LoginVerificationDTO(loginDetailsDTO.getEntity(),loginDetailsDTO.getPassword());
		
		
		
		AuthenticationDetailsDTO result=null;
		
		try{
			
			result=customerQuickRegisterService.verifyLoginDetails(loginVerificationDTO);
			
			httpSession.setAttribute("currentUser", result);
			
			return new ResponseEntity<ResponseDTO<AuthenticationDetailsDTO>>(new ResponseDTO<AuthenticationDetailsDTO>(result,""), HttpStatus.OK);
			
		}catch(AuthenticationDetailsNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<AuthenticationDetailsDTO>>(new ResponseDTO<AuthenticationDetailsDTO>(result,e.getMessage()), HttpStatus.OK);
		}
		
	}
	
	
	
	
	@RequestMapping(value="/emailPasswordVerification/{customerId}/{customerType}/{emailPassword}",method=RequestMethod.GET)
	public ResponseEntity<ResponseDTO<String>> verifyLoginDetailsWithEmailPassword(@PathVariable Long customerId,@PathVariable Integer customerType,@PathVariable String emailPassword,Model model)
	{
		VerifyEmailLoginDetails verifyEmailDTO=new VerifyEmailLoginDetails(customerId,customerType,emailPassword);
		
		AuthenticationDetailsDTO result=null;
		
		try{
			result=customerQuickRegisterService.verifyEmailLoginDetails(verifyEmailDTO);
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("sucess", ""), HttpStatus.OK);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<String>>(new ResponseDTO<String>("failure", e.getMessage()), HttpStatus.OK);
		}
		
			
	}
	
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO)
	{
		Boolean result=false;
		
		try{
			result=customerQuickRegisterService.updatePassword(updatePasswordDTO);
		
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,""), HttpStatus.OK);
		}catch(UpdatePasswordFailedException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,e.getMessage()), HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/sendOrResendOrResetPassword",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> resetPassword(@RequestBody SendResendResetPasswordDTO customerIdDTO)
	{
		Boolean result=false;
		
		try{
			result=customerQuickRegisterService.sendOrResendOrResetPassword(customerIdDTO.getCustomerId(),customerIdDTO.getCustomerType(),
					customerIdDTO.getEmailOrMobile(),customerIdDTO.getSendOrResendOrResetFlag(),
					customerIdDTO.getRequestedBy(), customerIdDTO.getRequestedById());
		
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,""), HttpStatus.OK);
		}catch(PasswordRestFailedException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(result,e.getMessage()), HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value="/resetPasswordRedirect",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<ForgetPasswordRedirectDTO>> resetPasswordRedirect(@RequestBody ResetPasswordRedirectDTO resetPasswordRedirectDTO,Model model)
	{
		
		try{
		
			ForgetPasswordEntity fetchedResult=customerQuickRegisterService
					.resetPasswordRedirect(resetPasswordRedirectDTO.getEntity(),resetPasswordRedirectDTO.getRequestedBy(),
							resetPasswordRedirectDTO.getRequestedById());
		
			ForgetPasswordRedirectDTO forgetPasswordRedirectDTO=
					new ForgetPasswordRedirectDTO(fetchedResult.getCustomerId(), fetchedResult.getCustomerType(),
							fetchedResult.getIsMobileVerified(), fetchedResult.getIsEmailVerified(), fetchedResult.getIsPasswordSent());
						
			return new ResponseEntity<ResponseDTO<ForgetPasswordRedirectDTO>>(new ResponseDTO<ForgetPasswordRedirectDTO>(forgetPasswordRedirectDTO,""), HttpStatus.OK);
		
		}catch(PasswordRestFailedException e)
		{
			return new ResponseEntity<ResponseDTO<ForgetPasswordRedirectDTO>>(new ResponseDTO<ForgetPasswordRedirectDTO>(null,e.getMessage()), HttpStatus.OK);
		}
		
				
	}



}

