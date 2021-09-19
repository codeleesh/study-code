package com.lovethefeel.springboot.common.enums;

import lombok.Getter;

import java.util.Random;

@Getter
public enum Sexs {
    MALE("남성"),
    FEMALE("여성");

    private final String sex;

    Sexs(String sex) {
        this.sex = sex;
    }

    public String getValue() {
        return this.sex;
    }

    public static Sexs getRandom() {
        return Sexs.values()[new Random().nextInt(Sexs.values().length)];
    }
}
