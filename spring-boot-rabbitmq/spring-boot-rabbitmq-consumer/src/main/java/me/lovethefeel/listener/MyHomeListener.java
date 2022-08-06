package me.lovethefeel.listener;

import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.member.domain.Member;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyHomeListener {

    @RabbitListener(queues = "myhome.queue")
    public void receiveMessage(final Member member) {
        log.info("Message Member {}", member);
    }
}
