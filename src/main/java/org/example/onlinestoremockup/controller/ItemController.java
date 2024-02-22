package org.example.onlinestoremockup.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.onlinestoremockup.model.Item;
import org.example.onlinestoremockup.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/onlinestoremockup/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @GetMapping
    public List<Item> returnAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Optional<Item> returnItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        itemService.addItem(item);
        return ResponseEntity.ok(item);
    }
}
