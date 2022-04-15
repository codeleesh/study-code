package com.lovethefeel.before.domain;

import lombok.*;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String hobby;

    @Column
    private String useYn;

    public static User of(final String name, final String hobby, final String useYn) {
        return new User(null, name, hobby, useYn);
    }
}
