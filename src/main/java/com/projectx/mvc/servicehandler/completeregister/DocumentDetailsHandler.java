package com.projectx.mvc.servicehandler.completeregister;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.completeregister.UpdateDocumentDTO;
import com.projectx.rest.domain.completeregister.UpdateDocumentVerificationStatusAndRemarkDTO;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class DocumentDetailsHandler implements DocumentDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public DocumentDetails initializeDocumentDetails(Long customerId,
			Integer customerType, String documentName, MultipartFile file)
			throws IOException {


		DocumentKey key=new DocumentKey(customerId, customerType, documentName);
		
		byte[] document = null;
		
			document = file.getBytes();
		
		String contentType=file.getContentType();
		
		
		DocumentDetails  documentDetails=new DocumentDetails(key, document, contentType, 1, "NOT VERIFIED", new Date(), new Date(), "CUST_ONLINE");

		return documentDetails;
	}



	@Override
	public DocumentDetails saveDocument(DocumentDetails documentDetails) {
		
		DocumentDetails status=restTemplate.postForObject(env.getProperty("rest.host")+"/document/saveCustomerDocument", documentDetails, DocumentDetails.class);
		
		return status;
				
	}

	
	@Override
	public DocumentDetails getDocumentById(DocumentKey documentKey) {
		
		DocumentDetails status=restTemplate.postForObject(env.getProperty("rest.host")+"/document/getCustomerDocumentById", documentKey, DocumentDetails.class);
		
		return status;
		
		
	}

	@Override
	public DocumentDetails updateDocument(UpdateDocumentDTO updateDocumentDTO) {
		
		DocumentDetails status=restTemplate.postForObject(env.getProperty("rest.host")+"/document/updateDocument", updateDocumentDTO, DocumentDetails.class);
		
		return status;		

	}

	@Override
	public DocumentDetails updateDocumentVerificationDetails(
			UpdateDocumentVerificationStatusAndRemarkDTO updateDocumentDTO) {
	
		DocumentDetails status=restTemplate.postForObject(env.getProperty("rest.host")+"/document/updateVerificationStatusAndRemark", updateDocumentDTO, DocumentDetails.class);
		
		return status;		

	}

	@Override
	public Integer count() {

		Integer status=restTemplate.getForObject(env.getProperty("rest.host")+"/document/count",Integer.class);
		
		return status;
	}

	@Override
	public Boolean clearTestData() {
		
		Boolean status=restTemplate.getForObject(env.getProperty("rest.host")+"/document/clearTestData",Boolean.class);
		
		return status;
		
	}

}
