package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.dto.user.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcUserRepositoryTest {

    @Autowired
    private JdbcUserRepository jdbcUserRepository;

    @Test
    @Transactional
    void 대량_저장() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        int max_count = 10;

        List<UserRequest> userList = new ArrayList<>();
        for (int i = 0; i < max_count; i++) {
            UserRequest user = UserRequest.builder()
                    .userId("user"+i)
                    .userName("name"+i)
                    .count(max_count)
                    .build();
            userList.add(user);
        }
        jdbcUserRepository.saveAll(userList);

        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("total time : " + totalTimeMillis);
    }
}
