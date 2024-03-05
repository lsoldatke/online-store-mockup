package org.example.onlinestoremockup.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    @Getter
    private static Map<Item, Integer> itemsInCart = new HashMap<>();

    public static void addItem(Item item, Integer quantity) {
        itemsInCart.put(item, quantity);
    }

    public static void removeItem(Item item) {
        itemsInCart.remove(item);
    }
}
