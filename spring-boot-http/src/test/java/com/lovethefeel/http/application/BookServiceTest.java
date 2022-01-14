package com.lovethefeel.http.application;

import com.lovethefeel.http.domain.Book;
import com.lovethefeel.http.domain.BookRepository;
import com.lovethefeel.http.dto.BookChangeRequest;
import com.lovethefeel.http.dto.BookPriceChangeReuqest;
import com.lovethefeel.http.dto.BookRequest;
import com.lovethefeel.http.dto.BookResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @DisplayName("책을 등록한다.")
    @Test
    void registerTest() {
        final BookRequest bookRequest = BookRequest.of("자바의정석", 15000);
        final Book book = Book.of(1L, "자바의정석", 15000);
        given(bookRepository.save(any())).willReturn(book);

        final BookResponse bookResponse = bookService.registerBook(bookRequest);

        assertThat(bookResponse.getName()).isEqualTo(bookRequest.getName());
    }

    @DisplayName("책을 단건 조회한다.")
    @Test
    void findBookTest() {
        final Book book = Book.of(1L, "자바의정석", 15000);
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(book));

        final BookResponse bookResponse = bookService.findBook(1L);

        assertThat(bookResponse.getName()).isEqualTo(book.getName());
    }

    @DisplayName("책의 ID와 이름을 이용하여 조회한다.")
    @Test
    void findBookIdAndNameTest() {
        final Book book = Book.of(1L, "자바의정석", 15000);
        given(bookRepository.findByIdAndName(anyLong(), any())).willReturn(book);

        final BookResponse bookResponse = bookService.findBookByIdAndName(1L, "자바의정석");

        assertThat(bookResponse.getName()).isEqualTo(book.getName());
    }

    @DisplayName("책을 다건 조회한다.")
    @Test
    void findBookAllTest() {
        final Book book1 = Book.of(1L, "자바의정석", 15000);
        final Book book2 = Book.of(2L, "클린코드", 20000);
        final List<Book> books = Lists.newArrayList(book1, book2);
        given(bookRepository.findAll()).willReturn(books);

        final List<BookResponse> bookResponses = bookService.findBookAll();

        assertThat(bookResponses).hasSize(books.size());
    }

    @DisplayName("책 정보를 수정한다.")
    @Test
    void changeBookTest() {
        final BookChangeRequest bookChangeRequest = BookChangeRequest.of("클린코드", 20000);
        final Book book = Book.of(1L, "자바의정석", 15000);
        given(bookRepository.existsById(anyLong())).willReturn(true);
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(book));

        final BookResponse bookResponse = bookService.changeBook(1L, bookChangeRequest);

        assertThat(bookResponse.getName()).isEqualTo(bookChangeRequest.getName());
    }

    @DisplayName("책 가격을 수정한다.")
    @Test
    void changePriceBookTest() {
        final BookPriceChangeReuqest bookPriceChangeReuqest = BookPriceChangeReuqest.from(20000);
        final Book book = Book.of(1L, "자바의정석", 15000);
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(book));

        final BookResponse bookResponse = bookService.changePriceBook(1L, bookPriceChangeReuqest);

        assertThat(bookResponse.getPrice()).isEqualTo(bookPriceChangeReuqest.getPrice());
    }

    @DisplayName("책을 삭제한다.")
    @Test
    void deleteBookTest() {

        bookRepository.deleteById(anyLong());

        bookService.deleteBook(1L);
    }
}
