package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.Item;
import ru.job4j.persistence.ItemStore;

@Controller
public class ItemController {
    private final ItemStore itemStore;

    public ItemController(ItemStore itemStore) {
        this.itemStore = itemStore;
    }

    @GetMapping("/items")
    public String items(Model model) {
        model.addAttribute("items", itemStore.getALLItems());
        return "items";
    }

    @GetMapping("/addItem")
    public String addItem(Model model) {
        return "addItem";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute Item item) {
        itemStore.add(item);
        return "redirect:/items";
    }
}
