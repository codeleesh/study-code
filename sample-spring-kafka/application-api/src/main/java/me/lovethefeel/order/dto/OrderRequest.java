package me.lovethefeel.order.dto;

import lombok.*;

import java.math.BigDecimal;

import static lombok.AccessLevel.*;

@Setter
@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PUBLIC)
@ToString
public class OrderRequest {

    private long id;
    private String name;
    private BigDecimal amount;
}
