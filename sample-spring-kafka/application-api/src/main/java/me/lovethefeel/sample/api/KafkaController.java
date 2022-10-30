package me.lovethefeel.sample.api;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.order.dto.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaTemplate<String, OrderRequest> orderRequestKafkaTemplate;
    private static final String TOPIC_NAME = "my-topic";

    @PostMapping("produce")
    public ResponseEntity<Void> sendMessage(@RequestBody final OrderRequest orderRequest) {

        orderRequestKafkaTemplate.send(TOPIC_NAME, orderRequest);

        return ResponseEntity.ok().build();
    }
}
