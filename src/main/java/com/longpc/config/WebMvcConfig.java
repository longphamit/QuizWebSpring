package com.longpc.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.longpc"})
public class WebMvcConfig implements WebMvcConfigurer{
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}
}
