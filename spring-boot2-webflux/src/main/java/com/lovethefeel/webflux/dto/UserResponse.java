package com.lovethefeel.webflux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lovethefeel.webflux.domain.MobilePhone;
import com.lovethefeel.webflux.domain.Sex;
import com.lovethefeel.webflux.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Getter
public class UserResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("sex")
    private Sex sex;

    @JsonProperty("mobile_phone")
    private MobilePhoneResponse mobilePhone;

    public static UserResponse of(final User user) {
        final MobilePhone mobilePhone = user.getMobilePhone();
        return new UserResponse(user.getId(), user.getUserId(), user.getUserName(), user.getSex(), MobilePhoneResponse.of(mobilePhone));
    }

    public static UserResponse from(final Long id, final String userId, final String userName, final Sex sex, final MobilePhone mobilePhone) {
        MobilePhoneResponse mobilePhoneResponse = MobilePhoneResponse.of(mobilePhone);
        return new UserResponse(id, userId, userName, sex, mobilePhoneResponse);
    }
}
