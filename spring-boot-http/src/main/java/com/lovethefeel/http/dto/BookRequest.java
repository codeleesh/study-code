package com.lovethefeel.http.dto;

import com.lovethefeel.http.domain.Book;

public class BookRequest {

    private String name;

    protected BookRequest() {}

    private BookRequest(final String name) {
        this.name = name;
    }

    public static BookRequest from(final String name) {
        return new BookRequest(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book toBook() {
        return Book.from(name);
    }
}
