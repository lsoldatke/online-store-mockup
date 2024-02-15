package org.example.onlinestoremockup.controller;

import org.example.onlinestoremockup.model.Item;
import org.example.onlinestoremockup.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/onlinestoremockup")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @GetMapping("/items")
    public List<Item> returnAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/items/{id}")
    public Optional<Item> returnItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }
}
