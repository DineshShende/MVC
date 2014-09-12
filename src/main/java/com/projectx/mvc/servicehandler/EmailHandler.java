package com.projectx.mvc.servicehandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.Email;
import com.projectx.mvc.services.EmailService;

@Component
@Profile("dev")
@PropertySource(value="classpath:/application.properties")
public class EmailHandler implements
		EmailService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public Email addEmail(Email email) throws Exception {
		
			Email addedEmail=restTemplate.postForObject(
				env.getProperty("rest.host")+"/email/addemail", email,
				Email.class);
		
		
		return addedEmail;
	}
	
	

	/*
	@Override
	public Email emailRedirect(Email email) {

		//EmailDTO restEmail=new EmailDTO(email.getEmail(),email.getName());
		
		Email redirectEmail = restTemplate.postForObject(
				"http://localhost:8080/RestEvent/email/redirectemail", email,
				Email.class);

		//email.setEmail(redirectEmail.getEmail());
		//email.setName(redirectEmail.getName());
		//email.setMessage("Sucessfully Redirected");
		
		
		System.out.println("In dev:" + redirectEmail.getEmail());
		
		return redirectEmail;

	}

	@Override
	public List<Email> getAllEmails() {

		@SuppressWarnings("unchecked")
		List<Email> returnList=restTemplate.getForObject(
				"http://localhost:8080/RestEvent/customer/checkemail", List.class);
		
		return returnList;
	}


	@Override
	public Boolean checkEmailExisted(Email email) {
		return customerQuickRegisterRepository.checkEmailExisted(email);
	}
*/
}
