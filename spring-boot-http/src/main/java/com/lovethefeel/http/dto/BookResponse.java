package com.lovethefeel.http.dto;

import com.lovethefeel.http.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookResponse {

    private Long id;
    private String name;
    private long price;

    protected BookResponse() {}

    private BookResponse(final Long id, final String name, final long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static BookResponse of(final Long id, final String name, final long price) {
        return new BookResponse(id, name, price);
    }

    public static BookResponse from(final Book book) {
        return new BookResponse(book.getId(), book.getName(), book.getPrice());
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
