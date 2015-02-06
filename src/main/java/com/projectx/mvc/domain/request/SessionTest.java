package com.projectx.mvc.domain.request;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionTest {

	private String name;
	
	private Integer password;

	public SessionTest() {

	}

	public SessionTest(String name, Integer password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SessionTest [name=" + name + ", password=" + password + "]";
	}
	
	
	
	/*
	private SessionData sessionData;

	public SessionTest() {

	}

	public SessionTest(SessionData sessionData) {
		super();
		this.sessionData = sessionData;
	}

	public SessionData getSessionData() {
		return sessionData;
	}

	public void setSessionData(SessionData sessionData) {
		this.sessionData = sessionData;
	}

	@Override
	public String toString() {
		return "SessionTest [sessionData=" + sessionData + "]";
	}
	
	*/
	
	
	
}
