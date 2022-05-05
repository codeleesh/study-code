package com.lovethefeel.webflux.fixture;

import com.lovethefeel.webflux.domain.MobileAgency;
import com.lovethefeel.webflux.domain.MobilePhone;
import com.lovethefeel.webflux.domain.Sex;
import com.lovethefeel.webflux.domain.User;
import com.lovethefeel.webflux.dto.MobilePhoneResponse;
import com.lovethefeel.webflux.dto.UserRequest;
import com.lovethefeel.webflux.dto.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestUserFactory {
    public static UserRequest 사용자_요청(final String userId, final String userName, final Sex sex, final MobileAgency mobileAgency, final String phone1, final String phone2, final String phone3) {
        return UserRequest.from(userId, userName, sex, mobileAgency, phone1, phone2, phone3);
    }

    public static User 사용자_생성됨(final Long id, final String userId, final String userName, final Sex sex, final MobilePhone mobilePhone) {
        return User.from(id, userId, userName, sex, mobilePhone);
    }

    public static void 사용자_생성됨(UserResponse actual, User expected) {
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isEqualTo(expected.getId()),
                () -> assertThat(actual.getUserId()).isEqualTo(expected.getUserId()),
                () -> assertThat(actual.getUserName()).isEqualTo(expected.getUserName()),
                () -> assertThat(actual.getSex()).isEqualTo(expected.getSex()),
                () -> assertThat(actual.getMobilePhone().getMobileAgency()).isEqualTo(expected.getMobilePhone().getMobileAgency()),
                () -> assertThat(actual.getMobilePhone().getPhone1()).isEqualTo(expected.getMobilePhone().getPhone1()),
                () -> assertThat(actual.getMobilePhone().getPhone2()).isEqualTo(expected.getMobilePhone().getPhone2()),
                () -> assertThat(actual.getMobilePhone().getPhone3()).isEqualTo(expected.getMobilePhone().getPhone3())
        );
    }

    public static UserResponse 사용자_응답(Long id, String userId, String userName, Sex sex, MobilePhone mobilePhone) {
        return UserResponse.from(id, userId, userName, sex, mobilePhone);
    }
}
