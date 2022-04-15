package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    @Test
    void discount_vip() {
        Member member = new Member(1L, "nameVIP", Grade.VIP);

        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(1000);
    }

    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    @Test
    void discount_vip_error() {
        Member member = new Member(1L, "nameVIP", Grade.BASIC);

        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isZero();
    }
}