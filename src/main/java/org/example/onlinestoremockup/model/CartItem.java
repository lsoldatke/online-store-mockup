package org.example.onlinestoremockup.model;

import lombok.Data;

@Data
public class CartItem {
    private Long articleId;
    private int quantity;

    public CartItem(Long articleId, Integer quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }
}
