package me.lovethefeel.member.domain;

import java.io.Serializable;

public class Address implements Serializable {

    private static final long serialVersionUID = -5147813911539128566L;

    private String city;
    private String state;
    private String zipCode;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
