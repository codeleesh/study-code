package me.lovethefeel.sample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SampleService {

    @KafkaListener(topics = "test-topic1", groupId = "test-topic1-01", containerFactory = "defaultKafkaListenerContainerFactory")
    public void consume(final String message, final Acknowledgment ack) {
        log.info("[consumer] Consumed message : {}", message);
        try {
            ack.acknowledge(); // 모든 CRUD 작업이 완료되어야만 kafka의 read off-set 값 변경
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end");
    }
}
