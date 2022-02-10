package com.lovethefeel.commerce.domain.joinded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.InheritanceType.JOINED;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "job")
@Getter
@Setter
@Inheritance(strategy = JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = PROTECTED)
public abstract class Jobs {
    @Id
    @GeneratedValue
    @Column(name = "job_id")
    private Long id;

    @Column
    private String name;

    @Column
    private int salary;

    public Jobs(Long id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}
