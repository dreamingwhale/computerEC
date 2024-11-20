package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo.controller", "com.example.demo.config"}) 
public class DemoApplication {

    public static void main(String[] args) {
    	System.setProperty("file.encoding", "UTF-8");
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
