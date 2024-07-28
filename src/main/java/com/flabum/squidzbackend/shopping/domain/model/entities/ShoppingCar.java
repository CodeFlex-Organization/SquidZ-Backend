package com.flabum.squidzbackend.shopping.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class ShoppingCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shoppingCar")
    private List<SalesOrderItem> items;

    public ShoppingCar() {
        this.items = List.of();
    }

    public ShoppingCar(List<SalesOrderItem> items) {
        this.items = items;
    }

    Float getTotalPrices() {
        return items.stream().map(SalesOrderItem::getTotalPrice).reduce(0f, Float::sum);
    }

}
