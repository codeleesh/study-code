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
@DiscriminatorValue("S")
@NoArgsConstructor(access = PROTECTED)
public class Sales extends Jobs {

    /**
     * 매출금액
     */
    @Column
    private int amount;

    private Sales(final Long id, final String name, final int salary, final int amount) {
        super(id, name, salary);
        this.amount = amount;
    }

    public static Sales from(final String name, final int salary, final int amount) {
        return new Sales(null, name, salary, amount);
    }
}
