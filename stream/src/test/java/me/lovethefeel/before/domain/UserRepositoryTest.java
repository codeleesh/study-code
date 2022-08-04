package me.lovethefeel.before.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("사용자 저장")
    @Test
    void saveUser() {
        final User user = User.of("LEE", "당구,볼링,야구", "Y");
        final User saveUser = userRepository.save(user);

        final User findUser = userRepository.findById(saveUser.getId()).get();

        assertThat(saveUser).isEqualTo(findUser);
    }
}
