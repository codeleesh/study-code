package com.lovethefeel.webflux.ui;

import com.lovethefeel.webflux.application.UserService;
import com.lovethefeel.webflux.domain.MobileAgency;
import com.lovethefeel.webflux.domain.MobilePhone;
import com.lovethefeel.webflux.domain.Sex;
import com.lovethefeel.webflux.dto.UserRequest;
import com.lovethefeel.webflux.dto.UserResponse;
import com.lovethefeel.webflux.fixture.TestUserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.BodyInserters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @DisplayName("사용자 등록한다.")
    @Test
    void createUserTest() {
        final UserRequest 사용자요청 = TestUserFactory.사용자_요청("test", "test", Sex.MALE, MobileAgency.SK, "010", "1111", "2222");
        final UserResponse 사용자응답 = TestUserFactory.사용자_응답(1L, "test", "test", Sex.MALE, MobilePhone.from(MobileAgency.SK, "010", "1111", "2222"));

        given(this.userService.saveUser(any()))
                .willReturn(사용자응답);

        this.webTestClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(사용자요청))
                .exchange()
                .expectStatus().isCreated();
    }
}