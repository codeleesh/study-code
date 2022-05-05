package com.lovethefeel.webflux.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class MobilePhone {

    @Enumerated(STRING)
    private MobileAgency mobileAgency;

    @Column
    private String phone1;

    @Column
    private String phone2;

    @Column
    private String phone3;

    public static MobilePhone from(MobileAgency mobileAgency, String phone1, String phone2, String phone3) {
        return new MobilePhone(mobileAgency, phone1, phone2, phone3);
    }
}
