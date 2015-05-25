package com.projectx.mvc.controller.completeregister;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.projectx.mvc.domain.completeregister.CustomerDetailsAngDTO;
import com.projectx.mvc.domain.completeregister.ResponseDTO;
import com.projectx.mvc.services.completeregister.CustomerDetailsService;
import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.completeregister.UpdateDocumentDTO;
import com.projectx.rest.domain.completeregister.UpdateDocumentVerificationStatusAndRemarkDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;

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
	
	/*
	@RequestMapping(value="/",method=RequestMethod.POST)
	public DocumentContainer documentContainer(@RequestBody DocumentContainer documentContainer)
	{
		
	}
	*/
	
	/*
	@RequestMapping(value="/save",method=RequestMethod.POST)//,consumes = {"multipart/form-data"}
	@ResponseBody
	public String save(
	        @RequestParam(value = "file") MultipartFile file)
	{
		
		System.out.println(file.getContentType());
		
		
		//System.out.println(dto.getFile().getContentType());
		//try {
			//System.out.println(dto.getFile().getBytes());
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		return "abc";
	}
	*/
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)//,consumes = {"multipart/form-data"}
	@ResponseBody
	public ResponseDTO save(MultipartHttpServletRequest request,HttpServletResponse response)
	{
		
		MultipartHttpServletRequest mRequest;
        try {
            mRequest = (MultipartHttpServletRequest) request;
           Map<String, String[]> objectMap= mRequest.getParameterMap();
           
           Map<String, MultipartFile> fileMap =mRequest.getFileMap();
           
           for(Object key: fileMap.keySet())
           {
        	   System.out.println(key);
        	   System.out.println(fileMap.get(key));
        	   System.out.println(fileMap.get(key).getName());
        	   System.out.println(fileMap.get(key).getContentType());
        	   System.out.println(fileMap.get(key).getBytes());
           }
           
           
           for(Object keyObj:objectMap.keySet())
           {
        	   
        	   System.out.println("ParamKey"+keyObj);
        	   System.out.println("ParamKeyVal"+objectMap.get(keyObj)[0]);
           }
           
         //  CustomerDetailsAngDTO angDTO=(CustomerDetailsAngDTO)objectMap.get("user");
           
           //
           
           //System.out.println(objectMap.get("user"));

           /*
            Iterator<String> itr = mRequest.getFileNames();
            while (itr.hasNext()) {
                MultipartFile mFile = mRequest.getFile(itr.next());
                String fileName = mFile.getOriginalFilename();
                System.out.println(fileName);
            }
            */
           
           return new ResponseDTO("failure", "SOmething went wrong");
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return new ResponseDTO("sucee", "");
	}
	
	
	@RequestMapping(value="/documentUpload")
	public String documentUpload()
	{
		return "completeregister/documentUpload";
	}
	/*
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
	
	*/
	@RequestMapping(value="/updateDocument",method=RequestMethod.POST)
	public String updateDocument(@RequestParam("customerId") Long customerId,@RequestParam("customerType") Integer customerType,
			@RequestParam("documentName") String documentName,@RequestParam("documentVersion") Integer documentVersion,@RequestParam("requestedBy") String requestedBy,
            @RequestParam("file") MultipartFile file,Model model)
	{
		
		if (!file.isEmpty()) {
            try {
                
                DocumentDetails documentDetails=documentDetailsService.initializeDocumentDetails(customerId, customerType, 
                		documentName,documentVersion, file);
                
                UpdateDocumentDTO updateDocumentDTO=null;
                		//new UpdateDocumentDTO(documentDetails.getKey(), documentDetails.getDocument(), documentDetails.getContentType(),requestedBy);
                
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
			@RequestParam("documentName") String documentName,@RequestParam("documentVersion") Integer documentVersion,
			@RequestParam("verificationStatus") Integer verificationStatus,
			@RequestParam("verificationRemark") String verificationRemark,@RequestParam("requestedBy") Integer requestedBy,
			@RequestParam("requestedById") Long requestedById,Model model)
	{
		
                DocumentKey key=new DocumentKey(customerId, customerType, documentName,documentVersion);
                        
                UpdateDocumentVerificationStatusAndRemarkDTO dto=
                		new UpdateDocumentVerificationStatusAndRemarkDTO(key, verificationStatus, verificationRemark,requestedBy,requestedById);
                
               DocumentDetails documentDetails=documentDetailsService.updateDocumentVerificationDetails(dto);
                
                CustomerDetailsDTO customerDetails=customerDetailsService.getCustomerDetailsById(customerId);
                
                model.addAttribute("documentDetails", documentDetails);
                model.addAttribute("customerDetails", customerDetails);
                
                model.addAttribute("updateStatus", "You successfully uploaded file");
                model=customerDetailsService.initialiseShowCustomerDetails(customerId, model);
                
                return "completeregister/showCustomerDetails";
                

		
	}
	/*
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
