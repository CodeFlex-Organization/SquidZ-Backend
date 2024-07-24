package com.flabum.squidzbackend.reservation.domain.model.aggregates;

import com.flabum.squidzbackend.reservation.domain.model.entities.BarberService;
import com.flabum.squidzbackend.reservation.domain.model.entities.Local;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Getter
//    private User user;

    @OneToOne
    private Local local;

    @Getter
    private LocalDate date;

    @Getter
    private LocalTime time;

    @ManyToOne
    private BarberService barberService;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updateAt;

    public Reservation(LocalDate date, LocalTime time) {

        this();
        this.date = date;
        this.time = time;
    }

//    public Reservation(Local local, LocalDate date, LocalTime time, BarberService barberService) {
//        this.local = local;
//        this.date = date;
//        this.time = time;
//        this.barberService = barberService;
//    }
}
