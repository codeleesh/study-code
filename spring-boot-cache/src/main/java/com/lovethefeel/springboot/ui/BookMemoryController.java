package com.lovethefeel.springboot.ui;

import com.lovethefeel.springboot.application.BookMemoryService;
import com.lovethefeel.springboot.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookMemoryController {

    private final BookMemoryService bookMemoryService;

    @Cacheable("memory_books")
    @GetMapping("memory/books")
    public ResponseEntity<List<Book>> findBooks() {
        return ResponseEntity.ok(bookMemoryService.getBooks());
    }
}
