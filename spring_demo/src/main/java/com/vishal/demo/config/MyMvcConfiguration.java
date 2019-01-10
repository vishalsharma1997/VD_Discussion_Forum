package com.vishal.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("POST","PUT","GET","DELETE","OPTIONS","PATCH")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }
}
