package me.lovethefeel.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

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
