package com.flabum.squidzbackend.shopping.domain.services.product;

import com.flabum.squidzbackend.shopping.domain.model.entities.Product;
import com.flabum.squidzbackend.shopping.domain.model.queries.product.GetAllProductsQuery;
import com.flabum.squidzbackend.shopping.domain.model.queries.product.GetProductByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handle(GetAllProductsQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
}
