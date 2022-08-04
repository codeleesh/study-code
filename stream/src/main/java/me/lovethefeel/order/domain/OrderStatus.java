package me.lovethefeel.order.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum OrderStatus {

    TEMPSAVE("01", "임시저장"),
    INPROGRESS("02", "주문진행중"),
    COMPLETED("03", "주문완료");

    private String typeCode;
    private String typeName;

    OrderStatus(final String typeCode, final String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    private static final Map<String, String> orderStatusMap = Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(OrderStatus::getTypeCode, OrderStatus::name)));

    public static OrderStatus from(final String typeCode) {

        return OrderStatus.valueOf(orderStatusMap.get(typeCode));
    }
}
