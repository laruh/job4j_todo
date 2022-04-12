package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Item;
import ru.job4j.persistence.ItemStore;

import java.util.List;

@Service
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

    public Item findById(int id) {
        return store.findById(id);
    }

    public void update(Item item) {
        store.update(item);
    }

    public List<Item> findByConditionDone(boolean condition) {
        return store.findByConditionDone(condition);
    }

    public void makeDoneById(int id) {
        store.makeDoneById(id);
    }

    public void deleteById(int id) {
        store.deleteById(id);
    }
}
