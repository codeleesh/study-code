package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 상품주문() {
        // given
        final Member member = createMember();

        Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        // when
        final Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order findOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, findOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, findOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, findOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() {
        // given
        final Member member = createMember();
        final Item item = createBook("시골 JPA", 10000, 10);

        final int orderCount = 11;

        // when
        orderService.order(member.getId(), item.getId(), orderCount);

        // then
        fail("재고 수량 부족 예외가 발생해야 합니다.");
    }

    @Test
    public void 주문취소() {
        // given
        final Member member = createMember();
        final Book book = createBook("시골 JPA", 10000, 10);
        final int orderCount = 2;
        final Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);

        // then
        final Order findOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL 이다", OrderStatus.CANCLE, findOrder.getStatus());
        assertEquals("주문이 취소된 상품을 그만큼 재고가 증가해야 한다.", 10, book.getStockQuantity());
    }

    private Book createBook(final String name, final int price, final int stockQuantity) {
        final Book book = Book.from(name, price, stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        final Member member = Member.of("lee", Address.createAddress("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}