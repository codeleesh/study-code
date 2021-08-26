package com.lovethefeel.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TN_USER_IDENTITY")
public class UserIdentity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal balanceAmt;

    @Column
    private Long loanAmt;

    @Builder
    public UserIdentity(String name, BigDecimal balanceAmt, Long loanAmt) {
        this.name = name;
        this.balanceAmt = balanceAmt;
        this.loanAmt = loanAmt;
    }

}
