package com.projectx.mvc.config;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@ComponentScan(basePackages="com.projectx")
@EnableAutoConfiguration
@EnableWebMvc

@Import(value={BasicConfig.class,MVCConfig.class})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    
    		return application.sources(Application.class);
   }
    
    @Bean
    public RestTemplate restTemplate() throws IOException
    {
       	RestTemplate restTemplate=new RestTemplate();
    
    	return restTemplate;
    }
    
    
    @Bean
    public SimpleDateFormat simpleDateFormat()
    {
    	SimpleDateFormat simpleDateFormat=new  SimpleDateFormat("MM-dd-yyyy");
    	
    	return simpleDateFormat;
    }
    
   /* 
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new ImageServlet(),"dc");
    }
   
    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {

        ServletRegistrationBean registration = new ServletRegistrationBean(
        		new ImageServlet(), "/levelup/*");

        
        return registration;
    }

    
    @Bean
    public CommonsMultipartResolver multipartResolver()
    {
    	return new CommonsMultipartResolver();
    }
    */
//   public MethodValidationPostProcessor methodValidationPostProcessor()
//   {
//	   MethodValidationPostProcessor methodValidationPostProcessor=new MethodValidationPostProcessor();
//	 //  methodValidationPostProcessor.setValidatorFactory(validatorFactory);
//	
//	   return methodValidationPostProcessor;
//   }
   
  
    
}