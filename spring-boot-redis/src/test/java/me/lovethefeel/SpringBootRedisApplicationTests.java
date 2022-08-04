package me.lovethefeel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SpringBootRedisApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(UUID.randomUUID());
    }

}
