package com.lovethefeel.method;

import com.lovethefeel.method.application.SubwayServiceImpl;
import com.lovethefeel.method.dto.SubwayRequest;
import com.lovethefeel.method.dto.SubwayResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class SubwayServiceImplTest {
    @DisplayName("전철을 등록합니다.")
    @Test
    void saveSubwayTest() {
        // given
        final String subwayName = "잠실";
        final SubwayRequest subwayRequest = SubwayRequest.from(subwayName);

        // when
        final SubwayServiceImpl subwayService = new SubwayServiceImpl();
        final SubwayResponse SubwayResponse = subwayService.saveSubway(subwayRequest);

        // then
        assertThat(SubwayResponse.getName()).isEqualTo(subwayName);
    }

    @DisplayName("전철 저장시 NullPointerException 발생합니디.")
    @Test
    void saveSubway_exception() {
        // givenD
        final SubwayServiceImpl subwayService = new SubwayServiceImpl();
        final String subwayName = "잠실";
        final SubwayRequest subwayRequest = SubwayRequest.from(subwayName);

        // when
        assertThatThrownBy(() -> subwayService.saveSubway(subwayRequest))
                .isInstanceOf(NullPointerException.class);
    }
}
