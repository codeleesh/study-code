package com.lovethefeel.http.application;

import com.lovethefeel.http.domain.Book;
import com.lovethefeel.http.domain.BookRepository;
import com.lovethefeel.http.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse registerBook(final BookRequest bookRequest) {
        log.info("BookService findBook 메소드 시작 bookRequest [{}]", bookRequest);
        final Book selectedBook = bookRepository.findByName(bookRequest.getName());
        if (Objects.nonNull(selectedBook)) {
            throw new IllegalArgumentException("이미 등록된 책입니다.");
        }
        final Book book = bookRepository.save(bookRequest.toBook());
        return BookResponse.from(book);
    }

    public BookResponse findBook(final Long id) {
        log.info("BookService findBook 메소드 시작 id [{}]", id);
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return BookResponse.from(book);
    }

    public List<BookResponse> findBookAll() {
        log.info("BookService findBookAll 메소드 시작");
        final List<Book> books = bookRepository.findAll();
        return BookResponse.from(books);
    }

    public BookResponse changeBook(final Long id, final BookChangeRequest bookChangeRequest) {
        log.info("BookService changeBook 메소드 시작 id [{}] bookChangeRequest [{}]", id, bookChangeRequest);
        final boolean isExistBook = bookRepository.existsById(id);
        if (!isExistBook) {
            final Book persistBook = bookRepository.save(bookChangeRequest.toBook());
            return BookResponse.from(persistBook);
        }
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        book.changeBook(bookChangeRequest.toBook());
        return BookResponse.from(book);
    }

    public BookResponse changePriceBook(final Long id, final BookPriceChangeReuqest bookPriceChangeReuqest) {
        log.info("BookService changePriceBook 메소드 시작 id [{}] bookNameChangeReuqest [{}]", id, bookPriceChangeReuqest);
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        book.changePrice(bookPriceChangeReuqest.toBook());
        return BookResponse.from(book);
    }

    public void deleteBook(final Long id) {
        log.info("BookService deleteBook 메소드 시작 id [{}]", id);
        bookRepository.deleteById(id);
    }

    public BookResponse findBookByIdAndName(final Long id, final String name) {
        log.info("BookService findBookByIdAndName 메소드 시작 id [{}] name [{}]", id, name);
        final Book book = bookRepository.findByIdAndName(id, name);
        return BookResponse.from(book);
    }
}
