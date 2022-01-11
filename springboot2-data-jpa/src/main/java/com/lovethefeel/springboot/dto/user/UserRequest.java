package com.lovethefeel.springboot.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String sex;
    private BigDecimal balanceAmt;
    private Long loanAmt;

    @Builder
    public UserRequest(String name, String sex, BigDecimal balanceAmt, Long loanAmt){
        this.name = name;
        this.sex = sex;
        this.balanceAmt = balanceAmt;
        this.loanAmt = loanAmt;
    }
}
