package com.projectx.mvc.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

@ComponentScan(basePackages="com.projectx")
@EnableAutoConfiguration

public class BasicConfig  {
	
	@Bean
    public RestTemplate restTemplate() throws IOException
    {
       	RestTemplate restTemplate=new RestTemplate();
    
    	return restTemplate;
    }
	
	@Bean
	public Gson gson()
	{
		return new Gson();
	}

}
