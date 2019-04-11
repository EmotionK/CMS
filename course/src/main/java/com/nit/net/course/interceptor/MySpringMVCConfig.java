package com.nit.net.course.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class MySpringMVCConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**/**").
		excludePathPatterns("/administrator/login.do","/teacher/login.do","/administrator/reg.do","/course/html/login.html","/course/html/administrator_reg.html","/course/css/**","/course/js/**","/course/img/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	
	
	

	
}
