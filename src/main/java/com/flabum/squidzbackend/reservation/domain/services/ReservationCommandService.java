package com.flabum.squidzbackend.reservation.domain.services;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.commands.ConfirmReservationCommand;
import com.flabum.squidzbackend.reservation.domain.model.commands.CreateReservationCommand;
import com.flabum.squidzbackend.reservation.domain.model.commands.DeleteReservationCommand;

import java.util.Optional;

public interface ReservationCommandService {
    Optional<Reservation> handle (CreateReservationCommand command);
    void handle (DeleteReservationCommand command);

    void handle(ConfirmReservationCommand command);
}
