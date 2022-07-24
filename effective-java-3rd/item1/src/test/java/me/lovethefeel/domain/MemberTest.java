package me.lovethefeel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @DisplayName("사용자 초기 생성을 생성자를 이용한다.")
    @Test
    void 사용자_생성() {
        final Member member = new Member(1L, "lee", 20, "seoul");

        assertAll(
                () -> assertEquals(member.getId(), 1L),
                () -> assertEquals(member.getMemberName(), "lee"),
                () -> assertEquals(member.getAge(), 20),
                () -> assertEquals(member.getAddress(), "seoul")
        );
    }
}