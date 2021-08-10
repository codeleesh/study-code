package com.lovethefeel.springboot2.domain.enums;

public enum BoardType {
    notice("공지사항"),
    free("자유게시판");

    private final String value;

    BoardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
