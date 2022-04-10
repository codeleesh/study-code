package com.lovethefeel.stack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookShelfTest {

    private Book 자바의정석 = Book.of("1", "자바의정석");
    private Book 클린코드 = Book.of("2", "클린코드");
    private Book 이펙티브자바 = Book.of("3", "이펙티브자바");
    private BookShelf bookShelf;

    @BeforeEach
    void init() {
        bookShelf = BookShelf.init();
        bookShelf.push(자바의정석);
        bookShelf.push(클린코드);
        bookShelf.push(이펙티브자바);
    }

    @Test
    void 맨위에_있는_도서() {
        // given, when
        Book book = bookShelf.recentItem();

        // then
        assertEquals(book, 이펙티브자바);
    }

    @Test
    void 새로운_도서_추가() {
        // given
        Book 클린아키텍처 = Book.of("4", "클린아키텍처");
        BookShelf bookShelf = BookShelf.init();

        // when
        bookShelf.push(클린아키텍처);

        // then
        assertEquals(bookShelf.recentItem(), 클린아키텍처);
    }

    @Test
    void 도서_삭제() {
        // given, when
        Book popItem = bookShelf.pop();

        // then
        assertEquals(popItem, 이펙티브자바);
    }

    @Test
    void 도서_검색() {
        // given, when
        boolean isSearch = bookShelf.search(자바의정석);

        // then
        assertTrue(isSearch);
    }

    @Test
    void 도서_존재_여부() {
        // given, when
        BookShelf empty = BookShelf.init();

        // then
        assertTrue(empty.isEmpty());
    }

    @Test
    void 리스트_변환() {
        // given, when
        List<Book> listItems = bookShelf.list();

        // then
        assertEquals(3, listItems.size());
    }

    @Test
    void 더이상_제거할_도서가_없음() {
        // given, when
        BookShelf empty = BookShelf.init();

        // then
        assertThrows(EmptyStackException.class, empty::pop);
    }
}