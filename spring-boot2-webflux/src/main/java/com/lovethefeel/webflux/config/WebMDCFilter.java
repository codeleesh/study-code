package com.lovethefeel.webflux.config;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
public class WebMDCFilter implements WebFilter {

    public static final String TRACE_ID = "traceId";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange).transformDeferred(call -> doFilter(exchange, call));
    }

    private Publisher<Void> doFilter(ServerWebExchange exchange, Mono<Void> call) {
        // before
        return Mono.fromRunnable(() -> doBeforeRequest(exchange))
                // do request
                .then(call)
                // after (success)
                .doOnSuccess(done -> doAfterRequest(exchange));
    }

    private void doBeforeRequest(ServerWebExchange exchange) {
        log.info("===================== START =====================");
        final String traceId = UUID.randomUUID().toString();
        MDC.put(TRACE_ID, traceId);
        log.info("traceId : {}", traceId);
    }

    private void doAfterRequest(ServerWebExchange exchange) {
        log.info("MDC GET : {}", MDC.get(TRACE_ID));
        MDC.clear();
        log.info("MDC CLEAR : {}", MDC.get(TRACE_ID));
        log.info("===================== END =====================");
    }

}
