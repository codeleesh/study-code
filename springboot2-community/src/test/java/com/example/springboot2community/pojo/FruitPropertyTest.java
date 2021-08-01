package com.example.springboot2community.pojo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FruitPropertyTest {

    @Autowired
    FruitProperty fruitProperty;

    @Test
    public void testGetFruit() {
        List<Map> fruitData = fruitProperty.getList();

        assertThat(3).isEqualTo(fruitData.size());
        assertThat("banana").isEqualTo(fruitData.get(0).get("name"));
        assertThat("yellow").isEqualTo(fruitData.get(0).get("color"));
        assertThat("apple").isEqualTo(fruitData.get(1).get("name"));
        assertThat("red").isEqualTo(fruitData.get(1).get("color"));
        assertThat("water melon").isEqualTo(fruitData.get(2).get("name"));
        assertThat("green").isEqualTo(fruitData.get(2).get("color"));
    }
}
