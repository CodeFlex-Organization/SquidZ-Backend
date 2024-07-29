package com.flabum.squidzbackend.shopping.infrastructure.persistence.jpa.repositories;

import com.flabum.squidzbackend.shopping.domain.model.aggregates.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
    Optional<SalesOrder> findById(Long id);
}
