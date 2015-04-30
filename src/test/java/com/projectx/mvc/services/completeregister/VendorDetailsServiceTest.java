package com.projectx.mvc.services.completeregister;

import static com.projectx.mvc.fixtures.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.completeregister.DriverDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.VerifyEmailDTO;
import com.projectx.rest.domain.quickregister.VerifyMobileDTO;

import static com.projectx.mvc.fixtures.completeregister.VendorDetailsDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class VendorDetailsServiceTest {



	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	QuickRegisterService customerQuickRegisterService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	
	@Before
	public void setUp()
	{
		customerDetailsService.clearTestData();
		customerQuickRegisterService.clearTestData();
		
		vendorDetailsService.driverClearTestData();
		vendorDetailsService.clearTestData();
		
		vendorDetailsService.vehicleClearTestData();	
		
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	

	
	@Test
	public void merge()
	{
		
		assertEquals(0,vendorDetailsService.count().intValue());
		
	
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		
		customerQuickRegisterService.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY);
		
		customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY,
				mobileVerificationDetailsDTO.getMobilePin(), CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		
		AuthenticationDetails authenticationDetails=
				customerQuickRegisterService.getAuthenticationDetailsByCustomerIdType(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType()), "password", authenticationDetails.getPassword(),
						true, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId())));
		
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
		
		assertFalse(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
	
		assertTrue(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
	
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAddMobileVerified(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		assertEquals(standardVendor(savedEntity), vendorDetailsService.update(standardVendor(savedEntity)));
		
		assertEquals(1,vendorDetailsService.count().intValue());
	}
	
	
	
	@Test
	public void saveAndGetById()
	{
		
		assertEquals(0,vendorDetailsService.count().intValue());
		
		
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		customerQuickRegisterService.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY);
		
		customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY,
				mobileVerificationDetailsDTO.getMobilePin(), CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		
		AuthenticationDetails authenticationDetails=
				customerQuickRegisterService.getAuthenticationDetailsByCustomerIdType(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType()), "password", authenticationDetails.getPassword(),
						true, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId())));
		
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
		
		assertFalse(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
	
		assertTrue(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
	
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAddMobileVerified(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		assertEquals(standardVendor(savedEntity), vendorDetailsService.update(standardVendor(savedEntity)));
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
	}
	
	
	@Test
	public void verifyMobileDetails()
	{
		assertEquals(0,vendorDetailsService.count().intValue());
		
		
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		customerQuickRegisterService.sendEmailHash(new CustomerIdTypeEmailTypeUpdatedByDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY,
				quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=
				customerQuickRegisterService.getEmailVerificationDetailsByCustomerIdTypeAndEmail(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY);
		
		assertTrue(customerQuickRegisterService.verifyEmail(new VerifyEmailDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, emailVerificationDetailsDTO.getEmailHash(), 
				CUST_UPDATED_BY,quickRegisterSavedEntityDTO.getCustomer().getCustomerId())));
		
		AuthenticationDetails authenticationDetails=
				customerQuickRegisterService.getAuthenticationDetailsByCustomerIdType(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType()), "password", authenticationDetails.getPassword(),
						true, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId())));
		
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
		
		assertFalse(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
	
		assertTrue(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
	
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAddEmailVerified(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		
		VendorDetailsDTO mergedEntity=vendorDetailsService.update(standardVendor(savedEntity));
		
		assertEquals(standardVendor(savedEntity),mergedEntity );
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
		
		assertTrue(customerQuickRegisterService.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(
				mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY,mergedEntity.getVendorId())));
		
		MobileVerificationDetailsDTO MobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY);
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
				MobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY,mergedEntity.getVendorId());
		
		assertTrue(customerQuickRegisterService.verifyMobile(verifyMobileDTO));
		
		
	}
	
	
	
	@Test
	public void verifyEmailDetails()
	{
		assertEquals(0,vendorDetailsService.count().intValue());
		
				
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		

		customerQuickRegisterService.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY);
		
		customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY,
				mobileVerificationDetailsDTO.getMobilePin(), CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		
		AuthenticationDetails authenticationDetails=
				customerQuickRegisterService.getAuthenticationDetailsByCustomerIdType(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType()), "password", authenticationDetails.getPassword(),
						true, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId())));
		
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
		
		assertFalse(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
	
		assertTrue(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
	
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAddMobileVerified(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
	
		VendorDetailsDTO mergedEntity=vendorDetailsService.update(standardVendor(savedEntity));
		
		assertEquals(standardVendor(savedEntity),mergedEntity );
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
		
		assertTrue(customerQuickRegisterService.sendEmailHash(new CustomerIdTypeEmailTypeUpdatedByDTO(
				mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,mergedEntity.getVendorId())));
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=
				customerQuickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY);
		
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
				emailVerificationDetailsDTO.getEmailHash(),CUST_UPDATED_BY,mergedEntity.getVendorId());
		
		assertTrue(customerQuickRegisterService.verifyEmail(verifyEmailDTO));
		
		
	}
	
	
	@Test
	public void sendMobileverificationDetails()
	{
		assertEquals(0,vendorDetailsService.count().intValue());
		
		
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		

		customerQuickRegisterService.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY);
		
		customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY,
				mobileVerificationDetailsDTO.getMobilePin(), CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		
		AuthenticationDetails authenticationDetails=
				customerQuickRegisterService.getAuthenticationDetailsByCustomerIdType(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType()), "password", authenticationDetails.getPassword(),
						true, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId())));
		
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
		
		assertFalse(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
	
		assertTrue(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
	
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAddMobileVerified(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		VendorDetailsDTO mergedEntity=vendorDetailsService.update(standardVendor(savedEntity));
		
		assertEquals(standardVendor(savedEntity),mergedEntity );
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
	
		assertTrue(customerQuickRegisterService
				.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR,
						ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,mergedEntity.getVendorId())));
	}
	
	
	
	@Test
	public void sendEmailverificationDetails()
	{
		assertEquals(0,vendorDetailsService.count().intValue());
		
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		

		customerQuickRegisterService.sendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY);
		
		customerQuickRegisterService.verifyMobile(new VerifyMobileDTO(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
				quickRegisterSavedEntityDTO.getCustomer().getCustomerType(), ENTITY_TYPE_PRIMARY,
				mobileVerificationDetailsDTO.getMobilePin(), CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId()));
		
		
		AuthenticationDetails authenticationDetails=
				customerQuickRegisterService.getAuthenticationDetailsByCustomerIdType(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType());
		
		assertTrue(customerQuickRegisterService.updatePassword(new UpdatePasswordDTO(new AuthenticationDetailsKey(quickRegisterSavedEntityDTO.getCustomer().getCustomerId(),
						quickRegisterSavedEntityDTO.getCustomer().getCustomerType()), "password", authenticationDetails.getPassword(),
						true, CUST_UPDATED_BY, quickRegisterSavedEntityDTO.getCustomer().getCustomerId())));
		
		
		AuthenticationDetailsDTO authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
		
		assertFalse(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		authenticationDetailsDTO=customerQuickRegisterService.verifyLoginDetails(new LoginVerificationDTO(quickRegisterSavedEntityDTO.getCustomer().getEmail(),
				"password"));
	
		assertTrue(authenticationDetailsDTO.getIsCompleteRegisterCompleted());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.getCustomerDetailsById(quickRegisterSavedEntityDTO.getCustomer().getCustomerId());
		
	
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAddMobileVerified(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		
		
	
		VendorDetailsDTO mergedEntity=vendorDetailsService.update(standardVendor(savedEntity));
		
		assertEquals(standardVendor(savedEntity),mergedEntity );
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
	
		
		assertTrue(customerQuickRegisterService
				.sendEmailHash(new CustomerIdTypeEmailTypeUpdatedByDTO(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, 
						ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,mergedEntity.getVendorId())));
	}

	
	
	@Test
	public void count()
	{
		
		assertEquals(0,vendorDetailsService.count().intValue());
		
	}
	
	
	@Test
	public void addDriverAndGetByDriverId()
	{
		
		assertEquals(0, vendorDetailsService.driverCount().intValue());
		
		DriverDetailsDTO savedEntity=vendorDetailsService.addDriver(standardDriverDetails());
		
		assertEquals(savedEntity, vendorDetailsService.getDriverById(savedEntity.getDriverId()));
		
		assertEquals(1, vendorDetailsService.driverCount().intValue());
	}

	
	@Test
	public void addDriverWithError()
	{
		
		assertEquals(0, vendorDetailsService.driverCount().intValue());
		
		DriverDetailsDTO savedEntity=null;
		try{
			savedEntity=vendorDetailsService.addDriver(standardDriverDetailsWithError());
		}catch(ValidationFailedException e)
		{
			assertNull(savedEntity);
		}
		
		
		
		
	}
	@Test
	public void updateAndVerifyMobile()
	{
		assertEquals(0, vendorDetailsService.driverCount().intValue());
		
		DriverDetailsDTO savedEntity=vendorDetailsService.addDriver(standardDriverDetails());
		
		DriverDetailsDTO updatedEntity=vendorDetailsService.addDriver(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()));
		
		assertNotEquals(updatedEntity.getMobile(),standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile());
		
		customerQuickRegisterService
			.reSendMobilePin(new CustomerIdTypeMobileTypeRequestedByDTO(savedEntity.getDriverId(), ENTITY_TYPE_DRIVER, 
					ENTITY_TYPE_PRIMARY,CUST_UPDATED_BY,savedEntity.getDriverId()));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				customerQuickRegisterService.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntity.getDriverId(), ENTITY_TYPE_DRIVER, ENTITY_TYPE_PRIMARY);
		
		customerQuickRegisterService.verifyMobile(new com.projectx.rest.domain.quickregister
				.VerifyMobileDTO(savedEntity.getDriverId(), ENTITY_TYPE_DRIVER, ENTITY_TYPE_PRIMARY,
						mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY,savedEntity.getDriverId()));
		
		assertEquals(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile(),vendorDetailsService.getDriverById(savedEntity.getDriverId()).getMobile());
	}
	
	
	@Test
	public void addVehicle()
	{
		
		
		
		assertEquals(0, vendorDetailsService.vehicleCount().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		assertEquals(vehicleDetailsDTO, vendorDetailsService.getVehicleById(vehicleDetailsDTO.getVehicleId()));
		
		assertEquals(1, vendorDetailsService.vehicleCount().intValue());
		
	}
	
}
