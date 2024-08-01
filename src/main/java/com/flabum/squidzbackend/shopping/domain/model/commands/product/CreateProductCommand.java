package com.flabum.squidzbackend.shopping.domain.model.commands.product;

import com.flabum.squidzbackend.shopping.domain.model.valueobjects.ProductCategories;

public record CreateProductCommand(String name, String description, Float price, ProductCategories productCategory) {
}
