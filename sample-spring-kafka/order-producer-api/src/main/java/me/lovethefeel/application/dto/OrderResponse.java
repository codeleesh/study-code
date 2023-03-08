package me.lovethefeel.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lovethefeel.domain.Order;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;


@Getter
@Setter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class OrderResponse {

    private long id;
    private String name;
    private BigDecimal amount;

    public static OrderResponse from(final Order order) {

        return new OrderResponse(order.getId(), order.getName(), order.getAmount());
    }
}
