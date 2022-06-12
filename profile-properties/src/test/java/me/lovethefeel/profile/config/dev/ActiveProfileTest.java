package me.lovethefeel.profile.config.dev;

import me.lovethefeel.profile.config.ActiveProfile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class ActiveProfileTest {

    @Autowired
    private ActiveProfile activeProfile;

    @DisplayName("공통으로 설정된 port 번호를 확인한다.")
    @Test
    void serverPortTest() {
        // then
        assertThat(activeProfile.getPort()).isEqualTo(8080);
    }

    @DisplayName("공통 설정으로 배너모드는 콘솔이지만 dev에서는 off 처리한다.")
    @Test
    void bannerModeTest() {
        // then
        assertThat(activeProfile.getBannerMode()).isEqualTo("false");
    }

    @DisplayName("현재 설정된 active profile의 application 설정값을 확인한다.")
    @Test
    void currentProfileTest() {
        // then
        assertThat(activeProfile.getProfile()).isEqualTo("dev");
    }

    @DisplayName("현재 설정된 active profile의 분리된 설정 파일을 임포트하여서 값을 확인한다.")
    @Test
    void externalApiUrl() {
        // then
        assertThat(activeProfile.getExternalApiUrl()).isEqualTo("http://dev-host:8080/api");
    }
}