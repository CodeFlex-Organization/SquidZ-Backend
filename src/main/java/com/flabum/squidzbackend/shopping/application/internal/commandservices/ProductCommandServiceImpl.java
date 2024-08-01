package com.flabum.squidzbackend.shopping.application.internal.commandservices;

import com.flabum.squidzbackend.shopping.domain.model.commands.product.CreateProductCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.product.DeleteProductCommand;
import com.flabum.squidzbackend.shopping.domain.model.commands.product.UpdateProductCommand;
import com.flabum.squidzbackend.shopping.domain.model.entities.Product;
import com.flabum.squidzbackend.shopping.domain.services.product.ProductCommandService;
import com.flabum.squidzbackend.shopping.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {
        var product = new Product();
        productRepository.save(product);
        return Optional.of(product);
    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        if (productRepository.existsByIdIsNot(command.id()))
            throw new IllegalArgumentException("Product with same id already exists.");
        var productFound = productRepository.findById(command.id());
        if (productFound.isEmpty()) {
            throw new IllegalArgumentException("This product does not exists.");
        }
        var productToUpdate = productFound.get();
        try {
            var updatedProduct = productRepository.save(productToUpdate.updateProduct(command.name(), command.description(), command.price(), command.productCategory()));
            return Optional.of(updatedProduct);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating product: " + e.getMessage() + ".");
        }
    }

    @Override
    public void handle(DeleteProductCommand command) {
        if (!productRepository.existsById(command.id())) {
            throw new IllegalArgumentException("This product does not exists.");
        }
        try {
            productRepository.deleteById(command.id());
        } catch (Exception e){
            throw new IllegalArgumentException("Error while deleting product: " + e.getMessage() + ".");
        }
    }
}
