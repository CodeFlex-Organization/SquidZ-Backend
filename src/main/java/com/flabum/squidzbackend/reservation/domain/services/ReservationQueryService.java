package com.flabum.squidzbackend.reservation.domain.services;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetAllReservationsQuery;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetLocalByIdQuery;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetReservationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    List<Reservation> handle (GetAllReservationsQuery command);
    Optional<Reservation> handle (GetReservationByIdQuery command);


}
