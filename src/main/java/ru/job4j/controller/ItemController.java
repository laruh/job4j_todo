package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import ru.job4j.persistence.ItemStore;

@Controller
public class ItemController {
    private final ItemStore itemStore;

    public ItemController(ItemStore itemStore) {
        this.itemStore = itemStore;
    }
}
