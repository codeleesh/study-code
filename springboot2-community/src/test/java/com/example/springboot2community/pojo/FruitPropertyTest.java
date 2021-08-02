package com.example.springboot2community.pojo;

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
        assertThat("banana").isEqualTo(fruitData.get(0).getName());
        assertThat("yellow").isEqualTo(fruitData.get(0).getColor());
        assertThat("apple").isEqualTo(fruitData.get(1).getName());
        assertThat("red").isEqualTo(fruitData.get(1).getColor());
        assertThat("water melon").isEqualTo(fruitData.get(2).getName());
        assertThat("green").isEqualTo(fruitData.get(2).getColor());
    }
}
