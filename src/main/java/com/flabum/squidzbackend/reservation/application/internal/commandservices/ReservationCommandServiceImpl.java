package com.flabum.squidzbackend.reservation.application.internal.commandservices;

import com.flabum.squidzbackend.reservation.application.internal.queryservices.ReservationQueryServiceImpl;
import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.commands.CreateReservationCommand;
import com.flabum.squidzbackend.reservation.domain.model.commands.DeleteReservationCommand;
import com.flabum.squidzbackend.reservation.domain.services.ReservationCommandService;
import com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;

import java.util.Optional;

public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository){

        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> handle(CreateReservationCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteReservationCommand command) {

    }
}
