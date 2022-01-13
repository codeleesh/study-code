package com.lovethefeel.http.dto;

import com.lovethefeel.http.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookResponse {

    private Long id;
    private String name;

    protected BookResponse() {}

    private BookResponse(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static BookResponse of(final Long id, final String name) {
        return new BookResponse(id, name);
    }

    public static BookResponse from(final Book book) {
        return new BookResponse(book.getId(), book.getName());
    }

    public static List<BookResponse> from(final List<Book> books) {
        return books.stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
