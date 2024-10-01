package org.example.onlinestoremockup.controller;

import org.example.onlinestoremockup.model.Item;
import org.example.onlinestoremockup.service.CartService;
import org.example.onlinestoremockup.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ItemService itemService;

    public CartController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("totalCost", cartService.getTotalCost());
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("articleId") Long articleId, @RequestParam("quantity") int quantity, Model model) {
        if (quantity < 1) {
            model.addAttribute("operationValid", false);
        } else {
            Optional<Item> optionalItem = itemService.getById(articleId);

            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                cartService.addItem(articleId, quantity);
                model.addAttribute("operationValid", true);
                model.addAttribute("itemName", item.getName());
                model.addAttribute("itemQuantity", quantity);
                model.addAttribute("itemTotalPrice", item.getPrice() * quantity);
            }
        }

        return "added-to-cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("item-id") Long itemId, Model model) {
        Optional<Item> optionalItem = itemService.getById(itemId);

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            cartService.removeItemFromCart(itemId);
            model.addAttribute("itemName", item.getName());
        }

        return "removed-from-cart";
    }
}
