package com.lovethefeel.webflux.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lovethefeel.webflux.user.domain.MobileAgency;
import com.lovethefeel.webflux.user.domain.MobilePhone;
import com.lovethefeel.webflux.user.domain.Sex;
import com.lovethefeel.webflux.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class UserRequest {

    @NotBlank(message = "사용자 아이디는 입력해야 합니다.")
    @JsonProperty("user_id")
    private String userId;

    @NotNull(message = "사용자 이름은 입력해야 합니다.")
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("sex")
    private Sex sex;

    @JsonProperty("mobile_agency")
    private MobileAgency mobileAgency;

    @JsonProperty("phone1")
    private String phone1;

    @JsonProperty("phone2")
    private String phone2;

    @JsonProperty("phone3")
    private String phone3;

    public static UserRequest from(final String userId, final String userName, final Sex sex, final MobileAgency mobileAgency, final String phone1, final String phone2, final String phone3) {
        return new UserRequest(userId, userName, sex, mobileAgency, phone1, phone2, phone3);
    }

    public User toEntity() {
        final MobilePhone mobilePhone = MobilePhone.from(this.mobileAgency, this.phone1, this.phone2, this.phone3);
        return User.from(this.userId, this.userName, this.sex, mobilePhone);
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", mobileAgency=" + mobileAgency +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", phone3='" + phone3 + '\'' +
                '}';
    }
}
