package com.lovethefeel.webflux.user.ui;

import com.lovethefeel.webflux.fixture.TestUserFactory;
import com.lovethefeel.webflux.user.application.UserService;
import com.lovethefeel.webflux.user.domain.MobileAgency;
import com.lovethefeel.webflux.user.domain.MobilePhone;
import com.lovethefeel.webflux.user.domain.Sex;
import com.lovethefeel.webflux.user.dto.UserRequest;
import com.lovethefeel.webflux.user.dto.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @DisplayName("사용자 등록한다.")
    @Test
    void createUserTest() {
        final UserRequest 사용자_요청 = TestUserFactory.사용자_등록_요청("test", "test", Sex.MALE, MobileAgency.SK, "010", "1111", "2222");
        final UserResponse 사용자_응답 = TestUserFactory.사용자_등록_응답(1L, "test", "test", Sex.MALE, MobilePhone.from(MobileAgency.SK, "010", "1111", "2222"));

        given(this.userService.saveUser(any()))
                .willReturn(사용자_응답);

        this.webTestClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(사용자_요청))
                .exchange()
                .expectAll(
                        responseSpec -> responseSpec.expectStatus().isCreated(),
                        responseSpec -> responseSpec.expectHeader().contentType(MediaType.APPLICATION_JSON)
                )
                .expectBody()
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.user_id").isEqualTo("test")
                .jsonPath("$.user_name").isEqualTo("test")
                .jsonPath("$.sex").isEqualTo("MALE")
                .jsonPath("$.mobile_phone").isNotEmpty()
                .jsonPath("$.mobile_phone.mobile_agency").isEqualTo("SK")
                .jsonPath("$.mobile_phone.phone1").isEqualTo("010")
                .jsonPath("$.mobile_phone.phone2").isEqualTo("1111")
                .jsonPath("$.mobile_phone.phone3").isEqualTo("2222");
    }
}