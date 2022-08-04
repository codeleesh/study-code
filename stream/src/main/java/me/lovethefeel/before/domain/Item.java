package me.lovethefeel.before.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    private Item(final Long id, final String name, final Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public static Item of(final String name, final Status status) {
        return new Item(null, name, status);
    }
}
