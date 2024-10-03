package org.example.onlinestoremockup.service;

import org.example.onlinestoremockup.model.Cart;
import org.example.onlinestoremockup.model.CartItem;
import org.example.onlinestoremockup.model.CartItemDto;
import org.example.onlinestoremockup.model.Item;
import org.example.onlinestoremockup.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final Cart cart;
    private final ItemService itemService;
    private final ItemRepository itemRepository;

    public CartService(Cart cart, ItemRepository itemRepository, ItemService itemService) {
        this.cart = cart;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    public List<CartItem> getItems() {
        return cart.getItems();
    }

    public List<CartItemDto> getDetailedItems(List<CartItem> cartItems) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            Item item = itemService.getById(cartItem.getArticleId()).orElseThrow(() -> new RuntimeException("Item " +
                    "with id " + cartItem.getArticleId() + " not found"));

            CartItemDto cartItemDto = new CartItemDto(item, cartItem.getQuantity());

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
            Item item = itemRepository.findById(cartItem.getArticleId()).orElseThrow(() ->
                    new RuntimeException("Item with id " + cartItem.getArticleId() + " not found"));

            totalCost += item.getPrice();
        }

        return totalCost;
    }
}
