package item2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static item2.domain.OrderItem.builder;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemTest {

    @DisplayName("모든 항목에 대해서 주문 아이템을 생성한다.")
    @Test
    void create_all_orderItem() {

        final OrderItem orderItem = builder()
                .seq(1L)
                .orderId(1L)
                .menuId(1L)
                .quantity(10L)
                .build();

        assertAll(
                () -> assertEquals(orderItem.getSeq(), 1L),
                () -> assertEquals(orderItem.getOrderId(), 1L),
                () -> assertEquals(orderItem.getMenuId(), 1L),
                () -> assertEquals(orderItem.getQuantity(), 10L)
        );
    }

    @DisplayName("메뉴 아이디를 제외한 주문 아이템을 생성한다.")
    @Test
    void create_not_menu_orderItem() {

        final OrderItem orderItem = builder()
                .seq(1L)
                .orderId(1L)
                .quantity(10L)
                .build();

        assertAll(
                () -> assertEquals(orderItem.getSeq(), 1L),
                () -> assertEquals(orderItem.getOrderId(), 1L),
                () -> assertEquals(orderItem.getMenuId(), null),
                () -> assertEquals(orderItem.getQuantity(), 10L)
        );
    }

    @DisplayName("상품의 수량을 제외한 주문 아이템을 생성한다.")
    @Test()
    void create_not_quantity_orderItem() {

        final OrderItem orderItem = builder()
                .seq(1L)
                .orderId(1L)
                .menuId(1L)
                .build();

        assertAll(
                () -> assertEquals(orderItem.getSeq(), 1L),
                () -> assertEquals(orderItem.getOrderId(), 1L),
                () -> assertEquals(orderItem.getMenuId(), 1L),
                () -> assertEquals(orderItem.getQuantity(), null)
        );
    }

}