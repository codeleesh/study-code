package com.lovethefeel.webflux.user.acceptance;

import com.lovethefeel.webflux.AcceptanceTest;
import com.lovethefeel.webflux.fixture.TestUserFactory;
import com.lovethefeel.webflux.user.domain.MobileAgency;
import com.lovethefeel.webflux.user.domain.Sex;
import com.lovethefeel.webflux.user.dto.UserRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("사용자 관련 인수 테스트")
class UserAcceptanceTest extends AcceptanceTest {

    @DisplayName("사용자 등록한다.")
    @Test
    void saveUser() {
        final UserRequest 사용자_요청 = TestUserFactory.사용자_등록_요청("test", "test", Sex.MALE, MobileAgency.SK, "010", "1111", "2222");

        final ExtractableResponse<Response> 사용자_응답 = TestUserFactory.사용자_등록_요청함(사용자_요청);

        TestUserFactory.사용자_등록_생성됨(사용자_응답);
    }
}
