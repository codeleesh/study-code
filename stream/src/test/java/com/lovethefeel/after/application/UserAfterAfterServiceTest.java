package com.lovethefeel.after.application;

import com.lovethefeel.after.domain.UserAfter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class UserAfterAfterServiceTest {

    @Autowired
    private UserAfterService userService;

    @DisplayName("취미가 당구인 회원만 조회")
    @Test
    void findByHobbyTest() {
        final Set<String> hobbies = new HashSet<>();
        hobbies.add("당구");
        hobbies.add("볼링");
        hobbies.add("야구");

        final UserAfter user = UserAfter.of("LEE", hobbies, "Y");
        final UserAfter saveUser = userService.saveUser(user);

        final List<UserAfter> users = userService.findByHobby("당구");

        assertAll(
                () -> assertThat(users).hasSize(1),
                () -> assertThat(users.get(0).getHobby()).isEqualTo(user.getHobby())
        );
    }
}
