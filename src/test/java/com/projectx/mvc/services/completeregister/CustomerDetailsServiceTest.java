package com.projectx.mvc.services.completeregister;

import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetails;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetailsCopiedFromQuickRegisterEntity;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.standardCustomerDetailsWithMerge;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.CUST_UPDATED_BY;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.ENTITY_TYPE_CUSTOMER;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.ENTITY_TYPE_PRIMARY;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.ENTITY_TYPE_SECONDARY;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.standardCustomerQuickRegisterEntity;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomer;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomerDTO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class CustomerDetailsServiceTest {

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	QuickRegisterService customerQuickRegisterService;
	
	
	@Before
	public void setUp()
	{
		customerDetailsService.clearTestData();
		customerQuickRegisterService.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void createCustomerDetailsFromQuickRegisterEntity()
	{
		
		assertEquals(0,customerDetailsService.count().intValue());
		
		try{
		 customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer().getCustomerId());
		
		 assertEquals(0, 1);
		 
		}catch(DeleteQuickCreateDetailsEntityFailedException e)
		{
			assertEquals(1, 1);
		}
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAdd(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()), customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		assertEquals(1,customerDetailsService.count().intValue());
	}
	
	
	@Test
	public void merge()
	{
		
		assertEquals(0,customerDetailsService.count().intValue());
		
		try{
			customerDetailsService
					.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer().getCustomerId());
			assertEquals(0, 1);
			 
		}catch(DeleteQuickCreateDetailsEntityFailedException e)
		{
			assertEquals(1, 1);
		}
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
		
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAdd(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity );
		
				
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService
				.merge(standardCustomerDetails(savedEntity)));
		
		
		assertEquals(1,customerDetailsService.count().intValue());
	}
	
	
	
	@Test
	public void saveAndGetById()
	{
		
		assertEquals(0,customerDetailsService.count().intValue());
		
		try{
			customerDetailsService
					.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer().getCustomerId());
			assertEquals(0, 1);
			 
		}catch(DeleteQuickCreateDetailsEntityFailedException e)
		{
			assertEquals(1, 1);
		}
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAdd(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity );
		
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService
				.merge(standardCustomerDetails(savedEntity)));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService.getCustomerDetailsById(savedEntity.getCustomerId()));
		
		
		assertEquals(1,customerDetailsService.count().intValue());
	}
	
	
	
	@Test
	public void verifySecondaryMobileDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		try{
			customerDetailsService
					.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer().getCustomerId());
			assertEquals(0, 1);
			 
		}catch(DeleteQuickCreateDetailsEntityFailedException e)
		{
			assertEquals(1, 1);
		}
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAdd(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity );
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService
				.merge(standardCustomerDetails(savedEntity)));
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		assertTrue(customerQuickRegisterService.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(savedEntity.getCustomerId(), 
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY,CUST_UPDATED_BY,savedEntity.getCustomerId())));
		
		MobileVerificationDetailsDTO MobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY,
				MobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY,mergedEntity.getCustomerId());
		
		assertNotEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(), customerDetailsService.getCustomerDetailsById(savedEntity.getCustomerId()).getSecondaryMobile());
		
		assertTrue(customerQuickRegisterService.verifyMobile(verifyMobileDTO));
		
		assertEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(), customerDetailsService.getCustomerDetailsById(savedEntity.getCustomerId()).getSecondaryMobile());
		
	}
	
	
	
	@Test
	public void verifyMobileDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		QuickRegisterSavedEntityDTO saved= customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(saved.getCustomer().getCustomerId());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAdd(savedEntity.getCustomerId()), savedEntity);
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService
				.merge(standardCustomerDetails(savedEntity)));
		
		
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		MobileVerificationDetailsDTO MobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
				MobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY,mergedEntity.getCustomerId());
		
		assertTrue(customerQuickRegisterService.verifyMobile(verifyMobileDTO));
		
		
	}
	
	
	
	@Test
	public void verifyEmailDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		QuickRegisterSavedEntityDTO saved= customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(saved.getCustomer().getCustomerId());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAdd(savedEntity.getCustomerId()), savedEntity);
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), mergedEntity);
		
		
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=
				customerQuickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
				emailVerificationDetailsDTO.getEmailHash(),CUST_UPDATED_BY,mergedEntity.getCustomerId());
		
		assertTrue(customerQuickRegisterService.verifyEmail(verifyEmailDTO));
		
		
	}
	
	
	
	@Test
	public void sendMobileverificationDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		QuickRegisterSavedEntityDTO saved= customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(saved.getCustomer().getCustomerId());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAdd(savedEntity.getCustomerId()), savedEntity);
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), mergedEntity);
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		assertTrue(customerQuickRegisterService
				.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, 
						ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,mergedEntity.getCustomerId())));
	}
	
	@Test
	public void sendEmailverificationDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		QuickRegisterSavedEntityDTO saved= customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(saved.getCustomer().getCustomerId());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntityWithDefaultAdd(savedEntity.getCustomerId()), savedEntity);
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), mergedEntity);
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		assertTrue(customerQuickRegisterService
				.sendEmailHash(new CustomerIdTypeEmailTypeUpdatedByDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, 
						ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,mergedEntity.getCustomerId())));
	}

	
	@Test
	public void InitializeMetaData()
	{
		
		assertEquals(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()), customerDetailsService.InitializeMetaData(standardCustomerDetailsWithOutMetaData()));
	}
	
	
	@Test
	public void count()
	{
		
		assertEquals(0,customerDetailsService.count().intValue());
		
	}

	@Test
	public void awsTest()
	{
		assertEquals("aws", customerDetailsService.awsTest());
	}
	
}
