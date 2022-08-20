package me.lovethefeel.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @DisplayName("주문을 임시 저장한다.")
    @Test
    void save_temp_order() {

        final Order entity = Order.createEntity("ORDER001", "주문", OrderStatus.TEMPSAVE);

        final Order saveOrder = orderRepository.save(entity);

        assertThat(saveOrder.getOrderStatus()).isEqualTo(OrderStatus.TEMPSAVE);
    }
}
