package com.lovethefeel.profile.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "message")
public final class MessageConfig {
    private final Api api;

    @Getter
    @RequiredArgsConstructor
    public static class Api {
        private final String code;
        private final String name;
    }
}
