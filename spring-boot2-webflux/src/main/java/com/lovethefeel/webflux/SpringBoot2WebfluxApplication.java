package com.lovethefeel.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBoot2WebfluxApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2WebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.trace("여기는 Trace 입니다.");
        log.debug("이곳은 debug 입니다.");
        log.info("여기는 info 입니다.");
        log.warn("여기는 warn 입니다.");
        log.error("여기는 error 입니다.");
    }
}
