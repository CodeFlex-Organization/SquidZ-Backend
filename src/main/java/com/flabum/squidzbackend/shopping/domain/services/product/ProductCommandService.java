package com.flabum.squidzbackend.shopping.domain.services.product;

import com.flabum.squidzbackend.shopping.domain.model.commands.product.CreateProductCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.product.DeleteProductCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.product.UpdateProductCommand;
import com.flabum.squidzbackend.shopping.domain.model.entities.Product;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product> handle(CreateProductCommand command);
    Optional<Product> handle(UpdateProductCommand command);
    void handle(DeleteProductCommand command);
}
