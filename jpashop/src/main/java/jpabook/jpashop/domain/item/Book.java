package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;

    protected Book() {}

    public Book(final Long id, final String name, final int price, final int stockQuantity, final String author, final String isbn) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStockQuantity(stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public static Book from(final String name, final int price, final int stockQuantity) {
        return new Book(null, name, price, stockQuantity, null, null);
    }
}
