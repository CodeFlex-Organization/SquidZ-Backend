package com.flabum.squidzbackend.shopping.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class SalesOrderItem {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Getter
    private Product product;

    @Getter
    private Integer quantity;

    public SalesOrderItem() {
        this.product = null;
        this.quantity = 0;
    }

    public SalesOrderItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Float getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
