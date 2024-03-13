package org.example.onlinestoremockup.service;

import org.example.onlinestoremockup.model.Cart;
import org.example.onlinestoremockup.model.Item;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {
    private final Cart cart;

    public CartService(Cart cart) {
        this.cart = cart;
    }

    public Map<Item, Integer> getItems() {
        return cart.getItems();
    }

    public void addItemToCart(Item item, Integer quantity) {
        cart.addItem(item, quantity);
    }
}
