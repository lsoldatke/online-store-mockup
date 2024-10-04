package org.example.onlinestoremockup.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.onlinestoremockup.model.Article;
import org.example.onlinestoremockup.model.ArticleCategory;
import org.example.onlinestoremockup.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAll() {
        return articleService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Article> getById(@PathVariable Long id) {
        return articleService.getById(id);
    }

    @PostMapping("/search")
    public String getByName(@RequestParam("searched-phrase") String searchedPhrase, Model model) {
        model.addAttribute("title", "Search results for \"" + searchedPhrase + "\"");
        model.addAttribute("articles", articleService.getByName(searchedPhrase));
        model.addAttribute("categories", articleService.getCategories());

        return "index";
    }

    @GetMapping("/category")
    public String getByCategory(@RequestParam String categoryName, Model model) {
        ArticleCategory articleCategory = ArticleCategory.valueOf(categoryName);

        model.addAttribute("title", categoryName);
        model.addAttribute("articles", articleService.getByCategory(articleCategory));
        model.addAttribute("categories", articleService.getCategories());

        return "index";
    }

    @PostMapping
    public ResponseEntity<Article> add(@RequestBody Article article) {
        articleService.add(article);

        return ResponseEntity.ok(article);
    }
}
