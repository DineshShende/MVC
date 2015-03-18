package com.projectx.mvc.servicehandler.completeregister;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.projectx.mvc.exception.repository.completeregister.DocumentDetailsNotFoundException;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.completeregister.DocumentDetailsService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.completeregister.UpdateDocumentDTO;
import com.projectx.rest.domain.completeregister.UpdateDocumentVerificationStatusAndRemarkDTO;

@Component
@Profile(value={"Dev","Prod"})
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
		
		HttpEntity<DocumentKey> entity=new HttpEntity<DocumentKey>(documentKey);
		
		ResponseEntity<DocumentDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/document/getCustomerDocumentById",
					HttpMethod.POST, entity, DocumentDetails.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new DocumentDetailsNotFoundException();
		
		
	}

	@Override
	public DocumentDetails updateDocument(UpdateDocumentDTO updateDocumentDTO) {

		HttpEntity<UpdateDocumentDTO> entity=new HttpEntity<UpdateDocumentDTO>(updateDocumentDTO);
		
		ResponseEntity<DocumentDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/document/updateDocument",HttpMethod.POST,
					entity, DocumentDetails.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		
		throw new DocumentDetailsNotFoundException();

	}

	@Override
	public DocumentDetails updateDocumentVerificationDetails(
			UpdateDocumentVerificationStatusAndRemarkDTO updateDocumentDTO) {
	
		HttpEntity<UpdateDocumentVerificationStatusAndRemarkDTO> entity=
				new HttpEntity<UpdateDocumentVerificationStatusAndRemarkDTO>(updateDocumentDTO);
		
		ResponseEntity<DocumentDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("rest.host")+"/document/updateVerificationStatusAndRemark",HttpMethod.POST,
					entity, DocumentDetails.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		
		throw new DocumentDetailsNotFoundException();

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
