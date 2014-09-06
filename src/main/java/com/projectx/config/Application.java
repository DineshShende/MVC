package com.projectx.config;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages="com.projectx")
@EnableAutoConfiguration
@EnableWebMvc

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    
    
    
   // @Bean 
    //HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory()
    {
    	//HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory=new HttpComponentsClientHttpRequestFactory();
    	
    	//return httpComponentsClientHttpRequestFactory;
    }
    
    @Bean
    public RestTemplate restTemplate() throws IOException
    {
    	//HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory=new HttpComponentsClientHttpRequestFactory();
    	
      	//httpComponentsClientHttpRequestFactory.createRequest(URI.create("http://localhost:8080/RestEvent/customer/checkemail"),HttpMethod.POST);
    	
    	RestTemplate restTemplate=new RestTemplate();
    	
    //	restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
    	
    	
    	return restTemplate;
    }
    
   
    
    
}