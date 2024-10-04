package org.example.onlinestoremockup.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemDto {
    private Article article;
    private int quantity;
}
