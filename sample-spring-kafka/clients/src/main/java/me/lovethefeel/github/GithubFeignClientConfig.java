package me.lovethefeel.github;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class GithubFeignClientConfig {

    @Bean
    Logger.Level githubFeignClientLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new GithubFeignError();
    }
}
