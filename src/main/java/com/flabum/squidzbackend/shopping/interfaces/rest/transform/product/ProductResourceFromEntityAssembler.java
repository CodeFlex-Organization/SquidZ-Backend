package com.flabum.squidzbackend.shopping.interfaces.rest.transform.product;

import com.flabum.squidzbackend.shopping.domain.model.entities.Product;
import com.flabum.squidzbackend.shopping.interfaces.rest.resources.product.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getProductCategory()
        );
    }
}
