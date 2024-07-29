package com.flabum.squidzbackend.shopping.domain.model.entities;

import com.flabum.squidzbackend.shopping.domain.model.valueobjects.ProductCategories;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Product {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private Float price;

    @Enumerated(EnumType.STRING)
    private ProductCategories productCategory;

    public Product(){
        this.name = "";
        this.description = "";
        this.price = 0f;
        this.productCategory = null;
    }

    public Product(String name, String description, Float price, ProductCategories productCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
    }
}
