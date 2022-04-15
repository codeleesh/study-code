package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public static Address createAddress(final String city, final String street, final String zipcode) {
        return new Address(city, street, zipcode);
    }
}
