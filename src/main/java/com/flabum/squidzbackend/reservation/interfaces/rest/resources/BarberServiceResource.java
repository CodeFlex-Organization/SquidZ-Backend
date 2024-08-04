package com.flabum.squidzbackend.reservation.interfaces.rest.resources;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

public record BarberServiceResource(Long id, String name, Float price, String duration) {
}
//    private Long id;
//
//    @Getter
//    private String name;
//
//    @Getter
//    private Float price;
//
//    @Getter
//    private String duration;