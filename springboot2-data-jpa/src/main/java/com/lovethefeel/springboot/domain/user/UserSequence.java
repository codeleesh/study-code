package com.lovethefeel.springboot.domain.user;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TN_USER_SEQUENCE")
public class UserSequence extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Builder
    public UserSequence(String name) {
        this.name = name;
    }

}
