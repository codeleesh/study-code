package com.lovethefeel.before.application;

import com.lovethefeel.before.domain.Item;
import com.lovethefeel.before.domain.ItemRepository;
import com.lovethefeel.before.domain.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> lists() {
        return itemRepository.findAll();
    }

    public List<Item> processFromDataBefore(final List<Item> items) {
        return items.stream()
                .filter(e -> e.getStatus() == Status.DISABLE)
                .collect(Collectors.toList());
    }

    /**
     * List -> Stream -> List -> Stream -> List
     * @param items
     * @return
     */
    public List<Item> processData(final List<Item> items) {
        return items.stream().flatMap(e -> {
            List<Item> innerItems = new ArrayList<>();
            if (e.getStatus() == Status.DISABLE) {
                innerItems.add(e);
            }
            return innerItems.stream();
        }).collect(Collectors.toList());
    }

    public List<Item> processFromDataAfter(final List<Item> items) {
        return items.stream()
                .filter(e -> e.getStatus() == Status.DISABLE)
                .flatMap(this::processData)
                .collect(Collectors.toList());
    }
    /**
     *
     * @param item
     * @return
     */
    public Stream<Item> processData(final Item item) {
        if (Objects.nonNull(item)) {
            return Stream.of(item);
        }
        throw new IllegalStateException();
    }
}
