package com.lovethefeel.profile.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "message.api")
public final class MessageConfig {

    private String code;
    private String name;
}
