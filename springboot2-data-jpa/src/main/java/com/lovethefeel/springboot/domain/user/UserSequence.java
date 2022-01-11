package com.lovethefeel.springboot.domain.user;

import com.lovethefeel.springboot.common.enums.Sexs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TN_USER_SEQUENCE")
public class UserSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Sexs sexs;

    @Column
    private BigDecimal balanceAmt;

    @Column
    private Long loanAmt;

    @Builder
    public UserSequence(String name, Sexs sexs, BigDecimal balanceAmt, Long loanAmt) {
        this.name = name;
        this.sexs = sexs;
        this.balanceAmt = balanceAmt;
        this.loanAmt = loanAmt;
    }

}
