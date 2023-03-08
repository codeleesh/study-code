package me.lovethefeel.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    public Order findOrder(final long orderId) {

        log.info("findOrder orderId {}", orderId);

        return Order.create(orderId, "신규주문", "100.00");
    }
}
