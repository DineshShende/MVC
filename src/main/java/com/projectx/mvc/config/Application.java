package com.projectx.mvc.config;

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

    
    @Bean
    public RestTemplate restTemplate() throws IOException
    {
    	  	
    	RestTemplate restTemplate=new RestTemplate();
    	
    	return restTemplate;
    }
    
   
    
    
}