package org.example.onlinestoremockup.controller;

import org.example.onlinestoremockup.model.Cart;
import org.example.onlinestoremockup.model.Item;
import org.example.onlinestoremockup.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/onlinestoremockup/cart")
public class CartController {
    private Cart cart;
    private final ItemService itemService;

    public CartController(Cart cart, ItemService itemService) {
        this.cart = cart;
        this.itemService = itemService;
    }

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("items", cart.getItems());
        return "cart";
    }

    @PostMapping
    public String addToCart(@RequestParam("item-id") Long itemId, @RequestParam("quantity") Integer quantity, Model model) {
        Optional<Item> optionalItem = itemService.getItemById(itemId);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            cart.addItem(item, quantity);
            model.addAttribute("itemName", item.getName());
            model.addAttribute("itemQuantity", quantity);
            model.addAttribute("itemTotalPrice", item.getPrice() * quantity);
        }

        return "addedToCart";
    }
}
