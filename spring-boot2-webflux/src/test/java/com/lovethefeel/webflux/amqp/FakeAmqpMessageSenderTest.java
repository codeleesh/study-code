package com.lovethefeel.webflux.amqp;

import com.lovethefeel.webflux.customer.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FakeAmqpMessageSenderTest {

    @Test
    void 메시지전송이_실패한다() {

        final Customer customer = new Customer();

        AmqpMessage amqpMessage = new AmqpMessage.Fake();

        assertThatThrownBy(() -> amqpMessage.sendMessage("", "", customer))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("메세지전송 호출에 실패하였습니다.");
    }
}