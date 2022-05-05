package com.lovethefeel.webflux;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class FluxTest {

    @DisplayName("빈 값으로 Flux를 만들어서 반환한다.")
    @Test
    void createEmpty() {
        final Flux<String> empty1 = Flux.empty();
        final Flux<String> empty2 = Flux.empty();
        assertThat(empty2).isEqualTo(empty1);
    }

    @DisplayName("Collection 을 사용하지 않고 'foo', 'bar' 포함한 Flux 객체를 생성한다.")
    @Test
    void createJust() {
        Flux<String> just = Flux.just("foo", "bar");
    }

    @DisplayName("Collection 을 사용하여서 'foo', 'bar' 포함한 Flux 객체를 생성한다.")
    @Test
    void createJustCollection() {
        List<String> items = new ArrayList<>();
        items.add("foo");
        items.add("bar");
        Flux<String> lists = Flux.fromIterable(items);
        Flux<String> asList = Flux.fromIterable(Arrays.asList("foo", "bar"));
        Flux<String> newArrayList = Flux.fromIterable(Lists.newArrayList("foo", "bar"));
        Flux<String> of = Flux.fromIterable(List.of("foo", "bar"));
    }

    @DisplayName("Flux를 생성할때 에러처리")
    @Test
    void error() {
        Flux.error(new IllegalStateException())
                .doOnError(System.out::println)
                .subscribe();
    }

    @Test
    void test() {
        Flux.fromIterable(Arrays.asList("foo", "bar"))
                .doOnNext(System.out::println)
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        System.out.println("??");
    }

    @DisplayName("interval")
    @Test
    void interval() {

    }
}
