package org.example.onlinestoremockup.service;

import org.example.onlinestoremockup.model.Article;
import org.example.onlinestoremockup.model.ArticleCategory;
import org.example.onlinestoremockup.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public List<Article> getByName(String name) {
        return articleRepository.findByName(name);
    }

    public List<Article> getByCategory(ArticleCategory category) {
        return articleRepository.findByCategory(category);
    }

    public Optional<Article> getById(Long id) {
        return articleRepository.findById(id);
    }

    public List<ArticleCategory> getCategories() {
        return articleRepository.findCategories();
    }

    public void add(Article article) {
        articleRepository.save(article);
    }
}
