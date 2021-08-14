package com.lovethefeel.springboot2.domain.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialTypeTest {

    @Test
    public void getRoleType() {
        SocialType socialType = SocialType.FACEBOOK;
        assertThat("ROLE_FACEBOOK").isEqualTo(socialType.getRoleType());
    }

    @Test
    public void getValue() {
        SocialType socialType = SocialType.FACEBOOK;
        assertThat("facebook").isEqualTo(socialType.getValue());
    }

    @Test
    public void isEquals() {
        String authority = "ROLE_FACEBOOK";
        SocialType socialType = SocialType.FACEBOOK;
        assertThat(true).isEqualTo(socialType.isEquals(authority));
    }
}
