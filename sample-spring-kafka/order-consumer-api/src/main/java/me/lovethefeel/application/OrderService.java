package me.lovethefeel.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    @KafkaListener(topics = "hub1", groupId = "myGroup1")
    public void consume(String message) {
        log.info("[consumer] Consumed message : {}", message);
        log.info("end");
    }
}
