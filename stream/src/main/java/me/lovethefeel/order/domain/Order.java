package me.lovethefeel.order.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_status")
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

    protected Order() {}

    public Order(final String orderId, final String orderName, final OrderStatus orderStatus) {

        this.orderId = orderId;
        this.orderName = orderName;
        this.orderStatus = orderStatus;
    }

    public static Order createEntity(final String orderId, final String orderName, final OrderStatus orderStatus) {

        return new Order(orderId, orderName, orderStatus);
    }
}
