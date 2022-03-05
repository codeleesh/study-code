package com.lovethefeel.before.application;

import com.lovethefeel.before.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.List;

@SpringBootTest
class ItemServiceTest {

    private StopWatch stopWatch;

    @Autowired
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        stopWatch = new StopWatch();
    }

    @Test
    void processFromDataBefore() {
        List<Item> lists = itemService.lists();

        long startTime = System.currentTimeMillis();

        itemService.processFromDataBefore(lists);

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("took " + estimatedTime + " ms");
    }

    @Test
    void processFromDataAfter() {
        List<Item> lists = itemService.lists();

        stopWatch.start("processFromDataAfter");

        itemService.processFromDataAfter(lists);

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}