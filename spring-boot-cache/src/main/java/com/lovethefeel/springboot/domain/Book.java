package com.lovethefeel.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    protected Book() {
    }

    private Book(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static Book of(final Long id, final String name) {
        return new Book(id, name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
