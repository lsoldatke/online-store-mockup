package org.example.onlinestoremockup.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CartItem {
    private Long articleId;
    private int quantity;

    public CartItem(Long articleId, int quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }
}
