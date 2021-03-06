package com.projectx.mvc.controller.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projectx.mvc.domain.request.SessionData;
import com.projectx.mvc.domain.request.SessionTest;

@Controller
//@RequestMapping("/sessionTest")
public class SessionTestController {

	@Autowired
	SessionTest sessionTest;
	
//	
//	@RequestMapping(value="/dateForm",method=RequestMethod.GET)
//	public String dateForm()
//	{
//		return "dateForm";
//		
//	}
//	
//	@RequestMapping(value="/datesubmit",method=RequestMethod.POST)
//	public NameDateDTO nameDateDTO(@RequestBody NameDateDTO nameDateDTO)
//	{
//		return nameDateDTO;
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String sessionForm()
	{
		return "sessionForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String sessionSave(@ModelAttribute SessionData sessionTest1,Model model)
	{
		
		//SessionTest newSessionTest=new SessionTest(sessionTest1.getName(), sessionTest1.getPassword());
		
		//sessionTest.setName(name);
		
		//System.out.println(newSessionTest);
		
		sessionTest.setSessionData(sessionTest1);
		
		model.addAttribute("sessionData", sessionTest.getSessionData());
		
		return "showSession";
	}
	
	@RequestMapping(value="/redirect",method=RequestMethod.GET)
	public String redirect(Model model)
	{
		//SessionTest newSessionTest=new SessionTest(sessionTest.getName(), sessionTest.getPassword());
		
		model.addAttribute("sessionData", sessionTest.getSessionData());
		
		return "showSessionRedirect";
	}
	
}
