package me.lovethefeel.github;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

public class GithubFeignClientConfig {

    @Bean
    Logger.Level githubFeignClientLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new GithubFeignError();
    }

    /**
     * Java 8 이상에서 설정
     * @return
     */
    @Bean
    public FeignFormatterRegistrar localDateFeignFormatterRegister() {
        return registry -> {
            final DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
        };
    }
}
