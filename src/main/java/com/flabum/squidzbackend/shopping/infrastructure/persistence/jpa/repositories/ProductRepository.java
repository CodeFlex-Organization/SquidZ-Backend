package com.flabum.squidzbackend.shopping.infrastructure.persistence.jpa.repositories;

import com.flabum.squidzbackend.shopping.domain.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByIdIsNot(Long id);
}
