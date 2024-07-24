package com.flabum.squidzbackend.reservation.application.internal.commandservices;

import com.flabum.squidzbackend.reservation.application.internal.queryservices.ReservationQueryServiceImpl;
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
    public Long handle(CreateReservationCommand command) {
        var reservation = new Reservation(command.date(), command.time());
        reservationRepository.save(reservation);
        return reservation.getId();

    }

    @Override
    public void handle(DeleteReservationCommand command) {

    }

    @Override
    public Long handle(ConfirmReservationCommand command) {
        return null;
    }
}
