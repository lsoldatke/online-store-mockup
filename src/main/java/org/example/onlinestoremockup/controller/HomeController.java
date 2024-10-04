package org.example.onlinestoremockup.controller;

import org.example.onlinestoremockup.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ArticleService articleService;

    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Recommended articles");
        model.addAttribute("articles", articleService.getAll());
        model.addAttribute("categories", articleService.getCategories());

        return "index";
    }
}
