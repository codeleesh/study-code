package com.lovethefeel.springboot.config;

import com.lovethefeel.springboot.repository.user.UserIdentityRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    final UserIdentityRepository userIdentityRepository;

    public DatabaseConfig(UserIdentityRepository userIdentityRepository) {
        this.userIdentityRepository = userIdentityRepository;
    }
}
