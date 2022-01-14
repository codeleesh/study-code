package com.lovethefeel.http.dto;

import com.lovethefeel.http.domain.Book;

public class BookChangeRequest {

    private String name;
    private long price;

    protected BookChangeRequest() {}

    private BookChangeRequest(final String name, final long price) {
        this.name = name;
        this.price = price;
    }

    public static BookChangeRequest of(final String name, final long price) {
        return new BookChangeRequest(name, price);
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
        return "BookChangeRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
