package com.projectx.mvc.controller.quickregister;

import static com.projectx.mvc.fixture.quickregister.QuickRegisterDataConstants.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.domain.quickregister.QuickRegisterMVCDTO;
import com.projectx.mvc.domain.quickregister.LoginDetailsDTO;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.mvc.util.validator.QuickRegisterEntityValidator;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStringStatusDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailLoginDetails;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

@Controller
@RequestMapping(value = "/quickregister")
public class QuickRegisterController {

	@Autowired
	QuickRegisterMVCDTO customerQuickRegisterDTO;
		
	@Autowired
	QuickRegisterService customerQuickRegisterService;


	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
    //@Qualifier("customerQuickRegisterValidator")
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
		
		return "customerQuickRegister";
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
			return "customerQuickRegister";
		}

		QuickRegisterStringStatusDTO status=customerQuickRegisterService.checkIfAlreadyExist(customerQuickRegisterEntity);
		
		if(status.getStatus().equals(REGISTER_NOT_REGISTERED))
		{
			QuickRegisterSavedEntityDTO cutomerQuickRegisterNewDTO=customerQuickRegisterService.addNewCustomer(customerQuickRegisterEntity);
			
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(cutomerQuickRegisterNewDTO.getCustomer());
			
			return "verifyEmailMobile";
			
		}
		else
		{
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(status.getCustomer());	
			
			model.addAttribute("customerQuickRegisterDTO", status.getCustomer());
							
			String message=customerQuickRegisterService.populateMessageForDuplicationField(status.getStatus());
			model.addAttribute("message", message);
			
			return "alreadyRegistered";
		}
		
		
			
	}
	
	
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public String verifyMobilePin(@ModelAttribute VerifyMobileDTO verifyMobile,Model model)
	{
		
		System.out.println(verifyMobile);
		
		Boolean result=customerQuickRegisterService.verifyMobile(verifyMobile);
		
		//System.out.println(result);
		
		if(result)
		{
			model.addAttribute("mobileVerificationStatus", "Mobile Verification Sucess");
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService.getByCustomerIdType(new CustomerIdTypeDTO(verifyMobile.getCustomerId(),verifyMobile.getCustomerType())));;
			return "loginForm";
		}	
		else
		{	
			model.addAttribute("mobileVerificationStatus", "Mobile Verification Failed");
			return "verifyEmailMobile";
		}
		
	}
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public String resendMobilePin(@ModelAttribute CustomerIdTypeMobileTypeDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.reSendMobilePin(mobileDTO);
		
		if(result)
		{	
			model.addAttribute("mobileVerificationStatus", "Mobile Pin is sent.Please Enter that code");
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService.getByCustomerIdType(new CustomerIdTypeDTO(mobileDTO.getCustomerId(),mobileDTO.getCustomerType())));;
			
		}	
		else
		{
			model.addAttribute("mobileVerificationStatus", "Error will sending Pin.Please Try again");
			
		}
		return "verifyEmailMobile";
	}
	
	
	@RequestMapping(value="/verifyEmailHash/{customerId}/{customerType}/{emailType}/{emailHash}",method=RequestMethod.GET)
	public String verifyEmailHash(@PathVariable Long customerId,@PathVariable Integer customerType, @PathVariable Integer emailType,@PathVariable String emailHash,Model model)
	{
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(customerId,customerType,emailType, emailHash);
		
		Boolean result=customerQuickRegisterService.verifyEmail(verifyEmailDTO);
		
		QuickRegisterDTO quickRegisterDTO=customerQuickRegisterService.getByCustomerIdType(new CustomerIdTypeDTO(customerId, customerType));
		
		if(quickRegisterDTO.getCustomerId()!=null)
		{
			if(result)
			{
				model.addAttribute("emailVerificationStatus", "Email Verification Sucess");
				customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService
						.getByCustomerIdType(new CustomerIdTypeDTO(verifyEmailDTO.getCustomerId(),verifyEmailDTO.getCustomerType())));
				
				return "loginForm";
			}
			else
			{	
				model.addAttribute("emailVerificationStatus", "Email Verification Failed");
				return "customerQuickRegister";
			}
		}
		else if(customerType.equals(ENTITY_TYPE_CUSTOMER))
		{
			CustomerDetailsDTO updatedCustomerDetailsDTO=customerDetailsService.getCustomerDetailsById(customerId);
			
			model.addAttribute("customerDetails", updatedCustomerDetailsDTO);
			
			if(result)
			{
				model.addAttribute("emailVerificationStatus", "sucess");
							
			}
			else
			{
				model.addAttribute("emailVerificationStatus", "failure");
			}
			
			model=customerDetailsService.initialiseShowCustomerDetails(customerId, model);
			
			return "showCustomerDetails";
			
		}
		else if(customerType.equals(ENTITY_TYPE_VENDOR))
		{
			VendorDetailsDTO updatedVendorDetailsDTO=vendorDetailsService.getCustomerDetailsById(customerId);
			
			model.addAttribute("vendorDetails", updatedVendorDetailsDTO);
			
			if(result)
			{
				model.addAttribute("emailVerificationStatus", "sucess");
							
			}
			else
			{
				model.addAttribute("emailVerificationStatus", "failure");
			}
			model=vendorDetailsService.initialiseShowVendorDetails(customerId, model);
			return "showVendorDetails";
		}
			
		return "loginForm";		
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public String resendEmailHash(@ModelAttribute CustomerIdTypeEmailTypeDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.reSendEmailHash(mobileDTO);
		
		if(result)
		{
			model.addAttribute("emailVerificationStatus", "Verification Email Sent");
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService
					.getByCustomerIdType(new CustomerIdTypeDTO(mobileDTO.getCustomerId(),mobileDTO.getCustomerType())));;
		}
		else
			
			model.addAttribute("emailVerificationStatus", "Error will sending Email.Please Try again");
		
		return "verifyEmailMobile";
	}
	
	
	@RequestMapping(value="/loginForm")
	public String loginForm()
	{
		return "loginForm";
	}
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public String verifyLoginDetails(@ModelAttribute LoginDetailsDTO loginDetailsDTO,Model model)
	{
		LoginVerificationDTO loginVerificationDTO=new LoginVerificationDTO(loginDetailsDTO.getEntity(),loginDetailsDTO.getPassword());
		
		AuthenticationDetailsDTO result=customerQuickRegisterService.verifyLoginDetails(loginVerificationDTO);
		
	//	System.out.println(result);
		
		if(result.getKey()==null || (result.getKey()!=null && result.getKey().getCustomerId()==null))
		{
			model.addAttribute("verificationStatus","Sucess" );
			return "loginForm";
		}
		else
		{
			if(result.getPasswordType().equals(CUST_PASSWORD_TYPE_DEFAULT))
			{
				model.addAttribute("loginDetails", result);
				return "forcePasswordChange";
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
					
				}
				else
				{
					model.addAttribute("vendorDetails", modelAndView.getModel().get("vendorDetails"));
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
		
		//System.out.println(verifyEmailDTO);
		
		AuthenticationDetailsDTO result=customerQuickRegisterService.verifyEmailLoginDetails(verifyEmailDTO);
		
		if(result.getKey()!=null && result.getKey().getCustomerId()==null)
		{
			model.addAttribute("verificationStatus","Sucess" );
			return "loginForm";
		}
		else
		{
			if(result.getPasswordType().equals(CUST_PASSWORD_TYPE_DEFAULT))
			{
				model.addAttribute("loginDetails", result);
				return "forcePasswordChange";
			}
			else
			{
				ModelAndView modelAndView=customerQuickRegisterService
						.populateCompleteRegisterRedirect(result);
				
				if(result.getKey().getCustomerType().equals(ENTITY_TYPE_CUSTOMER))
				{	
					model.addAttribute("customerDetails", modelAndView.getModel().get("customerDetails"));
					model=customerDetailsService.initialiseShowCustomerDetails(customerId, model);
				}
				else
				{
					model.addAttribute("vendorDetails", modelAndView.getModel().get("vendorDetails"));
					model=vendorDetailsService.initialiseShowVendorDetails(result.getKey().getCustomerId(), model);
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
			return "loginForm";
		else
			return "forcePasswordChange";
		
	}
	
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	@ResponseBody
	public String resetPassword(@ModelAttribute CustomerIdTypeDTO customerIdDTO)
	{
		Boolean result=customerQuickRegisterService.resetPassword(customerIdDTO.getCustomerId(),customerIdDTO.getCustomerType());
		
		if(result)
			return "sucess";
		else
			return "failue";
	}
	
	@RequestMapping(value="/forgotPassword")
	public String forgotPassword()
	{
		return "forgotPasswordForm";
	}
	
	@RequestMapping(value="/resetPasswordRedirect",method=RequestMethod.POST)
	public String resetPasswordRedirect(@ModelAttribute ResetPasswordRedirectDTO resetPasswordRedirectDTO,Model model)
	{
		
		QuickRegisterDTO fetchedResult=customerQuickRegisterService.resetPasswordRedirect(resetPasswordRedirectDTO.getEntity());
		
		if(fetchedResult.getCustomerId()!=null)
		{
			System.out.println(fetchedResult);
			
			System.out.println(customerQuickRegisterDTO);
			
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(fetchedResult);			
					
			return "alreadyRegistered";
			
		}
		else
		{
			model.addAttribute("message", "No matching Registration Found");
			return "forgotPasswordForm";
		}
		
	}

	@RequestMapping(value="/cleartestdata")
	public void clearTestData()
	{
		customerQuickRegisterService.clearTestData();
		
	}
	
	@RequestMapping(value="/upLoadFileForm")
	public String uploadFileForm()
	{
		return "fileUpload";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) {
 
		CustomerDocumetDTO customerDocumetDTO=new CustomerDocumetDTO();
		
		
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                
                
                
                System.out.println(file.getContentType());
                
                customerDocumetDTO.setCustomerId(212L);
                customerDocumetDTO.setImage(bytes);

                customerQuickRegisterService.saveCustomerDocumet(customerDocumetDTO);
                
                /*
                System.out.println(bytes);
                
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                System.out.println(rootPath);
                
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                System.out.println("You successfully uploaded file=" + name);*/
                return "viewUploadedFile";
                
            } catch (Exception e) {
                //System.out.println("You failed to upload " + name + " => " + e.getMessage());
                
                return "failure";
            }
        } else {
            //System.out.println("You failed to upload " + name+ " because the file was empty."); 
            return "failure";
        }
    }
 
	/*
	@RequestMapping(value="/getImage/{imageId}")
	public void getImage(@PathVariable String imageId,HttpServletResponse response) throws IOException
	{
	
		if (imageId == null) {
        
        	response.sendError(HttpServletResponse.SC_NOT_FOUND); 
            return;
        }

        CustomerDocumetDTO image = customerQuickRegisterService.getCustomerDocumetById(Long.parseLong(imageId)) ;

        
        byte[] bytes=image.getImage();
        
        
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream("/home/dinesh/Upload/upload.pdf"));
        stream.write(bytes);
        stream.close();

        
        
        if (image == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }


        response.reset();
        response.setContentType(image.getContentType());
       

       response.getOutputStream().write(image.getImage());

	}
	
	*/
	
	
	@ModelAttribute("customerQuickRegisterDTO")
	private QuickRegisterMVCDTO getcustomerQuickRegisterDTO()
	{
		return customerQuickRegisterDTO;
	}
	
	
	
}
