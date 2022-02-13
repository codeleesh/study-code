package com.lovethefeel.method.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Subway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subway_id")
    private Long id;

    @Column
    private String name;

    public static Subway from(final String name) {
        return new Subway(null, name);
    }

    public static Subway of(final Long id, final String name) {
        return new Subway(id, name);
    }
}
