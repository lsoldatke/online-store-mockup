package org.example.onlinestoremockup.controller;

import org.example.onlinestoremockup.model.Article;
import org.example.onlinestoremockup.model.CartItem;
import org.example.onlinestoremockup.model.CartItemDto;
import org.example.onlinestoremockup.service.CartService;
import org.example.onlinestoremockup.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ArticleService articleService;

    public CartController(CartService cartService, ArticleService articleService) {
        this.cartService = cartService;
        this.articleService = articleService;
    }

    @GetMapping
    public String showCart(Model model) {
        List<CartItem> cartItems = cartService.getItems();
        List<CartItemDto> cartItemDtos = cartService.getDetailedItems(cartItems);

        model.addAttribute("items", cartItemDtos);
        model.addAttribute("totalCost", cartService.getTotalCost());

        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("articleId") Long articleId, @RequestParam("quantity") int quantity, Model model) {
        if (quantity < 1) {
            model.addAttribute("operationValid", false);
        } else {
            Optional<Article> optionalItem = articleService.getById(articleId);

            if (optionalItem.isPresent()) {
                Article article = optionalItem.get();
                cartService.addItem(articleId, quantity);
                model.addAttribute("operationValid", true);
                model.addAttribute("itemName", article.getName());
                model.addAttribute("itemQuantity", quantity);
                model.addAttribute("itemTotalPrice", article.getPrice() * quantity);
            }
        }

        return "added-to-cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("item-id") Long itemId, Model model) {
        Optional<Article> optionalItem = articleService.getById(itemId);

        if (optionalItem.isPresent()) {
            Article article = optionalItem.get();
            cartService.removeItemFromCart(itemId);
            model.addAttribute("itemName", article.getName());
        }

        return "removed-from-cart";
    }
}
