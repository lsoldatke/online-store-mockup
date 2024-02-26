package org.example.onlinestoremockup.controller;

import org.example.onlinestoremockup.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/onlinestoremockup")
public class HomeController {
    private final ItemService itemService;

    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "index";
    }
}
