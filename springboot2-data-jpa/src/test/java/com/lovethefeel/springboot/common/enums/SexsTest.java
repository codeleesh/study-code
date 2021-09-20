package com.lovethefeel.springboot.common.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SexsTest {

    @Test
    void getValue() {
        assertThat(Sexs.FEMALE.getValue()).isEqualTo("여성");
    }

    @Test
    void getRandomValue() {
        String givenValue = Sexs.getRandom().getValue();
        String retValue = givenValue;
        assertThat(givenValue).isEqualTo(retValue);
    }

}
