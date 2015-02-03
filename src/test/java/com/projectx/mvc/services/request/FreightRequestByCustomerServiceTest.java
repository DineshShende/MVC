package com.projectx.mvc.services.request;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.config.BasicConfig;
import com.projectx.mvc.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByCustomerDTO;

import static com.projectx.mvc.fixtures.request.FreightRequestByCustomerDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class FreightRequestByCustomerServiceTest {

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Before
	public void clearData()
	{
		freightRequestByCustomerService.clearTestData();
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


}
