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
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)//,consumes = {"multipart/form-data"}
	@ResponseBody
	public ResponseDTO save(MultipartHttpServletRequest request,HttpServletResponse response)
	{
		//TODO Shri: Add your document compression and conversion to PDF code here
		MultipartHttpServletRequest mRequest;
        try {
            mRequest = (MultipartHttpServletRequest) request;
           Map<String, String[]> objectMap= mRequest.getParameterMap();
           
           Map<String, MultipartFile> fileMap =mRequest.getFileMap();
           
           for(Object keyObj:objectMap.keySet())
           {
        	   
        	   System.out.println("ParamKey"+keyObj);
        	   System.out.println("ParamKeyVal"+objectMap.get(keyObj)[0]);
           }
           
           
           for(Object key: fileMap.keySet())
           {
        	   System.out.println(key);
        	   System.out.println(fileMap.get(key));
        	   System.out.println(fileMap.get(key).getName());
        	   System.out.println(fileMap.get(key).getContentType());
        	   System.out.println(fileMap.get(key).getBytes());
           }
           
           
           
           
           
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

}
