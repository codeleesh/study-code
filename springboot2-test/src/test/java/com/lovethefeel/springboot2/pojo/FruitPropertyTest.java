package com.lovethefeel.springboot2.pojo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FruitPropertyTest {

    @Autowired
    FruitProperty fruitProperty;

    @Test
    public void testGetFruit() {
        List<Fruit> fruitData = fruitProperty.getList();

        assertThat(3).isEqualTo(fruitData.size());
        assertThat(fruitData.get(0).getName()).isEqualTo("banana");
        assertThat(fruitData.get(0).getColor()).isEqualTo("yellow");
        assertThat(fruitData.get(1).getName()).isEqualTo("apple");
        assertThat(fruitData.get(1).getColor()).isEqualTo("red");
        assertThat(fruitData.get(2).getName()).isEqualTo("water melon");
        assertThat(fruitData.get(2).getColor()).isEqualTo("green");
    }
}
