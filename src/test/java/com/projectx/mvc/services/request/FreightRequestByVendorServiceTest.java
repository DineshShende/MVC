package com.projectx.mvc.services.request;

import static com.projectx.mvc.fixtures.request.FreightRequestByVendorDataFixture.*;
import static com.projectx.mvc.fixtures.request.FreightRequestByCustomerDataFixture.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.mvc.exception.repository.completeregister.ValidationFailedException;
import com.projectx.mvc.services.completeregister.VendorDetailsService;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class FreightRequestByVendorServiceTest {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;

	@Before
	@After
	public void clearData()
	{
			
		freightRequestByVendorService.clearTestData();
		vendorDetailsService.clearTestData();
		vendorDetailsService.vehicleClearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGetById()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		//assertEquals(savedEntity, freightRequestByVendorService.getRequestById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
	}
	
	@Test
	public void saveWithError()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=null;
		
		try{
			savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendorWithError());
		}catch(ValidationFailedException e)
		{
			assertNull(savedEntity);
		}
		
		assertEquals(0, freightRequestByVendorService.count().intValue());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		savedEntity.setSource(REQ_DESTINATION_UPDATED);
		savedEntity.setAvailableTime(REQ_AVAIL_TIME_UPDATED);
		savedEntity.setPickupRangeInKm(REQ_PICK_UP_RANGE);
		
		FreightRequestByVendorDTO updatedEntity=freightRequestByVendorService.save(savedEntity);
		
		assertEquals(savedEntity, updatedEntity);
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
		
		freightRequestByVendorService.deleteRequestById(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
	}
	
	@Test
	public void deleteAll()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
		
		freightRequestByVendorService.clearTestData();
		
		assertEquals(0, freightRequestByVendorService.count().intValue());
	}
	
	
	@Test
	public void findByVendorId()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		VehicleDetailsDTO vehicleDetailsDTO=vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		List<FreightRequestByVendor> requestList=freightRequestByVendorService.getAllRequestForVendor(savedEntity.getVendorId());
		
		//assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}

	@Test
	public void getMatchingVendorReqForCustReq()
	{
		
		freightRequestByVendorService.clearTestData();
		vendorDetailsService.clearTestData();
		vendorDetailsService.vehicleClearTestData();
		
		vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO  savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		vendorDetailsService.save(standardVehicleDetailsOpen307());
		
		freightRequestByVendorService.save(standardFreightRequestByVendorOpen307());
		
		vendorDetailsService.save(standardVehicleDetailsClosed());
		
		freightRequestByVendorService.save(standardFreightRequestByVendorClosed());
		
		vendorDetailsService.save(standardVehicleDetailsFlexible());
		
		freightRequestByVendorService.save(standardFreightRequestByVendorFlexible());
		
		FreightRequestByCustomer freightRequestByCustomer=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		List<FreightRequestByVendor> matchList=freightRequestByVendorService.getMatchingVendorReqForCustReq(freightRequestByCustomer.toFreightRequestByCustomerDTO());
		
		assertEquals(1, matchList.size());
	}
	
	@Test
	public void getMatchingVendorReqForCustReqError()
	{

		try{
			List<FreightRequestByVendor> matchList=freightRequestByVendorService
					.getMatchingVendorReqForCustReq(standardFreightRequestByCustomerFullTruckLoadError());
			assertEquals(0, 1);
			
		}catch(ValidationFailedException e)
		{
			assertEquals(1, 1);
		}
		
	}
	
}
