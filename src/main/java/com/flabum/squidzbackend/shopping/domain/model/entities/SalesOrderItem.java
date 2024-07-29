package com.flabum.squidzbackend.shopping.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SalesOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

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
