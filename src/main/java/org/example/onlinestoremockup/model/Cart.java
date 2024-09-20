package org.example.onlinestoremockup.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    @Getter
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Long articleId, Integer quantity) {
        for (CartItem item : items) {
            if (item.getArticleId().equals(articleId)) {
                item.increaseQuantity(quantity);
                return;
            }
        }

        items.add(new CartItem(articleId, quantity));
    }

    public void removeItem(Long itemId) {
        for (Item item : items.keySet()) {
            if (item.getId().equals(itemId)) {
                items.remove(item);
            }
        }
    }
}
