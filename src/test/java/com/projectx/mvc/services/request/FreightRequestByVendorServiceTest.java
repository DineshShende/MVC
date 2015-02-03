package com.projectx.mvc.services.request;

import static com.projectx.mvc.fixtures.request.FreightRequestByVendorDataFixture.*;
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
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BasicConfig.class)
@ActiveProfiles(value="Dev")
public class FreightRequestByVendorServiceTest {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;

	@Before
	public void clearData()
	{
		freightRequestByVendorService.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGetById()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		assertEquals(savedEntity, freightRequestByVendorService.getRequestById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
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
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorService.count().intValue());
		
		freightRequestByVendorService.clearTestData();
		
		assertEquals(0, freightRequestByVendorService.count().intValue());
	}
	
	
	@Test
	public void findByVendorId()
	{
		assertEquals(0, freightRequestByVendorService.count().intValue());
		
		FreightRequestByVendorDTO savedEntity=freightRequestByVendorService.save(standardFreightRequestByVendor());
		
		List<FreightRequestByVendorDTO> requestList=freightRequestByVendorService.getAllRequestForVendor(savedEntity.getVendorId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}


}
