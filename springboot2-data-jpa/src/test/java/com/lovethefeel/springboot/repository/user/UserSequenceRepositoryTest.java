package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.domain.user.UserSequence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserSequenceRepositoryTest {

    @Autowired
    private SpringDataJpaUserSequenceRepository springDataJpaUserSequenceRepository;

    @Test
    @Transactional
    public void 사용자_저장_조회() {

        // given
        UserSequence userSequence = UserSequence.builder()
                .name("이름")
                .build();
        Long id = springDataJpaUserSequenceRepository.save(userSequence).getId();

        // when
        UserSequence result = springDataJpaUserSequenceRepository.findById(id).orElse(null);
        // then
        Assertions.assertThat(userSequence).isEqualTo(result);
    }

    @Test
    @Transactional
    public void 대량_저장() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<UserSequence> userSequenceList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserSequence userSequence = new UserSequence();
            userSequenceList.add(userSequence);
        }
        springDataJpaUserSequenceRepository.saveAll(userSequenceList);

        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("total time : " + totalTimeMillis);
    }

}
