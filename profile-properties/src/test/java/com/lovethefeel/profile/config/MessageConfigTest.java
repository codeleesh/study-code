package com.lovethefeel.profile.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class MessageConfigTest {

    @Autowired
    private MessageConfig messageConfig;

    @DisplayName("생성자 주입 방식을 이용해서 properties 값을 확인한다.")
    @Test
    void messageConfigTest() {
        // then
        assertThat(messageConfig.getApi().getCode()).isEqualTo("app");
        assertThat(messageConfig.getApi().getName()).isEqualTo("external-api");
    }
}