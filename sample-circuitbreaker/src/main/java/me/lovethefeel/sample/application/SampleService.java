package me.lovethefeel.sample.application;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.sample.client.SampleApi;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleApi sampleApi;

    @CircuitBreaker(name = "notificationSend", fallbackMethod = "sendFallback")
    public void send() {

        log.info("start");
        sampleApi.status();
        log.info("end");
    }

    private void sendFallback(final Throwable throwable) {

        log.info("fallback");
    }
}
