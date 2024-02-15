package org.example.onlinestoremockup.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.onlinestoremockup.model.Item;
import org.example.onlinestoremockup.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/items")
    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        itemService.addItem(item);
        return ResponseEntity.ok(item);
    }
}
