package com.lovethefeel.springboot.config;

import com.lovethefeel.springboot.repository.user.SpringDataJpaUserIdentityRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    private final SpringDataJpaUserIdentityRepository springDataJpaUserIdentityRepository;

    public DatabaseConfig(SpringDataJpaUserIdentityRepository springDataJpaUserIdentityRepository) {
        this.springDataJpaUserIdentityRepository = springDataJpaUserIdentityRepository;
    }


//    private EntityManager em;
//
//    @Autowired
//    public DatabaseConfig(EntityManager em) {
//        this.em = em;
//    }
//
//    @Bean
//    public UserRepository userRepository() {
//        return new SpringDataJpaUserRepository(em);
//    }
}
