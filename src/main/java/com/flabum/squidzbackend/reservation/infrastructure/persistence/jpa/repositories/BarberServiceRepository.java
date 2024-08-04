package com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.entities.BarberService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberServiceRepository extends JpaRepository<BarberService, Long> {
}
