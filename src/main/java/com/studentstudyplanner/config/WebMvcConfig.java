package com.studentstudyplanner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	 private final LoggingInterceptor loggingInterceptor;

	    public WebMvcConfig(LoggingInterceptor loggingInterceptor) {
	        this.loggingInterceptor = loggingInterceptor;
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(loggingInterceptor)
	                .addPathPatterns("/", "/register", "/login", "/logout",
	                		"/courses", "/courses/**", "/assignments", "/assignments/**",
	                		"/tasks", "/tasks/**", "/notes", "/notes/**");
	    }
}