package me.lovethefeel.member.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.member.domain.Member;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "myhome.exchange";

    public String memberPublish(final Member member) {

        log.info("Member {}", member);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "myhome.lovethefeel.#", member);
        return "message send success!";
    }
}
