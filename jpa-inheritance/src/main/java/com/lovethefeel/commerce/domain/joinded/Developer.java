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
@DiscriminatorValue("D")
@NoArgsConstructor(access = PROTECTED)
public class Developer extends Jobs {

    /**
     * 보유기술
     */
    @Column
    private String skill;

    private Developer(final Long id, final String name, final int salary, final String skill) {
        super(id, name, salary);
        this.skill = skill;
    }

    public static Developer from(final String name, final int salary, final String skill) {
        return new Developer(null, name, salary, skill);
    }
}
