package com.lovethefeel.webflux;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@SpringBootApplication
public class SpringBoot2WebfluxApplication implements CommandLineRunner {

    private final Logger jsonLogger = LoggerFactory.getLogger("json");

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

        jsonLogger.info(MarkerFactory.getMarker("jsonTags"), "{}, {}",
                kv("출발지", "검암"),
                kv("도착지", "김포공항")
        );

        try {
            String str = null;
            str.substring(1, 2);
        } catch (Exception e) {
            jsonLogger.error(MarkerFactory.getMarker("jsonTags"), "{}", e);
        }
    }
}
