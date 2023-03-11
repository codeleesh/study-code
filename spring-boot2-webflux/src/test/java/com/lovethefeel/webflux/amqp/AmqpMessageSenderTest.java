package com.lovethefeel.webflux.amqp;

import com.lovethefeel.webflux.customer.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AmqpMessageSenderTest {

    @Test
    void 메시지전송이_실패한다() {

        final Customer customer = new Customer();

        RabbitTemplate rabbitTemplate = new MockRabbitTemplate();
        AmqpMessageSender amqpMessageSender = new AmqpMessageSender(rabbitTemplate);

        assertThatThrownBy(() -> amqpMessageSender.sendMessage("", "", customer))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("메세지전송 호출에 실패하였습니다.");
    }

    private class MockRabbitTemplate extends RabbitTemplate {

        @Override
        public void convertAndSend(String exchange, String routingKey, final Object object) {
            throw new RuntimeException("메세지전송 호출에 실패하였습니다.");
        }
    }
}