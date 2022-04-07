package com.lovethefeel.stack.domain;

import java.util.Objects;

public class Book {
    private final String isbnId;
    private final String name;

    private Book(final String isbnId, final String name) {
        validate(isbnId, name);
        this.isbnId = isbnId;
        this.name = name;
    }

    private void validate(final String isbnId, final String name) {
        if (Objects.isNull(isbnId) || "".equals(isbnId)) {
            throw new IllegalArgumentException("isbn_id가 없습니다.");
        }
        if (Objects.isNull(name) || "".equals(name)) {
            throw new IllegalArgumentException("name이 없습니다.");
        }
    }

    public static Book of(final String isbnId, final String name) {
        return new Book(isbnId, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbnId, book.isbnId) && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbnId, name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbnId='" + isbnId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
