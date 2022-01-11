package com.lovethefeel.springboot.ui;

import com.lovethefeel.springboot.application.BookMemoryService;
import com.lovethefeel.springboot.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookMemoryController {

    private final BookMemoryService bookMemoryService;

    @Cacheable("memory_books")
    @GetMapping("memory/books")
    public Mono<List<Book>> findBooks() {
        return Mono.just(bookMemoryService.getBooks());
    }
}
