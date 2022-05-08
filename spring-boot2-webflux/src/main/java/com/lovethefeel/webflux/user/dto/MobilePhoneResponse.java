package com.lovethefeel.webflux.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lovethefeel.webflux.user.domain.MobileAgency;
import com.lovethefeel.webflux.user.domain.MobilePhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Getter
public class MobilePhoneResponse {

    @JsonProperty("mobile_agency")
    private MobileAgency mobileAgency;

    private String phone1;

    private String phone2;

    private String phone3;

    public static MobilePhoneResponse from(final MobileAgency mobileAgency, final String phone1, final String phone2, final String phone3) {
        return new MobilePhoneResponse(mobileAgency, phone1, phone2, phone3);
    }

    public static MobilePhoneResponse of(final MobilePhone mobilePhone) {
        return new MobilePhoneResponse(mobilePhone.getMobileAgency(), mobilePhone.getPhone1(), mobilePhone.getPhone2(), mobilePhone.getPhone3());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobilePhoneResponse that = (MobilePhoneResponse) o;
        return mobileAgency == that.mobileAgency && Objects.equals(phone1, that.phone1) && Objects.equals(phone2, that.phone2) && Objects.equals(phone3, that.phone3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobileAgency, phone1, phone2, phone3);
    }

    @Override
    public String toString() {
        return "MobilePhoneResponse{" +
                "mobileAgency=" + mobileAgency +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", phone3='" + phone3 + '\'' +
                '}';
    }
}
