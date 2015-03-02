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
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;

import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;


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
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()), customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer()));
		
		assertEquals(1,customerDetailsService.count().intValue());
	}
	
	
	@Test
	public void merge()
	{
		
		assertEquals(0,customerDetailsService.count().intValue());
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity );
		
				
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService
				.merge(standardCustomerDetails(savedEntity)));
		
		
		assertEquals(1,customerDetailsService.count().intValue());
	}
	
	
	
	@Test
	public void saveAndGetById()
	{
		
		assertEquals(0,customerDetailsService.count().intValue());
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity );
		
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService
				.merge(standardCustomerDetails(savedEntity)));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService.getCustomerDetailsById(savedEntity.getCustomerId()));
		
		
		assertEquals(1,customerDetailsService.count().intValue());
	}
	
	
	
	@Test
	public void verifySecondaryMobileDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileCustomerDTO());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity );
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService
				.merge(standardCustomerDetails(savedEntity)));
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		assertTrue(customerDetailsService.sendMobileVerificationDetails(new CustomerIdTypeMobileTypeDTO(savedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY)));
		
		MobileVerificationDetailsDTO MobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY,
				MobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY);
		
		assertNotEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(), customerDetailsService.getCustomerDetailsById(savedEntity.getCustomerId()).getSecondaryMobile());
		
		assertTrue(customerDetailsService.verifyMobileDetails(verifyMobileDTO));
		
		assertEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(), customerDetailsService.getCustomerDetailsById(savedEntity.getCustomerId()).getSecondaryMobile());
		
	}
	
	
	
	@Test
	public void verifyMobileDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		QuickRegisterSavedEntityDTO saved= customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(saved.getCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), customerDetailsService
				.merge(standardCustomerDetails(savedEntity)));
		
		
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		MobileVerificationDetailsDTO MobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
				MobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY);
		
		assertTrue(customerDetailsService.verifyMobileDetails(verifyMobileDTO));
		
		
	}
	
	
	
	@Test
	public void verifyEmailDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		QuickRegisterSavedEntityDTO saved= customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(saved.getCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), mergedEntity);
		
		
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=
				customerQuickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
				emailVerificationDetailsDTO.getEmailHash(),CUST_UPDATED_BY);
		
		assertTrue(customerDetailsService.verifyEmailDetails(verifyEmailDTO));
		
		
	}
	
	
	
	@Test
	public void sendMobileverificationDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		QuickRegisterSavedEntityDTO saved= customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(saved.getCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), mergedEntity);
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		assertTrue(customerDetailsService
				.sendMobileVerificationDetails(new CustomerIdTypeMobileTypeDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY)));
	}
	
	@Test
	public void sendEmailverificationDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		QuickRegisterSavedEntityDTO saved= customerQuickRegisterService.addNewCustomer(standardCustomerQuickRegisterEntity());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(saved.getCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithMerge(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), mergedEntity);
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		assertTrue(customerDetailsService
				.sendEmailVerificationDetails(new CustomerIdTypeEmailTypeDTO(mergedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY)));
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

}
