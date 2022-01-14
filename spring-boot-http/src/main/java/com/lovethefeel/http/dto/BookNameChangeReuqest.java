package com.lovethefeel.http.dto;

import com.lovethefeel.http.domain.Book;

public class BookNameChangeReuqest {

    private String name;

    protected BookNameChangeReuqest() {}

    private BookNameChangeReuqest(final String name) {
        this.name = name;
    }

    public static BookNameChangeReuqest from(final String name) {
        return new BookNameChangeReuqest(name);
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

    @Override
    public String toString() {
        return "BookNameChangeReuqest{" +
                "name='" + name + '\'' +
                '}';
    }
}
