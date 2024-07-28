package com.flabum.squidzbackend.shopping.domain.services;

import com.flabum.squidzbackend.shopping.domain.model.entities.Product;
import com.flabum.squidzbackend.shopping.domain.model.queries.GetAllProductsQuery;

import java.util.List;

public interface ProductQueryService {
    List<Product> handle(GetAllProductsQuery query);
}
