package me.lovethefeel.profile.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class MessageConfigTest {

    @Autowired
    private MessageConfig messageConfig;

    @DisplayName("생성자 주입 방식을 이용해서 properties 값을 확인한다.")
    @Test
    void messageConfigTest() {
        // then
        assertThat(messageConfig.getCode()).isEqualTo("app");
        assertThat(messageConfig.getName()).isEqualTo("external-api");
    }
}