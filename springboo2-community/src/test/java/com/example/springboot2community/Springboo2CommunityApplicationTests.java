package com.example.springboot2community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class Springboo2CommunityApplicationTests {

    @Value("${property.test.name}")
    private String propertyTestName;

    @Value("${propertyTest}")
    private String propertyTest;

    @Value("${noKey:default value}")
    private String defaultValue;

    @Value("${propertyTestList}")
    private String[] propertyTestArray;

    @Value("#{'${propertyTestList}'.split(',')}")
    private List<String> propertyTestList;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testValue() {
        assertThat("property depth test").isEqualTo(propertyTestName);
        assertThat("test").isEqualTo(propertyTest);
        assertThat("default value").isEqualTo(defaultValue);
        assertThat("a").isEqualTo(propertyTestArray[0]);
        assertThat("c").isEqualTo(propertyTestList.get(2));
    }

}
