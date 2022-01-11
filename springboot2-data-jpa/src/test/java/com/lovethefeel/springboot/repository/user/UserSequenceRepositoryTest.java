package com.lovethefeel.springboot.repository.user;

import com.lovethefeel.springboot.domain.user.UserSequence;
import com.lovethefeel.springboot.repository.usersequence.UserSequenceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserSequenceRepositoryTest {

    @Autowired
    private UserSequenceRepository userSequenceRepository;

    @Test
    void 사용자_저장_조회() {

        // given
        UserSequence userSequence = UserSequence.builder()
                .name("이름")
                .build();
        Long id = userSequenceRepository.save(userSequence).getId();

        // when
        UserSequence result = userSequenceRepository.findById(id).orElse(null);
        // then
        Assertions.assertThat(userSequence).isEqualTo(result);
    }

    @Test
    void 대량_저장() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<UserSequence> userSequenceList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserSequence userSequence = new UserSequence();
            userSequenceList.add(userSequence);
        }
        userSequenceRepository.saveAll(userSequenceList);

        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("total time : " + totalTimeMillis);
    }

}
