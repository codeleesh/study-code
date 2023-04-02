package me.lovethefeel;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("header", "header1", "header2");
    }
}
