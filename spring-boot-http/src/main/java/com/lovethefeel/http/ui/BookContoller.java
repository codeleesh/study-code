package com.lovethefeel.http.ui;

import com.lovethefeel.http.application.BookService;
import com.lovethefeel.http.dto.BookChangeRequest;
import com.lovethefeel.http.dto.BookNameChangeReuqest;
import com.lovethefeel.http.dto.BookRequest;
import com.lovethefeel.http.dto.BookResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookContoller {

    private final BookService bookService;

    @PostMapping("book")
    public ResponseEntity<BookResponse> saveBook(@RequestBody final BookRequest bookRequest) {
        final BookResponse bookResponse = bookService.registerBook(bookRequest);
        return ResponseEntity.created(URI.create("/book/" + bookResponse.getId())).body(bookResponse);
    }

    @PutMapping("book/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable final Long id, @RequestBody final BookChangeRequest bookChangeRequest) {
        bookService.changeBook(id, bookChangeRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("book/{id}")
    public ResponseEntity<BookResponse> updateName(@PathVariable final Long id, @RequestBody final BookNameChangeReuqest bookNameChangeReuqest) {
        final BookResponse bookResponse = bookService.changeNameBook(id, bookNameChangeReuqest);
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

    @GetMapping("books")
    public ResponseEntity<List<BookResponse>> findBooks() {
        final List<BookResponse> bookResponses = bookService.findBookAll();
        return ResponseEntity.ok(bookResponses);
    }
}
