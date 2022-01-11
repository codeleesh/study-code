package com.lovethefeel.springboot.application;

import com.lovethefeel.springboot.domain.Book;
import com.lovethefeel.springboot.domain.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Mono<Book> findBook() {
        log.info("BookService findBook 메소드 시작");
        final Book book = Book.of(1L, "자바의정석");
        return Mono.just(book)
                .log();
    }

    public Mono<List<Book>> findBookAll() {
        log.info("BookService findBookAll 메소드 시작");
        final List<Book> books = bookRepository.findAll();
        return Mono.just(books);
    }
}
