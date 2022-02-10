package com.lovethefeel.commerce.domain.singletable;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static lombok.AccessLevel.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
@NoArgsConstructor(access = PROTECTED)
public class Book extends Item {
    @Column
    private String author;

    @Column
    private String isbn;

    private Book(final Long id, final String name, final int price, final int stockQuantity, final String author, final String isbn) {
        super(id, name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public static Book from(final String name, final int price, final int stockQuantity, final String author, final String isbn) {
        return new Book(null, name, price, stockQuantity, author, isbn);
    }
}
