package com.projectx.mvc.fixtures.completeregister;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.projectx.rest.domain.completeregister.Document;
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
	
	public static Integer DOCUMENT_VERSION=1;
	
	private static Integer ACTOR_ENTITY_SELF_WEB=1;
	
	public static String DOCUMENT_UID="12323";
	
	public static Integer DOCUMENT_REJECTION_COUNT=0;
 	
	private static Gson gson=new Gson();
	
	public static DocumentKey standardDocumentKey()
	{
		return new DocumentKey(CUST_ID, CUST_TYPE_CUSTOMER, DOCUMENT_NAME,DOCUMENT_VERSION);
	}
	
	public static DocumentKey standardDocumentKey(Long customerId)
	{
		return new DocumentKey(customerId, CUST_TYPE_CUSTOMER, DOCUMENT_NAME,DOCUMENT_VERSION);
	}
	
	public static byte[] documentDummy()
	{
		return "abcdefghijklmn".getBytes();
	}

	public static byte[] documentDummyNew()
	{
		return "jhsdjheudhdfj".getBytes();
	}
	
	
	
	
	public static List<Document> documentList()
	{
		Document document1=new Document(1L, "document1".getBytes());
		Document document2=new Document(1L, "document2A".getBytes());
		
		List<Document> documentList=new ArrayList<Document>();
		documentList.add(document1);
		documentList.add(document2);
		
		return documentList;
		
	}
	
	public static List<Document> documentListNew()
	{
		Document document1=new Document(1L, "documentNew1".getBytes());
		Document document2=new Document(1L, "documentNew2".getBytes());
		
		List<Document> documentList=new ArrayList<Document>();
		documentList.add(document1);
		documentList.add(document2);
		
		return documentList;
		
	}
	
	public static DocumentDetails standardDocumentDetails()
	{
		
		return new DocumentDetails(standardDocumentKey(), documentList(), DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS,DOCUMENT_UID, DOCUMENT_REJECTION_COUNT, DOCUMENT_REJECTION_COUNT, 
				CUST_DATE, CUST_DATE, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB, standardDocumentKey().getCustomerId(),
				standardDocumentKey().getCustomerId());
		
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocument()
	{
		return new DocumentDetails(standardDocumentKey(), documentList(), DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS,DOCUMENT_UID, DOCUMENT_REJECTION_COUNT, DOCUMENT_REJECTION_COUNT, 
				CUST_DATE, CUST_DATE, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB, standardDocumentKey().getCustomerId(),
				standardDocumentKey().getCustomerId());
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocumentWithError()
	{
		return new DocumentDetails(standardDocumentKey(), documentList(), null,
				DOCUMENT_VERIFICATION_REMARKS,DOCUMENT_UID, DOCUMENT_REJECTION_COUNT, DOCUMENT_REJECTION_COUNT, 
				CUST_DATE, CUST_DATE, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB, standardDocumentKey().getCustomerId(),
				standardDocumentKey().getCustomerId());
		
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark()
	{
		return new DocumentDetails(standardDocumentKey(), documentList(), DOCUMENT_VERIFICATION_STATUS_FAILED,
				DOCUMENT_VERIFICATION_REMARKS_FAILED,DOCUMENT_UID, DOCUMENT_REJECTION_COUNT, DOCUMENT_REJECTION_COUNT, 
				CUST_DATE, CUST_DATE, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB, standardDocumentKey().getCustomerId(),
				standardDocumentKey().getCustomerId());
		
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocumentWithNewDocument()
	{
		return new DocumentDetails(standardDocumentKey(), documentListNew(), DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS,DOCUMENT_UID, DOCUMENT_REJECTION_COUNT, DOCUMENT_REJECTION_COUNT, 
				CUST_DATE, CUST_DATE, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB, standardDocumentKey().getCustomerId(),
				standardDocumentKey().getCustomerId());
	}
		
	
	
	
	public static DocumentDetails standardDocumentDetailsWithNewVerificationStatusAndRemark()
	{
		return new DocumentDetails(standardDocumentKey(), documentList(), DOCUMENT_VERIFICATION_STATUS_FAILED,
				DOCUMENT_VERIFICATION_REMARKS_FAILED,DOCUMENT_UID, DOCUMENT_REJECTION_COUNT, DOCUMENT_REJECTION_COUNT, 
				CUST_DATE, CUST_DATE, ACTOR_ENTITY_SELF_WEB,ACTOR_ENTITY_SELF_WEB, standardDocumentKey().getCustomerId(),
				standardDocumentKey().getCustomerId());
		
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
