package org.example.onlinestoremockup.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    @Enumerated(EnumType.STRING)
    private ArticleCategory category;
    private String imageUrl;
    private int available;
}
