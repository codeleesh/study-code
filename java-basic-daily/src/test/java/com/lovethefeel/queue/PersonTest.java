package com.lovethefeel.queue;

import com.lovethefeel.queue.domain.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonTest {

    @DisplayName("이용자 생성 예외처리")
    @Test
    void 이용자_생성_이름없음_생성불가() {
        assertThrows(IllegalArgumentException.class, () ->
                Person.of(1L, "")
        );
    }
}
