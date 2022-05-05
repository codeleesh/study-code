package com.lovethefeel.webflux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lovethefeel.webflux.domain.MobileAgency;
import com.lovethefeel.webflux.domain.MobilePhone;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

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
}
