package jpa.ordershop.ordinal.domain;

import jpa.ordershop.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "orders_ordinal")
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class OrderOrdinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    public static OrderOrdinal from(final OrderStatus status) {
        return new OrderOrdinal(null, status);
    }
}
