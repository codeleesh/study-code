package com.lovethefeel.http.dto;

import com.lovethefeel.http.domain.Book;

public class BookPriceChangeReuqest {

    private long price;

    protected BookPriceChangeReuqest() {}

    private BookPriceChangeReuqest(final long price) {
        this.price = price;
    }

    public static BookPriceChangeReuqest from(final long price) {
        return new BookPriceChangeReuqest(price);
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Book toBook() {
        return Book.from(price);
    }

    @Override
    public String toString() {
        return "BookPridceChangeReuqest{" +
                "price=" + price +
                '}';
    }
}
