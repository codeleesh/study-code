package com.lovethefeel.springboot.common.enums;

public enum Sex {
    MALE("남성"),
    FEMALE("여성");

    private String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return this.sex;
    }

    
}
