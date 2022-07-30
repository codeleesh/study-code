package me.lovethefeel.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig extends RedisLettuceConfig {

    private int database;
    private String host;
    private int port;

    @Bean
    public RedisConnectionFactory CIRedisConnectionFactory() {
        final LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(host, port);
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate() {

        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(CIRedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
