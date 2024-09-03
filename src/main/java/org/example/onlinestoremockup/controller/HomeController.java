package org.example.onlinestoremockup.controller;

import org.example.onlinestoremockup.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ItemService itemService;

    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Recommended articles");
        model.addAttribute("items", itemService.getAll());
        model.addAttribute("categories", itemService.getCategories());

        return "index";
    }
}
