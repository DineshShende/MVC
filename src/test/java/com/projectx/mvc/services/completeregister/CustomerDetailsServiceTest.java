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
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileDTO;
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
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer()));
		
		assertEquals(1,customerDetailsService.count().intValue());
	}
	
	@Test
	public void merge()
	{
		
		assertEquals(0,customerDetailsService.count().intValue());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		assertEquals(standardCustomerDetails(savedEntity), customerDetailsService
				.merge(standardCustomerDetails(savedEntity)));
		
		
		assertEquals(1,customerDetailsService.count().intValue());
	}
	
	
	@Test
	public void verifySecondaryMobileDetails()
	{
		assertEquals(0,customerDetailsService.count().intValue());
		
		CustomerDetailsDTO savedEntity=customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetailsDTO mergedEntity=customerDetailsService.merge(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetails(savedEntity),mergedEntity );
		
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		MobileVerificationDetailsDTO MobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(mergedEntity.getCustomerId(), CUST_TYPE, mergedEntity.getSecondaryMobile());
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(mergedEntity.getCustomerId(), CUST_TYPE, mergedEntity.getSecondaryMobile(),2,
				MobileVerificationDetailsDTO.getMobilePin());
		
		assertTrue(customerDetailsService.verifyMobileDetails(verifyMobileDTO));
		
		
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
		
		assertEquals(standardCustomerDetails(savedEntity),mergedEntity );
		
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		MobileVerificationDetailsDTO MobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(mergedEntity.getCustomerId(), CUST_TYPE, mergedEntity.getMobile());
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(mergedEntity.getCustomerId(), CUST_TYPE, mergedEntity.getMobile(),1,
				MobileVerificationDetailsDTO.getMobilePin());
		
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
		
		assertEquals(standardCustomerDetails(savedEntity),mergedEntity );
		
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=
				customerQuickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(mergedEntity.getCustomerId(), CUST_TYPE, mergedEntity.getEmail());
		
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(mergedEntity.getCustomerId(), CUST_TYPE, mergedEntity.getEmail(),1,
				emailVerificationDetailsDTO.getEmailHash());
		
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
		
		assertEquals(standardCustomerDetails(savedEntity),mergedEntity );
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		assertTrue(customerDetailsService
				.sendMobileVerificationDetails(new CustomerIdTypeMobileDTO(mergedEntity.getCustomerId(), CUST_TYPE, mergedEntity.getMobile())));
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
		
		assertEquals(standardCustomerDetails(savedEntity),mergedEntity );
		
		assertEquals(1,customerDetailsService.count().intValue());
		
		assertTrue(customerDetailsService
				.sendEmailVerificationDetails(new CustomerIdTypeEmailDTO(mergedEntity.getCustomerId(), CUST_TYPE, mergedEntity.getEmail())));
	}
	
	@Test
	public void count()
	{
		
		assertEquals(0,customerDetailsService.count().intValue());
		
	}

}
