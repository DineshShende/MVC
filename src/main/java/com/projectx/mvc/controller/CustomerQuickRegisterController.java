package com.projectx.mvc.controller;

import static com.projectx.mvc.fixture.CustomerQuickRegisterDataConstants.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.projectx.mvc.domain.CustomerDocumetDTO;
import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.domain.CustomerQuickRegisterMVCDTO;
import com.projectx.mvc.domain.LoginDetailsDTO;
import com.projectx.mvc.domain.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.UpdatePasswordDTO;
import com.projectx.mvc.services.CustomerQuickRegisterService;
import com.projectx.rest.domain.CustomerAuthenticationDetailsDTO;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerIdEmailDTO;
import com.projectx.rest.domain.CustomerIdMobileDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.CustomerQuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.CustomerQuickRegisterStringStatusDTO;
import com.projectx.rest.domain.LoginVerificationDTO;
import com.projectx.rest.domain.VerifyEmailDTO;
import com.projectx.rest.domain.VerifyEmailLoginDetails;
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

		CustomerQuickRegisterStringStatusDTO status=customerQuickRegisterService.checkIfAlreadyExist(customerQuickRegisterEntity);
		
		if(status.getStatus().equals(REGISTER_NOT_REGISTERED))
		{
			CustomerQuickRegisterSavedEntityDTO cutomerQuickRegisterNewDTO=customerQuickRegisterService.addNewCustomer(customerQuickRegisterEntity);
			
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(cutomerQuickRegisterNewDTO.getCustomer());
			
			return "verifyEmailMobile";
			
		}
		else
		{
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(status.getCustomer());			
							
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
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(verifyMobile.getCustomerId())));;
			return "loginForm";
		}	
		else
		{	
			model.addAttribute("mobileVerificationStatus", "Mobile Verification Failed");
			return "verifyEmailMobile";
		}
		
	}
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public String resendMobilePin(@ModelAttribute CustomerIdMobileDTO mobileDTO,Model model)
	{
		Boolean result=customerQuickRegisterService.reSendMobilePin(mobileDTO);
		
		if(result)
		{	
			model.addAttribute("mobileVerificationStatus", "Mobile Pin is sent.Please Enter that code");
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(mobileDTO.getCustomerId())));;
			
		}	
		else
		{
			model.addAttribute("mobileVerificationStatus", "Error will sending Pin.Please Try again");
			
		}
		return "verifyEmailMobile";
	}
	
	
	@RequestMapping(value="/verifyEmailHash/{customerId}/{email}/{emailHash}",method=RequestMethod.GET)
	public String verifyEmailHash(@PathVariable Long customerId,@PathVariable String email,@PathVariable String emailHash,Model model)
	{
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(customerId,email, emailHash);
		
		Boolean result=customerQuickRegisterService.verifyEmail(verifyEmailDTO);
		
		if(result)
		{
			model.addAttribute("emailVerificationStatus", "Email Verification Sucess");
			customerQuickRegisterDTO.toCustomerQuickRegisterMVC(customerQuickRegisterService.getByCustomerId(new CustomerIdDTO(verifyEmailDTO.getCustomerId())));;
			return "loginForm";
		}
		else
		{	
			model.addAttribute("emailVerificationStatus", "Email Verification Failed");
			return "verifyEmailMobile";
		}	
				
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public String resendEmailHash(@ModelAttribute CustomerIdEmailDTO mobileDTO,Model model)
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
	
	
	@RequestMapping(value="/loginForm")
	public String loginForm()
	{
		return "loginForm";
	}
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public String verifyLoginDetails(@ModelAttribute LoginDetailsDTO loginDetailsDTO,Model model)
	{
		LoginVerificationDTO loginVerificationDTO=new LoginVerificationDTO(loginDetailsDTO.getEntity(),loginDetailsDTO.getPassword());
		
		CustomerAuthenticationDetailsDTO result=customerQuickRegisterService.verifyLoginDetails(loginVerificationDTO);
		
		if(result.getCustomerId()==null)
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
				model.addAttribute("loginDetails", result);
				return "loginSucess";
			}
		}
		
		
	}
	
	@RequestMapping(value="/emailPasswordVerification/{customerId}/{emailPassword}",method=RequestMethod.GET)
	public String verifyLoginDetailsWithEmailPassword(@PathVariable Long customerId,@PathVariable String emailPassword,Model model)
	{
		VerifyEmailLoginDetails verifyEmailDTO=new VerifyEmailLoginDetails(customerId,emailPassword);
		
		//System.out.println(verifyEmailDTO);
		
		CustomerAuthenticationDetailsDTO result=customerQuickRegisterService.verifyEmailLoginDetails(verifyEmailDTO);
		
		if(result.getCustomerId()==null)
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
				model.addAttribute("loginDetails", result);
				return "loginSucess";
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
	public String resetPassword(@ModelAttribute CustomerIdDTO customerIdDTO)
	{
		Boolean result=customerQuickRegisterService.resetPassword(customerIdDTO.getCustomerId());
		
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
		
		CustomerQuickRegisterDTO fetchedResult=customerQuickRegisterService.resetPasswordRedirect(resetPasswordRedirectDTO.getEntity());
		
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
       // response.setContentType(image.getContentType());
       // response.setContentLength(image.getContent().length);

       response.getOutputStream().write(image.getImage());

	}
	
	@ModelAttribute("customerQuickRegisterDTO")
	private CustomerQuickRegisterMVCDTO getcustomerQuickRegisterDTO()
	{
		return customerQuickRegisterDTO;
	}
	
	
	
}
