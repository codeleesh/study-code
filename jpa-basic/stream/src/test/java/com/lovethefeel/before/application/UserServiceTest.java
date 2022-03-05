package com.lovethefeel.before.application;

import com.lovethefeel.before.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @DisplayName("취미가 당구인 회원만 조회")
    @Test
    void findByHobbyTest() {
        final User user = User.of("LEE", "당구,볼링,야구", "Y");
        final User saveUser = userService.saveUser(user);

        final List<User> users = userService.findByHobby("당구");

        assertAll(
                () -> assertThat(users).hasSize(1),
                () -> assertThat(users.get(0).getHobby()).isEqualTo(user.getHobby())
        );
    }
}
