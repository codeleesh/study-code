package com.lovethefeel.springboot.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class UserTest {

    @Autowired
    JacksonTester<User> userJson;

    @Test
    void user_json_테스트() throws IOException {

        // given
        User user = User.builder()
                .userId("lovethefeel")
                .userName("퐁당")
                .count(10)
                .build();

        String content = "{\"userId\":\"lovethefeel\", \"userName\":\"퐁당\", \"count\":10}";

        assertThat(this.userJson.parseObject(content).getUserId()).isEqualTo(user.getUserId());
    }
}
