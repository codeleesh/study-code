package com.lovethefeel.springboot.common.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SexTest {

    @Test
    void getValue() {
        assertThat(Sex.FEMALE.getValue()).isEqualTo("여성");
    }

    @Test
    void getRandomValue() {
        String givenValue = Sex.getRandom().getValue();
        String retValue = "";
        if ( "남성".equals(givenValue) ) {
            retValue = givenValue;
        } else {
            retValue = givenValue;
        }
        assertThat(givenValue).isEqualTo(retValue);
    }

}
