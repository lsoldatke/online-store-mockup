package org.example.onlinestoremockup.repository;

import org.example.onlinestoremockup.model.Article;
import org.example.onlinestoremockup.model.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.name LIKE ?1")
    List<Article> findByName(String name);

    @Query("SELECT a FROM Article a WHERE a.category = ?1")
    List<Article> findByCategory(ArticleCategory category);

    @Query("SELECT DISTINCT(a.category) FROM Article a")
    List<ArticleCategory> findCategories();
}
