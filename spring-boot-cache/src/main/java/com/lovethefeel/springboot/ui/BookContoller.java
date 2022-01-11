package com.lovethefeel.springboot.ui;

import com.lovethefeel.springboot.application.BookService;
import com.lovethefeel.springboot.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookContoller {

    private final BookService bookService;

    @Cacheable("book")
    @GetMapping("book")
    public Mono<Book> findBook() {
        return bookService.findBook();
    }

    @Cacheable("books")
    @GetMapping("books")
    public Mono<List<Book>> findBooks() {
        return bookService.findBookAll();
    }
}
