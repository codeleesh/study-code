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
@Entity(name = "TN_USER_IDENTITY")
public class UserIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated
    private Sexs sexs;

    @Column
    private BigDecimal balanceAmt;

    @Column
    private Long loanAmt;

    @Builder
    public UserIdentity(String name, Sexs sexs, BigDecimal balanceAmt, Long loanAmt) {
        this.name = name;
        this.sexs = sexs;
        this.balanceAmt = balanceAmt;
        this.loanAmt = loanAmt;
    }

}
