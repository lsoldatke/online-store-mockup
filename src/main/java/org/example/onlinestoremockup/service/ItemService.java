package org.example.onlinestoremockup.service;

import org.example.onlinestoremockup.model.Item;
import org.example.onlinestoremockup.model.ItemCategory;
import org.example.onlinestoremockup.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public List<ItemCategory> getAllCategories() {
        return itemRepository.getAllCategories();
    }

    public List<Item> getItemsByCategory(ItemCategory itemCategory) {
        return itemRepository.getByCategory(itemCategory);
    }

    public List<Item> getItemsByName(String itemName) {
        return itemRepository.getByName(itemName);
    }
}
