package com.lovethefeel.http.ui;

import com.lovethefeel.http.application.BookService;
import com.lovethefeel.http.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class BookContoller {

    private final BookService bookService;

    public BookContoller(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("book")
    public ResponseEntity<BookResponse> saveBook(@RequestBody final BookRequest bookRequest) {
        final BookResponse bookResponse = bookService.registerBook(bookRequest);
        return ResponseEntity.created(URI.create("/book/" + bookResponse.getId())).body(bookResponse);
    }

    @PutMapping("book/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable final Long id, @RequestBody final BookChangeRequest bookChangeRequest) {
        final BookResponse bookResponse = bookService.changeBook(id, bookChangeRequest);
        if (id.equals(bookResponse.getId())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.created(URI.create("/book/" + bookResponse.getId())).build();
    }

    @PatchMapping("book/{id}")
    public ResponseEntity<BookResponse> updatePrice(@PathVariable final Long id, @RequestBody final BookPriceChangeReuqest bookPriceChangeReuqest) {
        final BookResponse bookResponse = bookService.changePriceBook(id, bookPriceChangeReuqest);
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable final Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("book/{id}")
    public ResponseEntity<BookResponse> findBook(@PathVariable final Long id) {
        final BookResponse bookResponse = bookService.findBook(id);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("book")
    public ResponseEntity<BookResponse> findBookByIdAndName(@RequestParam final Long id, @RequestParam final String name) {
        final BookResponse bookResponse = bookService.findBookByIdAndName(id, name);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("books")
    public ResponseEntity<List<BookResponse>> findBooks() {
        final List<BookResponse> bookResponses = bookService.findBookAll();
        return ResponseEntity.ok(bookResponses);
    }
}
