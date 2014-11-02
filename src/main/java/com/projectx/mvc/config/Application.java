package com.projectx.mvc.config;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages="com.projectx")
@EnableAutoConfiguration
@EnableWebMvc

@Import(value=BasicConfig.class)
public class Application {
//AAAAAAAAAAAABBBBB
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
/*
    
    @Bean
    public RestTemplate restTemplate() throws IOException
    {
    	  	
    	RestTemplate restTemplate=new RestTemplate();
    	
    	return restTemplate;
    }
 */   
    @Bean
    public RestTemplate restTemplate() throws IOException
    {
       	RestTemplate restTemplate=new RestTemplate();
    
    	return restTemplate;
    }
    
//   public MethodValidationPostProcessor methodValidationPostProcessor()
//   {
//	   MethodValidationPostProcessor methodValidationPostProcessor=new MethodValidationPostProcessor();
//	 //  methodValidationPostProcessor.setValidatorFactory(validatorFactory);
//	
//	   return methodValidationPostProcessor;
//   }
   
  
    
}