package org.example.onlinestoremockup.service;

import org.example.onlinestoremockup.model.Article;
import org.example.onlinestoremockup.model.Cart;
import org.example.onlinestoremockup.model.CartItem;
import org.example.onlinestoremockup.model.CartItemDto;
import org.example.onlinestoremockup.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final Cart cart;
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    public CartService(Cart cart, ArticleRepository articleRepository, ArticleService articleService) {
        this.cart = cart;
        this.articleRepository = articleRepository;
        this.articleService = articleService;
    }

    public List<CartItem> getItems() {
        return cart.getItems();
    }

    public List<CartItemDto> getDetailedItems(List<CartItem> cartItems) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            Article article = articleService.getById(cartItem.getArticleId()).orElseThrow(() -> new RuntimeException("Item " +
                    "with id " + cartItem.getArticleId() + " not found"));

            CartItemDto cartItemDto = new CartItemDto(article, cartItem.getQuantity());

            cartItemDtos.add(cartItemDto);
        }

        return cartItemDtos;
    }

    public void addItem(Long articleId, int quantity) {
        cart.addArticle(articleId, quantity);
    }

    public void removeItemFromCart(Long itemId) {
        cart.removeArticle(itemId);
    }

    public float getTotalCost() {
        /*
            TODO:
                - Change float to BigDecimal
         */

        float totalCost = 0.0f;

        for (CartItem cartItem : cart.getItems()) {
            Article article = articleRepository.findById(cartItem.getArticleId()).orElseThrow(() ->
                    new RuntimeException("Item with id " + cartItem.getArticleId() + " not found"));

            totalCost += article.getPrice();
        }

        return totalCost;
    }
}
