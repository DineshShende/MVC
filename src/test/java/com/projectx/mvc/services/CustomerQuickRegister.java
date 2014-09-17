package com.projectx.mvc.services;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.projectx.mvc.domain.CustomerQuickRegisterEntity;
import com.projectx.mvc.servicefixtures.CustomerQuickRegisterFixture;

public class CustomerQuickRegister {

	CustomerQuickRegisterFixture customerQuickRegisterService; 
	
	@Before
	public void setUp()
	{
		customerQuickRegisterService=new CustomerQuickRegisterFixture();
	}
	
	
	@Test
	public void setStatusWithEmail() throws Exception {
		
		//CustomerQuickRegisterKey key=new CustomerQuickRegisterKey("dineshshe@gmail.com", null);
		
		CustomerQuickRegisterEntity customerQuickRegisterEntity=new CustomerQuickRegisterEntity("Dinesh", "Shende","dineshshe@gmail.com", null, 413133, null);
				
		assertEquals("EmailVerificationPending", customerQuickRegisterService.setStatus(customerQuickRegisterEntity));
		
	}

	
	@Test
	public void setStatusWithMobile() throws Exception {
		
		//CustomerQuickRegisterKey key=new CustomerQuickRegisterKey("", 99608218669L);
		
		CustomerQuickRegisterEntity customerQuickRegisterEntity=new CustomerQuickRegisterEntity("Dinesh", "Shende","", 99608218669L, 413133, null);
				
		assertEquals("MobileVerificationPending", customerQuickRegisterService.setStatus(customerQuickRegisterEntity));
		
	}
	
	@Test
	public void setStatusWithEmailMobile() throws Exception {
		
		//CustomerQuickRegisterKey key=new CustomerQuickRegisterKey("dineshshe@gmail.com", 99608218669L);
		
		CustomerQuickRegisterEntity customerQuickRegisterEntity=new CustomerQuickRegisterEntity("Dinesh", "Shende","dineshshe@gmail.com", 99608218669L, 413133, null);
				
		assertEquals("EmailMobileVerificationPending", customerQuickRegisterService.setStatus(customerQuickRegisterEntity));
		
	}
	
	/*
	@Test
	public void setStatusWithEmailMobileEmpty() throws Exception {
		
		CustomerQuickRegisterEntity customerQuickRegisterEntity=new CustomerQuickRegisterEntity("Dinesh", "Shende", "",null, 413133, null);
				
		assertEquals("EmailMobileVerificationPending", customerQuickRegisterService.setStatus(customerQuickRegisterEntity));
	
		
	}
	
	*/
	
}
