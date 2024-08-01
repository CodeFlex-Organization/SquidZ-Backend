package com.flabum.squidzbackend.shopping.infrastructure.persistence.jpa.repositories;

import com.flabum.squidzbackend.shopping.domain.model.entities.ShoppingCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCarRepository extends JpaRepository<ShoppingCar, Long> {
    Optional<ShoppingCar> findById(Long id);
}
