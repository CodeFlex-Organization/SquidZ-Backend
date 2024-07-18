package com.flabum.squidzbackend.reservation.domain.model.commands;

import com.flabum.squidzbackend.reservation.domain.model.entities.BarberService;
import com.flabum.squidzbackend.reservation.domain.model.entities.Local;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateReservationCommand(Local local, LocalDate date, LocalTime time, BarberService barberService) {
}
