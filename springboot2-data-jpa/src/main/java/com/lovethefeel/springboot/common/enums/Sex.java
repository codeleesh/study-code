package com.lovethefeel.springboot.common.enums;

import lombok.Getter;

import java.util.Random;

@Getter
public enum Sex {
    MALE("남성"),
    FEMALE("여성");

    final private String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getValue() {
        return this.sex;
    }

    public static Sex getRandom() {
        return Sex.values()[new Random().nextInt(Sex.values().length)];
    }
}
