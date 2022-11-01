package me.lovethefeel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SampleCircuitbreakerApplication {

    public static void main(String[] args) {

        SpringApplication.run(SampleCircuitbreakerApplication.class, args);
    }
}
