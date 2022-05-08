package com.lovethefeel.webflux.user.domain;

import lombok.Getter;

@Getter
public enum Sex {
    MALE("님성"),
    FEMALE("여성");

    private String name;

    Sex(String name) {
        this.name = name;
    }
}
