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
import com.projectx.mvc.services.quickregister.QuickRegisterService;
import com.projectx.rest.domain.completeregister.CustomerDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.DriverDetailsDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.completeregister.VendorDetailsDTO;
import com.projectx.rest.domain.completeregister.VerifyEmailDTO;
import com.projectx.rest.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;

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
		vendorDetailsService.clearTestData();
		vendorDetailsService.driverClearTestData();
		vendorDetailsService.vehicleClearTestData();
		
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	

	
	@Test
	public void createCustomerDetailsFromQuickRegisterEntity()
	{
		
		assertEquals(0,vendorDetailsService.count().intValue());
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileVendor()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		assertEquals(standardVendorCreatedFromQuickRegister(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()), vendorDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
	}
	
	
	@Test
	public void merge()
	{
		
		assertEquals(0,vendorDetailsService.count().intValue());
		
	
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileVendor()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		
		assertEquals(standardVendorCreatedFromQuickRegister(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		assertEquals(standardVendor(savedEntity), vendorDetailsService.update(standardVendor(savedEntity)));
		
		assertEquals(1,vendorDetailsService.count().intValue());
	}
	
	
	
	@Test
	public void saveAndGetById()
	{
		
		assertEquals(0,vendorDetailsService.count().intValue());
		
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileVendor()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		
		assertEquals(standardVendorCreatedFromQuickRegister(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		assertEquals(standardVendor(savedEntity), vendorDetailsService.update(standardVendor(savedEntity)));
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
	}
	
	
	@Test
	public void verifyMobileDetails()
	{
		assertEquals(0,vendorDetailsService.count().intValue());
		
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileVendor()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		
		assertEquals(standardVendorCreatedFromQuickRegister(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		VendorDetailsDTO mergedEntity=vendorDetailsService.update(standardVendor(savedEntity));
		
		assertEquals(standardVendor(savedEntity),mergedEntity );
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
		
		MobileVerificationDetailsDTO MobileVerificationDetailsDTO=
				customerQuickRegisterService
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY);
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
				MobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY);
		
		assertTrue(vendorDetailsService.verifyMobileDetails(verifyMobileDTO));
		
		
	}
	
	
	
	@Test
	public void verifyEmailDetails()
	{
		assertEquals(0,vendorDetailsService.count().intValue());
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileVendor()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		
		assertEquals(standardVendorCreatedFromQuickRegister(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		VendorDetailsDTO mergedEntity=vendorDetailsService.update(standardVendor(savedEntity));
		
		assertEquals(standardVendor(savedEntity),mergedEntity );
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
		
		EmailVerificationDetailsDTO emailVerificationDetailsDTO=
				customerQuickRegisterService
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY);
		
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
				emailVerificationDetailsDTO.getEmailHash(),CUST_UPDATED_BY);
		
		assertTrue(customerDetailsService.verifyEmailDetails(verifyEmailDTO));
		
		
	}
	
	
	@Test
	public void sendMobileverificationDetails()
	{
		assertEquals(0,vendorDetailsService.count().intValue());
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileVendor()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		
		assertEquals(standardVendorCreatedFromQuickRegister(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		VendorDetailsDTO mergedEntity=vendorDetailsService.update(standardVendor(savedEntity));
		
		assertEquals(standardVendor(savedEntity),mergedEntity );
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
	
		assertTrue(vendorDetailsService
				.sendMobileVerificationDetails(new CustomerIdTypeMobileTypeDTO(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY)));
	}
	
	
	
	@Test
	public void sendEmailverificationDetails()
	{
		assertEquals(0,vendorDetailsService.count().intValue());
		
		assertNull( customerDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileVendor()));
		
		QuickRegisterSavedEntityDTO quickRegisterSavedEntityDTO=
				customerQuickRegisterService.addNewCustomer(standardEmailMobileVendorDTO());
		
		VendorDetailsDTO savedEntity=vendorDetailsService
				.createCustomerDetailsFromQuickRegisterEntity(quickRegisterSavedEntityDTO.getCustomer());
		
		
		assertEquals(standardVendorCreatedFromQuickRegister(quickRegisterSavedEntityDTO.getCustomer().getCustomerId()),savedEntity);
	
		VendorDetailsDTO mergedEntity=vendorDetailsService.update(standardVendor(savedEntity));
		
		assertEquals(standardVendor(savedEntity),mergedEntity );
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.getCustomerDetailsById(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsService.count().intValue());
	
		
		assertTrue(customerDetailsService
				.sendEmailVerificationDetails(new CustomerIdTypeEmailTypeDTO(mergedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY)));
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
	public void updateAndVerifyMobile()
	{
		assertEquals(0, vendorDetailsService.driverCount().intValue());
		
		DriverDetailsDTO savedEntity=vendorDetailsService.addDriver(standardDriverDetails());
		
		DriverDetailsDTO updatedEntity=vendorDetailsService.update(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()));
		
		assertNotEquals(updatedEntity.getMobile(),standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile());
		
		customerQuickRegisterService
			.reSendMobilePin(new com.projectx.rest.domain.quickregister.CustomerIdTypeMobileTypeDTO(savedEntity.getDriverId(), ENTITY_TYPE_DRIVER, ENTITY_TYPE_PRIMARY));
		
		MobileVerificationDetailsDTO mobileVerificationDetailsDTO=
				customerQuickRegisterService.getMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntity.getDriverId(), ENTITY_TYPE_DRIVER, ENTITY_TYPE_PRIMARY);
		
		customerQuickRegisterService.verifyMobile(new com.projectx.rest.domain.quickregister
				.VerifyMobileDTO(savedEntity.getDriverId(), ENTITY_TYPE_DRIVER, ENTITY_TYPE_PRIMARY,mobileVerificationDetailsDTO.getMobilePin(),CUST_UPDATED_BY));
		
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
