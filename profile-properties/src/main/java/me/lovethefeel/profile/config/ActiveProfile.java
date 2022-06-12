package me.lovethefeel.profile.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ActiveProfile {

    @Value("${server.port}")
    private int port;

    @Value("${spring.config.activate.on-profile}")
    private String profile;

    @Value("${spring.main.banner-mode}")
    private String bannerMode;

    @Value("${external.api.url}")
    private String externalApiUrl;
}
