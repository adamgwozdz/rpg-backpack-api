package com.rpgbackpack.rpgbackpack;

import filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RpgBackpackApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpgBackpackApiApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns("/api/sessions/*");
        return registrationBean;
    }
}
