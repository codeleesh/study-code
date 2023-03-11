package com.lovethefeel.webflux.amqp;

import com.lovethefeel.webflux.customer.domain.Customer;

public interface AmqpMessage {

    void sendMessage(final String exchangeName, final String routingKey, final Customer customer);

    final class Fake implements AmqpMessage {
        @Override
        public void sendMessage(String exchangeName, String routingKey, Customer customer) {
            throw new RuntimeException("메세지전송 호출에 실패하였습니다.");
        }
    }
}
