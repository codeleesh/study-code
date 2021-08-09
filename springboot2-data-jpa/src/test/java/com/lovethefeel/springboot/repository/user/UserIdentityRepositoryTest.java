package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.domain.user.UserIdentity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserIdentityRepositoryTest {

    @Autowired
    private SpringDataJpaUserIdentityRepository springDataJpaUserIdentityRepository;

    @Test
    @Transactional
    public void 사용자_저장_조회() {

        // given
        UserIdentity userIdentity = UserIdentity.builder()
                .name("이름")
                .build();
        Long id = springDataJpaUserIdentityRepository.save(userIdentity).getId();

        // when
        UserIdentity result = springDataJpaUserIdentityRepository.findById(id).orElse(null);
        // then
        Assertions.assertThat(userIdentity).isEqualTo(result);
    }

    @Test
    @Transactional
    public void 대량_저장() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<UserIdentity> userIdentityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserIdentity userIdentity = new UserIdentity();
            userIdentityList.add(userIdentity);
        }
        springDataJpaUserIdentityRepository.saveAll(userIdentityList);

        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("total time : " + totalTimeMillis);
    }

}
