package com.lovethefeel.http.application;

import com.lovethefeel.http.domain.Book;
import com.lovethefeel.http.domain.BookRepository;
import com.lovethefeel.http.dto.BookChangeRequest;
import com.lovethefeel.http.dto.BookNameChangeReuqest;
import com.lovethefeel.http.dto.BookRequest;
import com.lovethefeel.http.dto.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookResponse registerBook(final BookRequest bookRequest) {
        log.info("BookService findBook 메소드 시작 BookRequest [{}]", bookRequest);
        final Book book = bookRepository.save(bookRequest.toBook());
        return BookResponse.from(book);
    }

    public BookResponse findBook(final Long id) {
        log.info("BookService findBook 메소드 시작 Long [{}]", id);
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return BookResponse.from(book);
    }

    public List<BookResponse> findBookAll() {
        log.info("BookService findBookAll 메소드 시작");
        final List<Book> books = bookRepository.findAll();
        return BookResponse.from(books);
    }

    public void changeBook(final Long id, final BookChangeRequest bookChangeRequest) {
        log.info("BookService changeBook 메소드 시작 Long [{}] BookChangeRequest [{}]", id, bookChangeRequest);
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        book.changeBook(bookChangeRequest.toBook());
    }

    public BookResponse changeNameBook(final Long id, final BookNameChangeReuqest bookNameChangeReuqest) {
        log.info("BookService changeNameBook 메소드 시작 Long [{}] BookChangeRequest [{}]", id, bookNameChangeReuqest);
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        book.changeName(bookNameChangeReuqest.toBook());
        return BookResponse.from(book);
    }

    public void deleteBook(final Long id) {
        log.info("BookService deleteBook 메소드 시작 Long [{}]", id);
        bookRepository.deleteById(id);
    }
}
