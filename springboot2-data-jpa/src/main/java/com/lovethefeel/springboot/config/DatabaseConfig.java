package com.lovethefeel.springboot.config;

import com.lovethefeel.springboot.repository.user.SpringDataJpaUserIdentityRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    final SpringDataJpaUserIdentityRepository springDataJpaUserIdentityRepository;

    public DatabaseConfig(SpringDataJpaUserIdentityRepository springDataJpaUserIdentityRepository) {
        this.springDataJpaUserIdentityRepository = springDataJpaUserIdentityRepository;
    }
}
