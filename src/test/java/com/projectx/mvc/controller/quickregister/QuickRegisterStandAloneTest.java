package com.projectx.mvc.controller.quickregister;

import static com.projectx.mvc.fixtures.completeregister.DocumentDetailsDataFixture.CUST_TYPE_CUSTOMER;
import static com.projectx.mvc.fixtures.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.mvc.fixtures.quickregister.QuickRegisterDataFixture.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.mvc.controller.quickregister.QuickRegisterController;
import com.projectx.mvc.domain.quickregister.QuickRegisterEntity;
import com.projectx.mvc.servicefixtures.quickregister.QuickRegisterServiceFixture;
import com.projectx.rest.domain.quickregister.QuickRegisterSavedEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterStatusDTO;

public class QuickRegisterStandAloneTest {

	@InjectMocks
	QuickRegisterController customerQuickRegisterController;
	
	@Mock
	QuickRegisterServiceFixture customerQuickRegisterService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerQuickRegisterController)
	    		.build();
	    
	}
	


}
