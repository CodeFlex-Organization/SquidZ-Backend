package com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

//    Optional<Reservation> findById(Reservation reservation);
}
