package com.lovethefeel.springboot.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class UserSequenceTest {

    @Autowired
    JacksonTester<UserSequence> userJson;

    @Test
    void user_json_테스트() throws IOException {

        // given
        UserSequence userSequence = UserSequence.builder()
                .name("lovethefeel")
                .build();

        String content = "{\"name\":\"lovethefeel\"}";

        assertThat(this.userJson.parseObject(content).getName()).isEqualTo(userSequence.getName());
    }
}
