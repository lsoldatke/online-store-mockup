package org.example.onlinestoremockup.service;

import org.example.onlinestoremockup.model.Cart;
import org.example.onlinestoremockup.model.CartItem;
import org.example.onlinestoremockup.model.Item;
import org.example.onlinestoremockup.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final Cart cart;
    private final ItemRepository itemRepository;

    public CartService(Cart cart, ItemRepository itemRepository) {
        this.cart = cart;
        this.itemRepository = itemRepository;
    }

    public List<CartItem> getItems() {
        return cart.getItems();
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
                - Check optional
         */

        float totalCost = 0.0f;

        for (CartItem cartItem : cart.getItems()) {
            Item item = itemRepository.findById(cartItem.getArticleId()).get();

            totalCost += item.getPrice();
        }

        return totalCost;
    }
}
