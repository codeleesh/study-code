package com.lovethefeel.springboot;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling     // 스케쥴러 기능 활성화
@EnableBatchProcessing  // 배치 기능 활성화
@SpringBootApplication
public class Springboot2BatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2BatchApplication.class, args);
    }

}
