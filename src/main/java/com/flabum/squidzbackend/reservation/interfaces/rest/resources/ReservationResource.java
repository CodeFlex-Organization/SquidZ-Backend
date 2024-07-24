package com.flabum.squidzbackend.reservation.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResource(Long id, LocalDate date, LocalTime time ) {
}

//    private Long id;
//    private User user;
//    private Local local;
//    private LocalDate date;
//    private LocalTime time;