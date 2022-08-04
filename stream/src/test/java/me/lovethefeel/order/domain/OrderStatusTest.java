package me.lovethefeel.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderStatusTest {

    @DisplayName("enum 코드로 Enum 객체를 구할 수 있습니다.")
    @Test
    void 주문상태_코드_조회() {

        final OrderStatus orderStatus = OrderStatus.from("01");

        assertThat(orderStatus).isNotNull();
    }
}
