package jpa.ordershop.string.domain;

import jpa.ordershop.domain.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class OrderStringRepositoryTest {

    @Autowired
    private OrderStringRepository orderRepository;

    @DisplayName("주문의 상태를 enum을 이용하여 저장한다.")
    @Test
    void saveOrder() {
        final OrderString order = OrderString.from(OrderStatus.COMPLETE);
        final OrderString savedOrder = orderRepository.save(order);

        final OrderString findOrder = orderRepository.findById(savedOrder.getId()).get();

        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.COMPLETE);
    }

    @DisplayName("저장되어 있는 주문을 조회하여 상태를 비교한다.")
    @Test
    void findOrder() {
        final OrderString order = orderRepository.findById(1L).get();

        assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETE);
    }
}
