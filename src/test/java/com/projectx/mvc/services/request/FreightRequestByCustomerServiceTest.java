package com.projectx.mvc.services.request;


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
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

import static com.projectx.mvc.fixtures.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.mvc.fixtures.completeregister.VehicleDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.request.FreightRequestByVendorDataFixture.*;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class FreightRequestByCustomerServiceTest {

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Before
	@After
	public void clearData()
	{
		freightRequestByCustomerService.clearTestData();
		freightRequestByVendorService.clearTestData();
		vendorDetailsService.vehicleClearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGet()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		
		assertEquals(savedEntity, freightRequestByCustomerService.getRequestById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerService.count().intValue());
	}
	
	


	@Test
	public void update()
	{
				
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		savedEntity.setBodyType(CREQ_BODYTYPE_CLOSED);
		
		freightRequestByCustomerService.save(savedEntity);
		
		assertEquals(savedEntity, freightRequestByCustomerService.getRequestById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerService.count().intValue());
		
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		assertEquals(1, freightRequestByCustomerService.count().intValue());
		
		freightRequestByCustomerService.deleteRequestById(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByCustomerService.count().intValue());
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
	}

	
	@Test
	public void clearTestData()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		assertEquals(1, freightRequestByCustomerService.count().intValue());
		
		freightRequestByCustomerService.clearTestData();
		
		assertEquals(0, freightRequestByCustomerService.count().intValue());
	}
	
	@Test
	public void findByCustomerId()
	{
		assertEquals(0, freightRequestByCustomerService.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad()));
		
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerService.getAllRequestForCustomer(savedEntity.getCustomerId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}

	
	@Test
	public void getCustmerReqForVendorReq()
	{
		freightRequestByCustomerService.clearTestData();
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoad110()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoadClosedAcerReq()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerFullTruckLoadOpenTataReq()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoad15()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenAcer()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenTata()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrand()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrandAndNoModel()));
		
		savedEntity=freightRequestByCustomerService.save(FreightRequestByCustomer.fromFreightRequestByCustomerDTO(standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel()));
		
		//FreightRequestByVendor vendorRequest=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		
		vendorDetailsService.save(standardVehicleDetails());
		
		FreightRequestByVendorDTO testRequest=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		freightRequestByCustomerService.getCustmerReqForVendorReq(standardFreightRequestByVendor());
		//TODO internal error need to setup things
		

	}
	
	@Test
	public void getCustmerReqForVendorReqWithError()
	{
		try{
			freightRequestByCustomerService.getCustmerReqForVendorReq(standardFreightRequestByVendorWithError());
			assertEquals(0, 1);
		}catch(ValidationFailedException e)
		{
			assertEquals(0, 0);
		}
		
		
	}

}
