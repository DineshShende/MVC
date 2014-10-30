package com.projectx.mvc.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages="com.projectx")
@EnableAutoConfiguration
public class BasicConfig {
	
	@Bean
    public RestTemplate restTemplate() throws IOException
    {
       	RestTemplate restTemplate=new RestTemplate();
    
    	return restTemplate;
    }

}
