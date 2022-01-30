package com.lovethefeel.springboot.application;

import com.lovethefeel.springboot.domain.Book;
import com.lovethefeel.springboot.domain.BookRepository;
import com.lovethefeel.springboot.dto.BookRequest;
import com.lovethefeel.springboot.dto.BookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse findBook(final Long bookId) {
        log.info("BookService findBook 메소드 시작");
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));
        return BookResponse.from(book);
    }

    public List<BookResponse> findBookAll() {
        log.info("BookService findBookAll 메소드 시작");
        final List<Book> books = bookRepository.findAll();
        return BookResponse.from(books);
    }

    @Transactional
    public BookResponse createBook(final BookRequest bookRequest) {
        log.info("BookService createBook 메소드 시작");
        final Book book = bookRepository.save(bookRequest.toBook());
        return BookResponse.from(book);
    }

    @Transactional
    public BookResponse updateBook(final Long bookId, final BookRequest bookRequest) {
        log.info("BookService updateBook 메소드 시작");
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));
        book.updateName(bookRequest.getName());
        return BookResponse.from(book);
    }
}
