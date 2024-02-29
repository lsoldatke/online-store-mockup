package org.example.onlinestoremockup.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.onlinestoremockup.model.Item;
import org.example.onlinestoremockup.model.ItemCategory;
import org.example.onlinestoremockup.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/category")
    public String returnItemsByCategory(@RequestParam String categoryName, Model model) {
        ItemCategory itemCategory = ItemCategory.valueOf(categoryName);
        model.addAttribute("results", itemService.getItemsByCategory(itemCategory));
        model.addAttribute("itemCategories", itemService.getAllCategories());
        return "results";
    }

    @PostMapping("/search")
    public String returnItemsByName(@RequestParam("searched-phrase") String searchedPhrase, Model model) {
        model.addAttribute("results", itemService.getItemsByName(searchedPhrase));
        model.addAttribute("itemCategories", itemService.getAllCategories());
        return "results";
    }
}
