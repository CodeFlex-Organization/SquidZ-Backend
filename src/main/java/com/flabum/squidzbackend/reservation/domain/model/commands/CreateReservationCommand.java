package com.flabum.squidzbackend.reservation.domain.model.commands;

import com.flabum.squidzbackend.reservation.domain.model.entities.BarberService;
import com.flabum.squidzbackend.reservation.domain.model.entities.Local;
import jakarta.persistence.OneToOne;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateReservationCommand(LocalDate date, LocalTime time) {
}


