package com.projectx.mvc.fixtures.completeregister;

import java.io.File;
import java.io.FileInputStream;

import com.google.gson.Gson;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;

import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;

public class DocumentDetailsDataFixture {

	
	public static String DOCUMENT_NAME="DrivingLicense";
	public static String DOCUMENT_CONTENT_TYPE="image/jpeg";
	public static String DOCUMENT_CONTENT_TYPE_NEW="application/pdf";
	public static String DOCUMENT_CONTENT_TYPE_DUMMY="text/test";
	
	public static Integer DOCUMENT_VERIFICATION_STATUS=1;
	public static Integer CUST_TYPE_CUSTOMER=1;
	
	public static String DOCUMENT_VERIFICATION_REMARKS="NOT VERIFIED";
	
	public static String DOCUMENT_VERIFICATION_REMARKS_FAILED="L1_VERIFICATION_FAILED";
	
	public static Integer DOCUMENT_VERIFICATION_STATUS_FAILED=2;
 	
	private static Gson gson=new Gson();
	
	public static DocumentKey standardDocumentKey()
	{
		return new DocumentKey(CUST_ID, CUST_TYPE_CUSTOMER, DOCUMENT_NAME);
	}
	
	public static DocumentKey standardDocumentKey(Long customerId)
	{
		return new DocumentKey(customerId, CUST_TYPE_CUSTOMER, DOCUMENT_NAME);
	}
	
	public static byte[] documentDummy()
	{
		return "abcdefghijklmn".getBytes();
	}

	public static byte[] documentDummyNew()
	{
		return "jhsdjheudhdfj".getBytes();
	}
	

	
	public static DocumentDetails standardDocumentDetails()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				standardDocumentKey().getCustomerId(),standardDocumentKey().getCustomerId());
	}
	
	public static DocumentDetails standardDocumentDetails(Long customerId)
	{
		return new DocumentDetails(standardDocumentKey(customerId), documentDummy(), DOCUMENT_CONTENT_TYPE, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,customerId,customerId);
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocument()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE_DUMMY, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				standardDocumentKey().getCustomerId(),standardDocumentKey().getCustomerId());
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocumentNew()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE_DUMMY, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				standardDocumentKey().getCustomerId(),standardDocumentKey().getCustomerId());
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE_DUMMY, DOCUMENT_VERIFICATION_STATUS_FAILED,
				DOCUMENT_VERIFICATION_REMARKS_FAILED, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				standardDocumentKey().getCustomerId(),standardDocumentKey().getCustomerId());
	}
	
	public static DocumentDetails standardDocumentDetailsWithNewDocumentContentType()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummyNew(), DOCUMENT_CONTENT_TYPE_NEW, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				standardDocumentKey().getCustomerId(),standardDocumentKey().getCustomerId());
	}
	
	public static DocumentDetails standardDocumentDetailsWithNewVerificationStatusAndRemark()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE, DOCUMENT_VERIFICATION_STATUS_FAILED,
				DOCUMENT_VERIFICATION_REMARKS_FAILED, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,
				standardDocumentKey().getCustomerId(),standardDocumentKey().getCustomerId());
	}
	
	public static String standardJsonDocumentDetails(DocumentDetails details)
	{
		System.out.println(gson.toJson(details));
		
		return gson.toJson(details); 
	}
	
	public static String standardJsonDocumentKey()
	{
		System.out.println(gson.toJson(standardDocumentKey()));
		
		return gson.toJson(standardDocumentKey());
	}
	
}
