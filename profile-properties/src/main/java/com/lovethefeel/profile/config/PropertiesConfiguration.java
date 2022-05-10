package com.lovethefeel.profile.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {MessageConfig.class})
public class PropertiesConfiguration {
}
