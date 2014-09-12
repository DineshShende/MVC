package com.projectx.mvc.servicefixtures;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.mvc.domain.Email;
import com.projectx.mvc.services.EmailService;

@Component
@Profile("Test")
public class EmailFixtures implements
		EmailService {

	Map<String, Email> emailMap;

	public EmailFixtures() {
		emailMap = new HashMap<String, Email>();
	}

	@Override
	public Email addEmail(Email email) {

		if (emailMap.containsKey(email.getEmail()))
			email.setMessage("Email Already Exists");
		else {
			email.setMessage("Email added sucesssfully");
			emailMap.put(email.getEmail(), email);
		}

		return email;
	}
	/*
	 * 
	 * @Override public Email emailRedirect(Email email) {
	 * 
	 * System.out.println("In test:"+email.getEmail()); return new
	 * Email("dineshshe@gmail.com"); }
	 * 
	 * @Override public List<Email> getAllEmails() { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public boolean checkEmailExisted(Email email) { // TODO
	 * Auto-generated method stub return false; }
	 */
}
