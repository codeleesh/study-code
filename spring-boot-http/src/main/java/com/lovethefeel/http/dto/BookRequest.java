package com.lovethefeel.http.dto;

import com.lovethefeel.http.domain.Book;

public class BookRequest {

    private String name;
    private long price;

    protected BookRequest() {}

    private BookRequest(final String name, final long price) {
        this.name = name;
        this.price = price;
    }

    public static BookRequest of(final String name, final long price) {
        return new BookRequest(name, price);
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

    public Book toBook() {
        return Book.of(name, price);
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
