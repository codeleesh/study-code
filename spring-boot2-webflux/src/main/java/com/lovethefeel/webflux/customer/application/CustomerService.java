package com.lovethefeel.webflux.customer.application;

import com.lovethefeel.webflux.customer.domain.Customer;
import com.lovethefeel.webflux.customer.repository.CustomerOrderRepository;
import com.lovethefeel.webflux.customer.repository.CustomerRepository;
import com.lovethefeel.webflux.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final ProductService productService;
    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "myhome.exchange";
    private static final String ROUTING_KEY = "myhome.lovethefeel.#";

    public void sendMessage(final Customer customer) {

        try {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, customer);
        } catch (Exception ae) {
            log.error("[메세지전송] Message: {}", customer);
            log.error("[메세지전송] Exception", ae);
            throw new RuntimeException("메세지전송 호출에 실패하였습니다.");
        }
    }
}
