package com.lovethefeel.profile.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ActiveProfile {

    @Value("${server.port}")
    private int port;

    @Value("${spring.config.activate.on-profile}")
    private String profile;

    @Value("${external.api.url}")
    private String externalApiUrl;
}
