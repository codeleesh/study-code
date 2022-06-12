package me.lovethefeel.profile.config.local;

import me.lovethefeel.profile.config.ActiveProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class ActiveProfileTest {

    @Autowired
    private ActiveProfile activeProfile;

    @DisplayName("공통으로 설정된 port 번호를 확인한다.")
    @Test
    void serverPortTest() {
        // then
        assertThat(activeProfile.getPort()).isEqualTo(8080);
    }

    @DisplayName("현재 설정된 active profile의 application 설정값을 확인한다.")
    @Test
    void currentProfileTest() {
        // then
        assertThat(activeProfile.getProfile()).isEqualTo("local");
    }

    @DisplayName("현재 설정된 active profile의 분리된 설정 파일을 임포트하여서 값을 확인한다.")
    @Test
    void externalApiUrl() {
        // then
        assertThat(activeProfile.getExternalApiUrl()).isEqualTo("http://localhost:8080/api");
    }
}