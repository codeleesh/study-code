package com.lovethefeel.webflux.user.repository;

import com.lovethefeel.webflux.user.domain.MobileAgency;
import com.lovethefeel.webflux.user.domain.MobilePhone;
import com.lovethefeel.webflux.user.domain.Sex;
import com.lovethefeel.webflux.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("사용자를 저장를 할 수 있다.")
    @Test
    void 사용자_저장() {
        final User 테스트 = User.from("test", "test", Sex.MALE, MobilePhone.from(MobileAgency.SK, "011", "1111", "2222"));

        final User 테스트저장 = userRepository.save(테스트);

        User 테스트조회 = userRepository.findById(1L)
                .orElseThrow(IllegalArgumentException::new);

        assertThat(테스트저장).isEqualTo(테스트조회);
    }

}