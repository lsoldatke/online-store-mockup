package org.example.onlinestoremockup.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Cart {
    @Getter
    private Map<Item, Integer> items = new HashMap<>();

    public void addItem(Item item, Integer quantity) {
        items.put(item, quantity);
    }
}
