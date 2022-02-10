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
@DiscriminatorValue("A")
@NoArgsConstructor(access = PROTECTED)
public class Album extends Item {
    @Column
    private String artist;

    @Column
    private String etc;

    private Album(final Long id, final String name, final int price, final int stockQuantity, final String artist, final String etc) {
        super(id, name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

    public static Album from(final String name, final int price, final int stockQuantity, final String artist, final String etc) {
        return new Album(null, name, price, stockQuantity, artist, etc);
    }
}
