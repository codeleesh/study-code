package me.lovethefeel.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.ANY;

@DataJpaTest
@AutoConfigureTestDatabase(replace = ANY)
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @DisplayName("주문을 임시 저장한다.")
    @Test
    void save_temp_order() {

        final Order entity = Order.createEntity("orderId", "orderName", OrderStatus.TEMPSAVE);

        final Order saveOrder = orderRepository.save(entity);

        assertThat(saveOrder.getOrderStatus()).isEqualTo(OrderStatus.TEMPSAVE);
    }
}
