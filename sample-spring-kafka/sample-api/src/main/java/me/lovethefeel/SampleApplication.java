package me.lovethefeel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @EnableFeignClients 설명
 * root package 에 있어야 하며, 그렇지 않은 경우 basePackages 또는 basePackageClasses 를 지정 필요
 * 지정된 package 를 돌아다니면서 @FeignClient 를 찾아 구현체를 만들어 줍니다.
 */
@EnableFeignClients
@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
