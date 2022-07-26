package me.lovethefeel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "me.lovethefeel.member")
public class SpringBootJunit4MockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJunit4MockApplication.class, args);
    }

}
