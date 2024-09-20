package org.example.onlinestoremockup.model;

import lombok.Data;

@Data
public class CartItem {
    private Long articleId;
    private int quantity;
}
