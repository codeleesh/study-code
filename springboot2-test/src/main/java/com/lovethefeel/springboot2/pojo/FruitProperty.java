package com.lovethefeel.springboot2.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("fruit")
public class FruitProperty {
    private List<Fruit> list;
}
