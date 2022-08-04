package me.lovethefeel.before.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @DisplayName("Item 저장 테스트")
    @Test
    void saveItem() {
        Item item = Item.of("컴퓨터", Status.ENABLE);
        Item saveItem = repository.save(item);

        Item findItem = repository.findById(saveItem.getId()).get();

        assertThat(saveItem).isEqualTo(findItem);
    }

    @DisplayName("Item 대량 저장 테스트")
    @Test
    void saveBulkItem() {
        List<Item> items = new ArrayList<>();
        for (int i=0; i<1000; i++) {
            Item item = Item.of("품목" + i, Status.ENABLE);
            items.add(item);
        }
        repository.saveAll(items);
    }
}