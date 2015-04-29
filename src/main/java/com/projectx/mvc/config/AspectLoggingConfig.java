package com.projectx.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.projectx.mvc.util.aspect.LoggingAspect;



@Configuration
@EnableAspectJAutoProxy
public class AspectLoggingConfig {

	@Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

}
