package com.projectx.mvc.controller.completeregister;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;

import com.projectx.async.domain.quickregister.EmailDTO;
import com.projectx.mvc.domain.quickregister.CustomerDocumetDTO;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.completeregister.UpdateDocumentDTO;
import com.projectx.rest.domain.completeregister.UpdateDocumentVerificationStatusAndRemarkDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;

@Controller
@RequestMapping(value = "/document")
public class DocumentDetailsController {
	
	@Autowired
	DocumentDetailsService documentDetailsService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	private Integer ENTITY_TYPE_VENDOR=2;
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@RequestParam("customerId") Long customerId,@RequestParam("customerType") Integer customerType,
			@RequestParam("documentName") String documentName,
            @RequestParam("file") MultipartFile file,Model model)
	{
		
		if (!file.isEmpty()) {
            try {
                
                DocumentDetails documentDetails=documentDetailsService.initializeDocumentDetails(customerId, customerType, documentName, file);
                
                documentDetails=documentDetailsService.saveDocument(documentDetails);
                
                if(customerType.equals(ENTITY_TYPE_CUSTOMER))
                {
                	
	                CustomerDetailsDTO customerDetails=customerDetailsService.getCustomerDetailsById(customerId);
	                
	                model.addAttribute("documentDetails", documentDetails);
	                model.addAttribute("customerDetails", customerDetails);
	                
	                model.addAttribute("uploadStatus", "You successfully uploaded file");
	                model=customerDetailsService.initialiseShowCustomerDetails(customerId, model);
	                return "completeregister/showCustomerDetails";
                
                }
                else if(customerType.equals(ENTITY_TYPE_VENDOR))
                {
                	VendorDetailsDTO vendorDetails=vendorDetailsService.getCustomerDetailsById(customerId);
                	
   	                model.addAttribute("documentDetails", documentDetails);
   	                model.addAttribute("vendorDetails", vendorDetails);
        	                
  	                model.addAttribute("uploadStatus", "You successfully uploaded file");
  	                model=vendorDetailsService.initialiseShowVendorDetails(customerId, model);
   	                return "completeregister/showVendorDetails";

                	
                }
                
            } catch (Exception e) {

            	model.addAttribute("uploadStatus", "You failed to upload ");
                return "failure";
            }
        } else {

        	model.addAttribute("uploadStatus", "You failed to upload because the file was empty");
            return "failure";
        }

		return "failure";
	}
	
	@RequestMapping(value="/updateDocument",method=RequestMethod.POST)
	public String updateDocument(@RequestParam("customerId") Long customerId,@RequestParam("customerType") Integer customerType,
			@RequestParam("documentName") String documentName,@RequestParam("requestedBy") String requestedBy,
            @RequestParam("file") MultipartFile file,Model model)
	{
		
		if (!file.isEmpty()) {
            try {
                
                DocumentDetails documentDetails=documentDetailsService.initializeDocumentDetails(customerId, customerType, documentName, file);
                
                UpdateDocumentDTO updateDocumentDTO=
                		new UpdateDocumentDTO(documentDetails.getKey(), documentDetails.getDocument(), documentDetails.getContentType(),requestedBy);
                
                documentDetails=documentDetailsService.updateDocument(updateDocumentDTO);
                
                CustomerDetailsDTO customerDetails=customerDetailsService.getCustomerDetailsById(customerId);
                
                model.addAttribute("documentDetails", documentDetails);
                model.addAttribute("customerDetails", customerDetails);
                
                model.addAttribute("updateStatus", "You successfully uploaded file");
                
                model=customerDetailsService.initialiseShowCustomerDetails(customerId, model);
                
                return "completeregister/showCustomerDetails";
                
            } catch (Exception e) {

            	model.addAttribute("updateStatus", "You failed to upload ");
                return "failure";
            }
        } else {

        	model.addAttribute("updateStatus", "You failed to upload because the file was empty");
            return "failure";
        }

		
	}

	
	@RequestMapping(value="/updateVerificationStatusRemark",method=RequestMethod.POST)
	public String updateVerificationStatusRemark(@RequestParam("customerId") Long customerId,@RequestParam("customerType") Integer customerType,
			@RequestParam("documentName") String documentName,@RequestParam("verificationStatus") Integer verificationStatus,
			@RequestParam("verificationRemark") String verificationRemark,@RequestParam("requestedBy") String requestedBy,
            Model model)
	{
		
                DocumentKey key=new DocumentKey(customerId, customerType, documentName);
                        
                UpdateDocumentVerificationStatusAndRemarkDTO dto=
                		new UpdateDocumentVerificationStatusAndRemarkDTO(key, verificationStatus, verificationRemark,requestedBy);
                
               DocumentDetails documentDetails=documentDetailsService.updateDocumentVerificationDetails(dto);
                
                CustomerDetailsDTO customerDetails=customerDetailsService.getCustomerDetailsById(customerId);
                
                model.addAttribute("documentDetails", documentDetails);
                model.addAttribute("customerDetails", customerDetails);
                
                model.addAttribute("updateStatus", "You successfully uploaded file");
                model=customerDetailsService.initialiseShowCustomerDetails(customerId, model);
                
                return "completeregister/showCustomerDetails";
                

		
	}
	
	@RequestMapping(value="/{customerId}/{customerType}/{documentName}")
	public void getDocument(@PathVariable Long customerId,@PathVariable Integer customerType,@PathVariable String documentName,
			HttpServletResponse response) throws IOException
	{
		System.out.println("CustomerId:"+customerId+",CustomerType:"+customerType+",Document Name:"+documentName);
	
		if (customerId == null || customerType==null ||documentName==null ) {
        
        	response.sendError(HttpServletResponse.SC_NOT_FOUND); 
            return;
        }

        DocumentDetails image = documentDetailsService.getDocumentById(new DocumentKey(customerId, customerType, documentName));

        System.out.println(image);
        
        byte[] bytes=image.getDocument();
        
        
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream("/home/dinesh/Upload/upload.pdf"));
        stream.write(bytes);
        stream.close();

//        if (image == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
//            return;
//        }

        response.reset();
        response.setContentType(image.getContentType());
       
        response.getOutputStream().write(image.getDocument());

	}
	
	@RequestMapping(value="/viewDocument",method=RequestMethod.GET)
	public String showDocument()
	{
		return "completeregister/showDocument";
	}

	/*
	
	@RequestMapping(value="/testCallable")
	@ResponseBody
	public Callable<String> testCallable()
	{
		System.out.println(Thread.currentThread().getName());
		
		return new Callable<String>() {
			
			@Override
			public String call() throws Exception {

				Thread.sleep(10000);
				System.out.println("After completion");
				System.out.println(Thread.currentThread().getName());
				return "showDocument";
			}
		};
		
	}
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String test() 
	{
		System.out.println(Thread.currentThread().getName());
		
		try {
			Thread.sleep(10000);
			System.out.println(Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "showDocument";
	}
	
	@RequestMapping(value="/testDeferredResult")
	public DeferredResult<String> testDeferredResult()
	{
		DeferredResult<String> deferredResult=new DeferredResult<String>();
		
		
		
		return deferredResult;
	}
	
	@RequestMapping("/custom-timeout-handling")
	public @ResponseBody WebAsyncTask<String> callableWithCustomTimeoutHandling() {

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(4000);
				return "Callable result";
			}
		};

		WebAsyncTask<String> webAsyncTask=new WebAsyncTask<String>(6000, callable);
		
		webAsyncTask.onCompletion(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Completed Execution!!");
			}
		});
		
		return webAsyncTask;
	}
	
	@RequestMapping(value="/async/{email}",method=RequestMethod.GET)
	@ResponseBody
	public String asyncCall(@PathVariable("email") String email)
	{
		RestTemplate restTemplate=new RestTemplate();
		
		EmailDTO emailDTO=new EmailDTO(212L, email, "Hi There");
		
		String response=restTemplate.postForObject("http://localhost:9060/sendVerificationDetails/sendEmail", emailDTO, String.class);
		
		return response;
	}
	*/
	

}
/*
System.out.println(file.getContentType());

customerDocumetDTO.setCustomerId(212L);
customerDocumetDTO.setImage(bytes);

customerQuickRegisterService.saveCustomerDocumet(customerDocumetDTO);
*/

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
