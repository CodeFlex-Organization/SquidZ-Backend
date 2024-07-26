package com.flabum.squidzbackend.reservation.application.internal.commandservices;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.commands.ConfirmReservationCommand;
import com.flabum.squidzbackend.reservation.domain.model.commands.CreateReservationCommand;
import com.flabum.squidzbackend.reservation.domain.model.commands.DeleteReservationCommand;
import com.flabum.squidzbackend.reservation.domain.services.ReservationCommandService;
import com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository){

        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> handle(CreateReservationCommand command) {
        var reservation = new Reservation(
                command.user(),
                command.local(),
                command.date(),
                command.time(),
                command.barberService());
        reservationRepository.save(reservation);
        return Optional.of(reservation);
    }

    @Override
    public void handle(DeleteReservationCommand command) {

    }

    @Override
    public void handle(ConfirmReservationCommand command) {
    }
}
