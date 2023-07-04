package com.szyperek.tasklist.util;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestCountConfig {
    @Bean
    public FilterRegistrationBean<RequestCountFilter> registerRequestCounterFilter() {
        FilterRegistrationBean<RequestCountFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestCountFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
