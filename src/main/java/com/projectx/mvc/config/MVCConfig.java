package com.projectx.mvc.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.projectx")
@EnableAutoConfiguration

public class MVCConfig extends WebMvcAutoConfigurationAdapter {

}
