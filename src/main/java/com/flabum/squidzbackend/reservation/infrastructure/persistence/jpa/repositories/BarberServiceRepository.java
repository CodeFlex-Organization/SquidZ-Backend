package com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories;

import com.flabum.squidzbackend.reservation.domain.model.entities.BarberService;
import com.flabum.squidzbackend.reservation.domain.model.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarberServiceRepository extends JpaRepository<BarberService, Long> {
    Optional<BarberService> findById(Long barberServiceId);
}
