package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.dto.user.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class JdbcUserRepositoryTest {

    @Autowired
    private JdbcUserRepository jdbcUserRepository;

    @Test
    @Transactional
    public void 대량_저장() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<UserRequest> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserRequest user = UserRequest.builder()
                    .userId("user"+i)
                    .userName("name"+i)
                    .count(10)
                    .build();
            userList.add(user);
        }
        jdbcUserRepository.saveAll(userList);

        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("total time : " + totalTimeMillis);
    }
}
