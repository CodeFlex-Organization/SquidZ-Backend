package com.flabum.squidzbackend.reservation.domain.services;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.entities.BarberService;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetAllBarberServicesQuery;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetAllReservationsQuery;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetReservationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BarberServiceQueryService {
    List<BarberService> handle (GetAllBarberServicesQuery command);
}
