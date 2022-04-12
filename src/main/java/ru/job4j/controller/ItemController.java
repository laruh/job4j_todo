package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Item;
import ru.job4j.service.ItemService;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String items(Model model) {
        model.addAttribute("items", itemService.getALLItems());
        return "items";
    }

    @GetMapping("/showNew")
    public String itemsNew(Model model) {
        model.addAttribute("items", itemService.findByConditionDone(false));
        return "items";
    }

    @GetMapping("/showDone")
    public String itemsDone(Model model) {
        model.addAttribute("items", itemService.findByConditionDone(true));
        return "items";
    }

    @GetMapping("/addItem")
    public String addItem(Model model) {
        return "addItem";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute Item item) {
        itemService.add(item);
        return "redirect:/items";
    }

    @GetMapping("/updateItem/{itemId}")
    public String updateItem(Model model, @PathVariable("itemId") int id) {
        model.addAttribute("item", itemService.findById(id));
        return "updateItem";
    }

    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute Item item) {
        itemService.update(item);
        return "redirect:/descriptionItem/" + item.getId();
    }

    @GetMapping("/descriptionItem/{itemId}")
    public String descriptionItem(Model model, @PathVariable("itemId") int id) {
        model.addAttribute("item", itemService.findById(id));
        return "descriptionItem";
    }

    @GetMapping("/doneItem/{itemId}")
    public String doneItem(@PathVariable("itemId") int id) {
        itemService.makeDoneById(id);
        return "redirect:/descriptionItem/" + id;
    }

    @GetMapping("/deleteItem/{itemId}")
    public String deleteItem(@PathVariable("itemId") int id) {
        itemService.deleteById(id);
        return "redirect:/items";
    }
}
