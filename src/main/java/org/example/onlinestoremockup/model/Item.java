package org.example.onlinestoremockup.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    @Enumerated(EnumType.STRING)
    private ItemCategory category;
    private String imageUrl;
    private int available;
}
