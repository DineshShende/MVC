package com.projectx.mvc.controller.ivr;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.projectx.mvc.domain.request.KookooResponse;
import com.projectx.rest.domain.ivr.KooResponseDTO;

@Controller
@RequestMapping(value="/koocall")
@PropertySource(value="classpath:/application.properties")
public class InBoundCallController {

	@Autowired
	Environment env;

	
	AsyncRestTemplate asyncRestTemplate=new AsyncRestTemplate();
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String respondToCall(HttpServletRequest request)
	{
		
		String sid=request.getParameter("sid");
		String cid=request.getParameter("cid");
		
		if(cid!=null)
			cid=cid.substring(1);
		
		String called_number=request.getParameter("called_number");
		String event=request.getParameter("event");
		String data=request.getParameter("data");
		
		System.out.println(data);
		
		if(event==null)
		{
			String abc="<?xml version=\"1.0\" encoding=\"UTF-8\"?><response sid=\""+sid+"\"><collectdtmf l=\"2\" t=\"#\" o=\"5000\"><playtext>Please enter one for select and two for reject. Press hash to submit</playtext>"
					+ "</collectdtmf></response>";
					
			return abc;
		}
		else if(event.equals("GotDTMF"))
		{
			try{
			Integer dataInt=Integer.parseInt(data);
			}catch(RuntimeException e)
			{
				String abc="<?xml version=\"1.0\" encoding=\"UTF-8\"?><response sid=\""+sid+"\"><collectdtmf l=\"2\" t=\"#\" o=\"5000\"><playtext>You have not selected any option.Please enter one for select and two for reject. Press hash to submit</playtext>"
						+ "</collectdtmf></response>";
								
			
				return abc;
				
			}
			
			if(data.equals(""))
			{
				String abc="<?xml version=\"1.0\" encoding=\"UTF-8\"?><response sid=\""+sid+"\"><collectdtmf l=\"2\" t=\"#\" o=\"5000\"><playtext>You have not selected any option.Please enter one for select and two for reject. Press hash to submit</playtext>"
						+ "</collectdtmf></response>";
								
							
				return abc;
			}
			else
			{	
				System.out.println("Received choice"+request.getParameter("data"));
			
				String hangup="<?xml version=\"1.0\" encoding=\"UTF-8\"?><response><playtext>Thank you for your response</playtext><hangup></hangup></response>";
			
				KooResponseDTO kooResponseDTO=new KooResponseDTO(sid, Long.parseLong(cid),Integer.parseInt(data));
				
				asyncRestTemplate.exchange(env.getProperty("rest.host")+"/outboundcall/receiveResponse", HttpMethod.POST, new HttpEntity<KooResponseDTO>(kooResponseDTO), Boolean.class);
				
				return hangup;
			}
		}

				
		String abc="<?xml version=\"1.0\" encoding=\"UTF-8\"?><response sid=\""+sid+"\"><collectdtmf l=\"2\" t=\"#\" o=\"5000\"><playtext>Please enter one to select and two to reject. Press hash to submit</playtext>"
		+ "</collectdtmf></response>";
				
			
		return abc;
		
	}
	
}
