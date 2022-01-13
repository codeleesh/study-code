package com.lovethefeel.http.dto;

import com.lovethefeel.http.domain.Book;

public class BookChangeRequest {

    private String name;

    protected BookChangeRequest() {}

    private BookChangeRequest(final String name) {
        this.name = name;
    }

    public static BookChangeRequest from(final String name) {
        return new BookChangeRequest(name);
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
