package org.example.onlinestoremockup.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addArticle(Long articleId, int quantity) {
        for (CartItem item : items) {
            if (item.getArticleId().equals(articleId)) {
                item.increaseQuantity(quantity);
                return;
            }
        }

        items.add(new CartItem(articleId, quantity));
    }

    public void removeArticle(Long articleId) {
        items.removeIf(item -> item.getArticleId().equals(articleId));
    }
}
