package org.example.onlinestoremockup.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Cart {
    @Getter
    private List<CartItem> items = new ArrayList<>();

//    public void addItem(Item item, Integer quantity) {
//        for (CartItem item : items) {
//            if ()
//        }
//    }

    public void removeItem(Long itemId) {
        for (Item item : items.keySet()) {
            if (item.getId().equals(itemId)) {
                items.remove(item);
            }
        }
    }
}
