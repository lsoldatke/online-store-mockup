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
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Item> getById(@PathVariable Long id) {
        return itemService.getById(id);
    }

    @PostMapping("/search")
    public String getByName(@RequestParam("searched-phrase") String searchedPhrase, Model model) {
        model.addAttribute("title", "Search results for \"" + searchedPhrase + "\"");
        model.addAttribute("items", itemService.getByName(searchedPhrase));
        model.addAttribute("categories", itemService.getCategories());

        return "index";
    }

    @GetMapping("/category")
    public String getByCategory(@RequestParam String categoryName, Model model) {
        ItemCategory itemCategory = ItemCategory.valueOf(categoryName);

        model.addAttribute("title", categoryName);
        model.addAttribute("items", itemService.getByCategory(itemCategory));
        model.addAttribute("categories", itemService.getCategories());

        return "index";
    }

    @PostMapping
    public ResponseEntity<Item> add(@RequestBody Item item) {
        itemService.add(item);

        return ResponseEntity.ok(item);
    }
}
