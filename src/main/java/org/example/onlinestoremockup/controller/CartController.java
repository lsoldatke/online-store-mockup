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
@RequestMapping("/onlinestoremockup/cart")
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
        return "cart";
    }

    @PostMapping
    public String addToCart(@RequestParam("item-id") Long itemId, @RequestParam("quantity") Integer quantity, Model model) {
        if (quantity < 1) {
            model.addAttribute("operationValid", false);
        } else {
            Optional<Item> optionalItem = itemService.getItemById(itemId);

            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                cartService.addItemToCart(item, quantity);
                model.addAttribute("operationValid", true);
                model.addAttribute("itemName", item.getName());
                model.addAttribute("itemQuantity", quantity);
                model.addAttribute("itemTotalPrice", item.getPrice() * quantity);
            }
        }

        return "addedToCart";
    }
}
