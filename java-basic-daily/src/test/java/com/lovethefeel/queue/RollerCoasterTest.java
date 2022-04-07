package com.lovethefeel.queue;

import com.lovethefeel.queue.domain.Person;
import com.lovethefeel.queue.domain.RollerCoaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class RollerCoasterTest {

    private Person 손오공;
    private Person 마이콜;
    private RollerCoaster 놀이기구;

    @BeforeEach
    void init() {
        손오공 = Person.of(1L, "손오공");
        마이콜 = Person.of(2L, "마이콜");
        놀이기구 = RollerCoaster.init();
        놀이기구.lineUpAdd(손오공);
        놀이기구.lineUpAdd(마이콜);
    }

    @Test
    void 회원이_줄을_선다_add() {
        // given
        Person 홍길동 = Person.of(1L, "홍길동");
        RollerCoaster 놀이기구 = RollerCoaster.init();

        // when
        boolean success = 놀이기구.lineUpAdd(홍길동);

        // then
        assertTrue(success);
    }

    @Test
    void 회원이_줄을_선다_offer() {
        // given
        Person 홍길동 = Person.of(1L, "홍길동");
        RollerCoaster 놀이기구 = RollerCoaster.init();

        // when
        boolean success = 놀이기구.lineUpOffer(홍길동);

        // then
        assertTrue(success);
    }

    @Test
    void 회원이_놀이공원_탑승한다_remove() {
        // given, when
        Person person = 놀이기구.lineOutRemove();

        // then
        assertEquals(person, 손오공);
    }

    @Test
    void 놀이공원_탑승할_회원이_없다_remove() {
        // given, when
        RollerCoaster 회원줄이없는놀이기구 = RollerCoaster.init();

        // then
        assertThrows(NoSuchElementException.class, 회원줄이없는놀이기구::lineOutRemove);
    }

    @Test
    void 회원이_놀이공원_탑승한다_poll() {
        // given, when
        Person person = 놀이기구.lineOutPoll();

        // then
        assertEquals(person, 손오공);
    }

    @Test
    void 놀이공원_탑승할_회원이_없다_poll() {
        // given
        RollerCoaster 회원줄이없는놀이기구 = RollerCoaster.init();

        // when
        Person person = 회원줄이없는놀이기구.lineOutPoll();

        // then
        assertNull(person);
    }

    @Test
    void 현재_맨앞줄_회원정보_확인한다_peek() {
        // given, when
        Person person = 놀이기구.searchFrontPeek();

        // then
        assertEquals(person, 손오공);
    }

    @Test
    void 대기줄이_없을때_회원정보_확인한다_peek() {
        // given
        RollerCoaster 회원줄이없는놀이기구 = RollerCoaster.init();

        // when
        Person person = 회원줄이없는놀이기구.searchFrontPeek();

        // then
        assertNull(person);
    }

    @Test
    void 현재_맨앞줄_회원정보_확인한다_element() {
        // given, 놀이기구
        Person person = 놀이기구.searchFrontElement();

        // then
        assertEquals(person, 손오공);
    }

    @Test
    void 대기줄이_없을때_회원정보_확인한다_element() {
        // given, when
        RollerCoaster 회원줄이없는놀이기구 = RollerCoaster.init();

        // then
        assertThrows(NoSuchElementException.class, 회원줄이없는놀이기구::searchFrontElement);
    }

}
