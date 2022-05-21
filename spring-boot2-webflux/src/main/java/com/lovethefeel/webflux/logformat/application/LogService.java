package com.lovethefeel.webflux.logformat.application;

import com.lovethefeel.webflux.product.domain.ProductStatus;
import com.lovethefeel.webflux.product.dto.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArgument;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.*;
import static net.logstash.logback.marker.Markers.*;

@Slf4j(topic = "json")
@Service
public class LogService {

    public void callStructureArguments() {

        log.info("log message {}", value("name", "value"));

        log.info("log message {}", keyValue("name", "value"));

        log.info("log message", keyValue("name", "value"));

        log.info("log message {} {}", keyValue("name1", "value1"), keyValue("name2", "value2"));

        log.info("log message {}", keyValue("name", "value", "{0}=[{1}]"));

        Foo foo = new Foo();
        log.info("log message {}", value("foo", foo));

        Map myMap = new HashMap();
        myMap.put("name1", "value1");
        myMap.put("name2", "value2");
        log.info("log message {}", entries(myMap));

        log.info("log message {}", array("array", 1, 2, 3));

        final ProductRequest productRequest = ProductRequest.from("치킨", new BigDecimal("20000"), ProductStatus.ENABLE, 10);
        log.info("log message {}", fields(productRequest));

        log.info("log message {}", foo(foo));
    }

    public void callMarkers() {

        log.info(append("name", "value"), "log message");

        log.info(append("name1", "value1").and(append("name2", "value2")), "log message");

        Map myMap = new HashMap();
        myMap.put("name1", "value1");
        myMap.put("name2", "value2");
        log.info(appendEntries(myMap), "log message");

        log.info(appendArray("array", 1, 2, 3), "log message");

        log.info(appendRaw("array", "[1,2,3]"), "log message");

        final ProductRequest productRequest = ProductRequest.from("치킨", new BigDecimal("20000"), ProductStatus.ENABLE, 10);
        log.info(append("object", productRequest), "log message");

        log.info(appendFields(productRequest), "log message");
    }

    public static StructuredArgument foo(Foo foo) {
        return value("foo", foo);
    }

    private class Foo {
    }
}
