package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    // 주문 상태 [ORDER, CANCEL]
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 연관관계 메소드
    // 양방향일때 사용
    public void addMember(final Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(final OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void addDelivery(final Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // 생성 메서드
    /**
     * 주문 생성
     * @param member
     * @param delivery
     * @param orderItems
     * @return
     */
    public static Order crateOrder(final Member member, final Delivery delivery, final OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    // 비즈니스 로직
    /**
     * 주문 취소
     */
    public void cancel() {
        if (this.delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCLE);
        for (OrderItem orderItem : this.orderItems) {
            orderItem.cancel();
        }
    }

    /**
     * 전체 주문 가격 조회
     * @return
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : this.orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
