package com.lovethefeel.commerce.domain.singletable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@DiscriminatorValue("M")
@NoArgsConstructor(access = PROTECTED)
public class Movie extends Item {
    @Column
    private String director;
    @Column
    private String actor;

    private Movie(final Long id, final String name, final int price, final int stockQuantity, final String director, final String actor) {
        super(id, name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }

    public static Movie from(final String name, final int price, final int stockQuantity, final String director, final String actor) {
        return new Movie(null, name, price, stockQuantity, director, actor);
    }
}
