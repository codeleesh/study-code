package com.lovethefeel.springboot.domain;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString
@Entity
@Table(name = "book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Long id;
    @Column
    private String name;

    private Book(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static Book of(final Long id, final String name) {
        return new Book(id, name);
    }

    public static Book from(final String name) {
        return new Book(null, name);
    }

    public void updateName(final String name) {
        this.name = name;
    }
}
