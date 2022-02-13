package com.lovethefeel.constructor;

import com.lovethefeel.constructor.application.BookService;
import com.lovethefeel.constructor.dto.BookRequest;
import com.lovethefeel.constructor.dto.BookResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @DisplayName("책을 등록합니다.")
    @Test
    void saveBookTest() {
        // given
        final String bookName = "자바의정석";
        final BookRequest bookRequest = BookRequest.from(bookName);

        // when
        final BookResponse bookResponse = bookService.saveBook(bookRequest);

        // then
        assertThat(bookResponse.getName()).isEqualTo(bookName);
    }
}
