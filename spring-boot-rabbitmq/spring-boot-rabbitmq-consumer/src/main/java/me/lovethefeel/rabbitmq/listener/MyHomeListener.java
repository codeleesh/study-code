package me.lovethefeel.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyHomeListener {

    @RabbitListener(queues = "myhome.queue")
    public void receiveMessage(final Message message) {
        log.info(message.toString());
    }
}
