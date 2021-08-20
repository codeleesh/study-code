package com.lovethefeel.springboot2.domain.enums;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialTypeTest {

    @Test
    public void getRoleType() {
        SocialType socialType = SocialType.FACEBOOK;
        assertThat(socialType.getRoleType()).isEqualTo("ROLE_FACEBOOK");

        socialType = SocialType.KAKAO;
        assertThat(socialType.getRoleType()).isEqualTo("ROLE_KAKAO");
    }

    @Test
    public void getValue() {
        SocialType socialType = SocialType.FACEBOOK;
        assertThat(socialType.getValue()).isEqualTo("facebook");
    }

    @Test
    public void isEquals() {
        String authority = "ROLE_FACEBOOK";
        SocialType socialType = SocialType.FACEBOOK;
        assertThat(socialType.isEquals(authority)).isEqualTo(true);
    }
}
