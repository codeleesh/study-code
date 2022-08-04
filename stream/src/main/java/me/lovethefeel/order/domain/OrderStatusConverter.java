package me.lovethefeel.order.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(final OrderStatus orderStatus) {

        return orderStatus.getTypeCode();
    }

    @Override
    public OrderStatus convertToEntityAttribute(final String hobby) {

        return OrderStatus.valueOf(hobby);
    }
}
