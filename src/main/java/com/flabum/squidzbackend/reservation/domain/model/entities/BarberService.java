package com.flabum.squidzbackend.reservation.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BarberService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;

    @Getter
    private Float price;

    @Getter
    private String duration;

//    public BarberService(String name, Float price, String duration) {
//        this.name = name;
//        this.price = price;
//        this.duration = duration;
//    }
}
