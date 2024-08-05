package com.flabum.squidzbackend.shopping.interfaces.rest.resources.product;

import com.flabum.squidzbackend.shopping.domain.model.valueobjects.ProductCategories;

public record CreateProductResource(
        String name,
        String description,
        Float price,
        ProductCategories productCategory
) {
}