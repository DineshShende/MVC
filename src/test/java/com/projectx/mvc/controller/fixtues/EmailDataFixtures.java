package com.projectx.mvc.controller.fixtues;

import com.projectx.mvc.domain.Email;

public class EmailDataFixtures {

	public static String EMAIL_NAME="dinesh";
	public static String EMAIL_EMAIL="dineshshe@gmail.com";
	public static String EMAIL_MSG="Email added sucesssfully";		
	
	public static Email standardEmail()
	{
		return new Email(EMAIL_NAME,EMAIL_EMAIL);
		
	}
	
}
