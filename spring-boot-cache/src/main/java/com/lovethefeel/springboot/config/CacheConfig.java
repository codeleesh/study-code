package com.lovethefeel.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@Slf4j
public class CacheConfig {

    @Bean
    public CacheManager cacheManger() {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        log.info("CacheManager cacheManger [{}]", cacheManager.getCacheNames());
        return cacheManager;
    }
}
