package org.example.onlinestoremockup.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.onlinestoremockup.model.Cart;
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
        model.addAttribute("title", categoryName);
        return "results";
    }

    @PostMapping("/search")
    public String returnItemsByName(@RequestParam("searched-phrase") String searchedPhrase, Model model) {
        model.addAttribute("results", itemService.getItemsByName(searchedPhrase));
        model.addAttribute("itemCategories", itemService.getAllCategories());
        model.addAttribute("title", "Search results for \"" + searchedPhrase + "\"");
        return "results";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("item-id") Long itemId, @RequestParam("quantity") Integer quantity, Model model) {
        Optional<Item> optionalItem = itemService.getItemById(itemId);

        if (optionalItem.isPresent()) {
            Cart.addItem(optionalItem.get(), quantity);
            model.addAttribute("addedItemName", optionalItem.get().getName());
            model.addAttribute("addedItemQuantity", quantity);
            model.addAttribute("addedItemTotalPrice", optionalItem.get().getPrice() * quantity);
        }

        System.out.println(Cart.getItemsInCart());

        return "addedToCart";
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("itemsInCart", Cart.getItemsInCart());

        return "cart";
    }
}
