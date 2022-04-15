package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(final Long memberId, final Long itemId, final int count) {
        // 엔티티 조회
        final Member member = memberRepository.findOne(memberId);
        final Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        final Delivery delivery = Delivery.createDelivery(member.getAddress());

        // 주문상품 생성
        final OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        final Order order = Order.crateOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(final Long orderId) {
        // 주문 엔티티 조회
        final Order order = orderRepository.findOne(orderId);

        // 주문 취소
        order.cancel();
    }

    /**
     * 주문 검색
     */
    public List<Order> findOrders(final OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
