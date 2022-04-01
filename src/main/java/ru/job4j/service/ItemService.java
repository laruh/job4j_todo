package ru.job4j.service;

import ru.job4j.model.Item;
import ru.job4j.persistence.ItemStore;

import java.util.List;

public class ItemService {
    private final ItemStore store;

    public ItemService(ItemStore store) {
        this.store = store;
    }

    public void add(Item item) {
        store.add(item);
    }

    public List<Item> getALLItems() {
        return store.getALLItems();
    }
}
