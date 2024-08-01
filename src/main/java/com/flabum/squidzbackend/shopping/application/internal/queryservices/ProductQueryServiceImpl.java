package com.flabum.squidzbackend.shopping.application.internal.queryservices;

import com.flabum.squidzbackend.shopping.domain.model.entities.Product;
import com.flabum.squidzbackend.shopping.domain.model.queries.product.GetAllProductsQuery;
import com.flabum.squidzbackend.shopping.domain.model.queries.product.GetProductByIdQuery;
import com.flabum.squidzbackend.shopping.domain.services.product.ProductQueryService;
import com.flabum.squidzbackend.shopping.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {
    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.id());
    }
}
