package com.lovethefeel.commerce.domain.joinded;

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
@DiscriminatorValue("R")
@NoArgsConstructor(access = PROTECTED)
public class Researcher extends Jobs {

    /**
     * 연구주제
     */
    @Column
    private String title;

    private Researcher(final Long id, final String name, final int salary, final String title) {
        super(id, name, salary);
        this.title = title;
    }

    public static Researcher from(final String name, final int salary, final String title) {
        return new Researcher(null, name, salary, title);
    }
}
