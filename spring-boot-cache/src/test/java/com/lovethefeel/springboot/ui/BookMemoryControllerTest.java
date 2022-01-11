package com.lovethefeel.springboot.ui;

import com.lovethefeel.springboot.application.BookMemoryService;
import com.lovethefeel.springboot.domain.Book;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.mockito.BDDMockito.given;

@WebFluxTest(controllers = BookMemoryController.class)
class BookMemoryControllerTest {
    
    @Autowired
    private WebTestClient webTestClient;
    
    @MockBean
    private BookMemoryService bookMemoryService;

    @DisplayName("메모리를 이용하여 책 여러권을 조회한다.")
    @Test
    void 메모리_책_여러권_조회() {
        // given
        final Book book1 = Book.of(1L, "자바의정석");
        final Book book2 = Book.of(2L, "자바의정석");
        final Book book3 = Book.of(3L, "자바의정석");
        final Book book4 = Book.of(4L, "자바의정석");
        final List<Book> books = Lists.newArrayList(book1, book2, book3, book4);

        given(bookMemoryService.getBooks()).willReturn(books);

        // when-then
        this.webTestClient.get().uri("/memory/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class);
    }
}
