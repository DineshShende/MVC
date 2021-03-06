package com.projectx.mvc.services.completeregister;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.UpdateDocumentDTO;
import com.projectx.rest.domain.completeregister.UpdateDocumentVerificationStatusAndRemarkDTO;

import static com.projectx.mvc.fixtures.completeregister.DocumentDetailsDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")

public class DocumentDetailsServiceTest {

	@Autowired
	DocumentDetailsService documentDetailsService;

	private Integer UPDATED_BY=1;
	
	@Before
	public void setUp()
	{
		documentDetailsService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
	}

	@Test
	public void saveAndGetDocumentDetails()
	{
		assertEquals(0, documentDetailsService.count().intValue());
		
		DocumentDetails savedEntity=documentDetailsService.saveDocument(standardDocumentDetailsWithDummyDocument());
		
		assertEquals(standardDocumentDetailsWithDummyDocument(), savedEntity);
		
		assertEquals(savedEntity, documentDetailsService.getDocumentById(standardDocumentKey()));
		
		assertEquals(1, documentDetailsService.count().intValue());
		
		
	}
	
	@Test
	public void updateDocument()
	{
		assertEquals(0, documentDetailsService.count().intValue());
		
		DocumentDetails savedEntity=documentDetailsService.saveDocument(standardDocumentDetailsWithDummyDocument());
		
		assertEquals(standardDocumentDetailsWithDummyDocument(), savedEntity);
		
		assertEquals(standardDocumentDetailsWithDummyDocumentWithNewDocument(),
				documentDetailsService.updateDocument(new UpdateDocumentDTO(standardDocumentKey(), 
						standardDocumentDetailsWithDummyDocumentWithNewDocument().getDocuments(),
						UPDATED_BY,standardDocumentKey().getCustomerId())));
		
		assertEquals(1, documentDetailsService.count().intValue());
	}
	
	@Test
	public void updateDocumentVerificationDetails()
	{
		assertEquals(0, documentDetailsService.count().intValue());
		
		DocumentDetails savedEntity=documentDetailsService.saveDocument(standardDocumentDetailsWithDummyDocument());
		
		assertEquals(standardDocumentDetailsWithDummyDocument(), savedEntity);
		
		assertEquals(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark(),
				documentDetailsService
				.updateDocumentVerificationDetails(new UpdateDocumentVerificationStatusAndRemarkDTO(standardDocumentKey(),
						standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getVerificationStatus(), 
						standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getVerificationRemark(),
						UPDATED_BY,standardDocumentKey().getCustomerId())));
		
		assertEquals(1, documentDetailsService.count().intValue());
	}
	
	@Test
	public void clearTest()
	{
		assertEquals(0, documentDetailsService.count().intValue());
		
		documentDetailsService.saveDocument(standardDocumentDetails());
		
		assertEquals(1, documentDetailsService.count().intValue());
		
		assertTrue(documentDetailsService.clearTestData());
		
		assertEquals(0, documentDetailsService.count().intValue());
	}
	
	@Test
	public void count()
	{
		assertEquals(0, documentDetailsService.count().intValue());
	}
}
