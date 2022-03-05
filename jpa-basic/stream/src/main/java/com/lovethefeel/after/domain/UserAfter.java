package com.lovethefeel.after.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "USERS_AFTER")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class UserAfter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String name;

    @Column
    @Convert(converter = TagConverter.class)
    private Set<String> hobby;

    @Column
    private String useYn;

    public static UserAfter of(final String name, final Set<String> hobby, final String useYn) {
        return new UserAfter(null, name, hobby, useYn);
    }
}
