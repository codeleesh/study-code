package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    // [READY, COMP]
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public static Delivery createDelivery(final Address address) {
        return new Delivery(null, null, address, DeliveryStatus.READY);
    }
}
