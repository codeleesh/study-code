package com.lovethefeel.springboot2.havi.controller;

import com.lovethefeel.springboot2.havi.domain.Book;
import com.lovethefeel.springboot2.havi.service.BookRestService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRestController {

    private final BookRestService bookRestService;

    public BookRestController(BookRestService bookRestService) {
        this.bookRestService = bookRestService;
    }

    @GetMapping(path = "/rest/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getRestBooks() {
        return bookRestService.getRestBook();
    }
}
