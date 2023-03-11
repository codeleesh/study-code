package me.lovethefeel.sample.api;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.application.dto.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaTemplate<String, String> defaultKafkaTemplate;
    private static final String TOPIC_NAME = "test-topic1";

    @PostMapping("produce")
    public ResponseEntity<Void> sendMessage(@RequestBody final OrderRequest orderRequest) {

        defaultKafkaTemplate.send(TOPIC_NAME, "", orderRequest.toString());

        return ResponseEntity.ok().build();
    }
}
