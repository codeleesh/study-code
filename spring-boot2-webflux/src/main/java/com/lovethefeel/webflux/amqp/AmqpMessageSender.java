package com.lovethefeel.webflux.amqp;

import com.lovethefeel.webflux.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmqpMessageSender implements AmqpMessage {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(final String exchangeName, final String routingKey, final Customer customer) {

        try {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, customer);
        } catch (Exception ae) {
            log.error("[메세지전송] Message: {}", customer);
            log.error("[메세지전송] Exception", ae);
            throw new RuntimeException("메세지전송 호출에 실패하였습니다.");
        }
    }
}
