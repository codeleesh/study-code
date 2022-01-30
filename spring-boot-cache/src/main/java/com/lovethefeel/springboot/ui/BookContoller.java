package com.lovethefeel.springboot.ui;

import com.lovethefeel.springboot.application.BookService;
import com.lovethefeel.springboot.dto.BookRequest;
import com.lovethefeel.springboot.dto.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookContoller {

    private final BookService bookService;

    @Cacheable(cacheNames = "books", key = "#bookId")
    @GetMapping("book/{bookId}")
    public ResponseEntity<BookResponse> findBook(@PathVariable("bookId") final Long bookId) {
        return ResponseEntity.ok(bookService.findBook(bookId));
    }

    @Cacheable(cacheNames = "books")
    @GetMapping("books")
    public ResponseEntity<List<BookResponse>> findBooks() {
        return ResponseEntity.ok(bookService.findBookAll());
    }

    @CacheEvict(cacheNames = "books", allEntries = true)
    @PostMapping("book")
    public ResponseEntity<BookResponse> createBook(final @RequestBody BookRequest bookRequest) {
        final BookResponse created = bookService.createBook(bookRequest);
        final URI uri = URI.create("/book/" + created.getId());
        return ResponseEntity.created(uri)
                .body(created);
    }

    @CachePut(cacheNames = "books", key = "#bookId")
    @PatchMapping("book/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable("bookId") final Long bookId, @RequestBody final BookRequest bookRequest) {
        final BookResponse response = bookService.updateBook(bookId, bookRequest);
        return ResponseEntity.ok(response);
    }
}
