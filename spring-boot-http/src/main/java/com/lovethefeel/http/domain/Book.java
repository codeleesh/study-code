package com.lovethefeel.http.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private long price;

    protected Book() {
    }

    private Book(final Long id, final String name, final long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static Book of(final Long id, final String name, final long price) {
        return new Book(id, name, price);
    }

    public static Book of(final String name, final long price) {
        return new Book(null, name, price);
    }

    public static Book from(final String name) {
        return new Book(null, name, 0);
    }

    public static Book from(final long price) {
        return new Book(null, null, price);
    }

    public void changeName(final Book book) {
        this.name = book.name;
    }

    public void changePrice(final Book book) {
        this.price = book.price;
    }

    public void changeBook(final Book book) {
        this.name = book.name;
        this.price = book.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
