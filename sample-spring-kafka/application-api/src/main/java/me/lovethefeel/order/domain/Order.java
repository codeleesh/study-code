package me.lovethefeel.order.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class Order {

    private final Long id;
    private final String name;
    private final BigDecimal amount;
    private final String createBy;
    private final LocalDateTime createDate;

    protected Order(final Long id, final String name, final BigDecimal amount, final String createBy, final LocalDateTime createDate) {

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.createBy = createBy;
        this.createDate = createDate;
    }

    public static Order create(final Long id, final String name, final String amount) {
        return new Order(id, name, new BigDecimal(amount), "SYSTEM", LocalDateTime.now());
    }
}
