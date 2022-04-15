package com.lovethefeel.stack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class BookShelf {

    public static final int NON_EXIST_ITEM = -1;

    private Stack<Book> bookShelfs;

    private BookShelf(final Stack<Book> bookShelfs) {
        this.bookShelfs = bookShelfs;
    }

    public static BookShelf init() {
        return new BookShelf(new Stack<>());
    }

    public static BookShelf from(final Stack<Book> bookShelfs) {
        return new BookShelf(bookShelfs);
    }

    public Book recentItem() {
        return this.bookShelfs.peek();
    }

    public void push(final Book book) {
        this.bookShelfs.push(book);
    }

    public Book pop() {
        return this.bookShelfs.pop();
    }

    public boolean search(final Book book) {
        return this.bookShelfs.search(book) != NON_EXIST_ITEM;
    }

    public boolean isEmpty() {
        return this.bookShelfs.empty();
    }

    public List<Book> list() {
        return new ArrayList<>(this.bookShelfs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookShelf bookShelf = (BookShelf) o;
        return Objects.equals(bookShelfs, bookShelf.bookShelfs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookShelfs);
    }

    @Override
    public String toString() {
        return "BookShelf{" +
                "bookShelfs=" + bookShelfs +
                '}';
    }
}
