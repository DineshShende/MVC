package com.projectx.mvc.services.completeregister;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.completeregister.UpdateDocumentDTO;
import com.projectx.rest.domain.completeregister.UpdateDocumentVerificationStatusAndRemarkDTO;




public interface DocumentDetailsService {

	DocumentDetails initializeDocumentDetails(Long customerId,Integer customerType,String documentName,Integer documentVersion,MultipartFile file) throws IOException;
	
	DocumentDetails saveDocument(DocumentDetails documentDetails);
	
	DocumentDetails getDocumentById(DocumentKey documentKey);
	
	DocumentDetails updateDocument(UpdateDocumentDTO updateDocumentDTO);
	
	DocumentDetails updateDocumentVerificationDetails(UpdateDocumentVerificationStatusAndRemarkDTO updateDocumentDTO);
	
	Integer count();
	
	Boolean clearTestData();
}
