package com.lovethefeel.webflux.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lovethefeel.webflux.user.domain.MobilePhone;
import com.lovethefeel.webflux.user.domain.Sex;
import com.lovethefeel.webflux.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(userName, that.userName) && sex == that.sex && Objects.equals(mobilePhone, that.mobilePhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userName, sex, mobilePhone);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", mobilePhone=" + mobilePhone +
                '}';
    }
}
