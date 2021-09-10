package com.lovethefeel.springboot.domain.user;

import com.lovethefeel.springboot.common.enums.Sex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class UserIdentityTest {

    @Autowired
    JacksonTester<UserIdentity> userJson;

    @Test
    void user_json_테스트() throws IOException {

        // given
        UserIdentity userIdentity = UserIdentity.builder()
                .name("퐁당")
                .sex(Sex.MALE)
                .loanAmt(10000L)
                .balanceAmt(new BigDecimal("1000"))
                .build();

        String content = "{\"name\":\"퐁당\", \"sex\":\"MALE\", \"loanAmt\":10000, \"balanceAmt\":\"1000\"}";

        assertThat(this.userJson.parseObject(content).getName()).isEqualTo(userIdentity.getName());
        assertThat(this.userJson.parseObject(content).getSex()).isEqualTo(userIdentity.getSex());
        assertThat(this.userJson.parseObject(content).getLoanAmt()).isEqualTo(userIdentity.getLoanAmt());
        assertThat(this.userJson.parseObject(content).getBalanceAmt()).isEqualTo(userIdentity.getBalanceAmt());
    }

}
