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

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public List<Item> getByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> getByCategory(ItemCategory category) {
        return itemRepository.findByCategory(category);
    }

    public Optional<Item> getById(Long id) {
        return itemRepository.findById(id);
    }

    public List<ItemCategory> getCategories() {
        return itemRepository.findCategories();
    }

    public void add(Item item) {
        itemRepository.save(item);
    }
}
