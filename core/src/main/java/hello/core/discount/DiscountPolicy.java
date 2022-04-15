package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    int discount(final Member member, final int price);
}
