package me.lovethefeel.config;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

public class RedisLettuceConfig {

    public LettuceConnectionFactory createLettuceConnectionFactory(int database, String host, int port) {

        RedisStandaloneConfiguration redistStandaloneConfiguration = new RedisStandaloneConfiguration();
        redistStandaloneConfiguration.setDatabase(database);
        redistStandaloneConfiguration.setHostName(host);
        redistStandaloneConfiguration.setPort(port);

        return new LettuceConnectionFactory(redistStandaloneConfiguration);
    }
}
