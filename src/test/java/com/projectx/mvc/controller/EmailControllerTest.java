package com.projectx.mvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.mvc.controller.EmailController;
import com.projectx.mvc.domain.Email;
import com.projectx.mvc.services.EmailService;



/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
*/
//@ActiveProfiles("Test")
public class EmailControllerTest {

	
	/*@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	*/
	
	
	@InjectMocks
	EmailController customerQuickRegisterController;
	
	@Mock
	EmailService customerQuickRegisterService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerQuickRegisterController)
	    		.build();
	    
	    when(customerQuickRegisterService.addEmail(new Email("dineshshe@gmail.com","dinesh"))).thenReturn(new Email("dineshshe@gmail.com","dinesh"));
		
	}
	
	@Test
	public void thatEmailAddedSucessfully() throws Exception {
		
		this.mockMvc.perform(
	            post("/email/addemail").param("name", "dinesh")
	            						.param("email", "dineshshe@gmail.com"))
	            //.content("{\"name\":\"dinesh\",\"email\":\"dineshshe@gmail.com\"}")
	                    //.contentType(MediaType.APPLICATION_JSON)
	                    //.accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
		
		
		
	}

}
