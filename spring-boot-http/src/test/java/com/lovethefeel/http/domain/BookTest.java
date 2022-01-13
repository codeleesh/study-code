package com.lovethefeel.http.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("책 도메인 단위 테스트")
class BookTest {

    @DisplayName("등록된 책 이름을 변경한다.")
    @Test
    void changeName() {
        final String change = "클린코드";
        final Book book = Book.from("자바의정석");

        book.changeName(Book.from(change));

        assertThat(book.getName()).isEqualTo(change);
    }
}
