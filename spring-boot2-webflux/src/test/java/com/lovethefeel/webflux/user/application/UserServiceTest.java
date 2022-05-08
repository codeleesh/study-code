package com.lovethefeel.webflux.user.application;

import com.lovethefeel.webflux.fixture.TestUserFactory;
import com.lovethefeel.webflux.user.domain.MobileAgency;
import com.lovethefeel.webflux.user.domain.MobilePhone;
import com.lovethefeel.webflux.user.domain.Sex;
import com.lovethefeel.webflux.user.domain.User;
import com.lovethefeel.webflux.user.dto.UserRequest;
import com.lovethefeel.webflux.user.dto.UserResponse;
import com.lovethefeel.webflux.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("사용자 생성 관련 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @DisplayName("사용자를 등록한다.")
    @Test
    void saveUser() {
        final MobilePhone 모바일 = MobilePhone.from(MobileAgency.SK, "010", "1111", "2222");
        final UserRequest 사용자_요청 = TestUserFactory.사용자_등록_요청("test", "test", Sex.MALE, MobileAgency.SK, "010", "1111", "2222");
        final User 사용자_생성_기대값 = TestUserFactory.사용자(1L, "test", "test", Sex.MALE, 모바일);

        given(userRepository.save(any())).willReturn(사용자_생성_기대값);

        final UserResponse 사용자_생성_응답값 = userService.saveUser(사용자_요청);

        TestUserFactory.사용자_등록_생성됨(사용자_생성_응답값, 사용자_생성_기대값);
    }
}