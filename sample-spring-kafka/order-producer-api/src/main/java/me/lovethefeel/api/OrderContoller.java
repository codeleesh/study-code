package me.lovethefeel.api;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.application.OrderService;
import me.lovethefeel.domain.Order;
import me.lovethefeel.application.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderContoller {

    private final OrderService orderService;

    @GetMapping("find/{orderId}")
    public ResponseEntity<OrderResponse> findOrder(@PathVariable final long orderId) {

        final Order order = orderService.findOrder(orderId);
        final OrderResponse orderResponse = OrderResponse.from(order);
        return ResponseEntity.ok(orderResponse);
    }
}
