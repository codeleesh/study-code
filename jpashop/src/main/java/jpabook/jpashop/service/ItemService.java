package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(final Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(final Long itemId, final Book bookParam) {
        final Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(bookParam.getPrice());
        findItem.setName(bookParam.getName());
        findItem.setStockQuantity(bookParam.getStockQuantity());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(final Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
