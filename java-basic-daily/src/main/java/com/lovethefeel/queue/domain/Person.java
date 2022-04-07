package com.lovethefeel.queue.domain;

import java.util.Objects;

/**
 * 사용자 관리 객체
 */
public class Person {
    /**
     * id
     */
    private Long id;
    /**
     * 이름
     */
    private String name;

    /**
     * 기본 생성자
     * @param id
     * @param name
     */
    private Person(final Long id, final String name) {
        validate(id, name);
        this.id = id;
        this.name = name;
    }

    /**
     * 기본 필드 검증
     * @param id
     * @param name
     */
    private void validate(final Long id, final String name) {
        if (Objects.isNull(id) || id == 0) {
            throw new IllegalArgumentException("id 정보가 없습니다.");
        }
        if (Objects.isNull(name) || "".equals(name)) {
            throw new IllegalArgumentException("이름이 없습니다.");
        }
    }

    /**
     * 사용자 생성 정적 메소드
     * @param id
     * @param name
     * @return
     */
    public static Person of(final Long id, final String name) {
        return new Person(id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
