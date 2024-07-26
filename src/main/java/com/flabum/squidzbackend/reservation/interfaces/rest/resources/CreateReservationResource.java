package com.flabum.squidzbackend.reservation.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateReservationResource(LocalDate date, LocalTime time ) {
}
