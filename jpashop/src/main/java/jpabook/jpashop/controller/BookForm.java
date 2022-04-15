package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public Book toBook() {
        return new Book(this.id, this.name, this.price, this.stockQuantity, this.author, this.isbn);
    }
}
