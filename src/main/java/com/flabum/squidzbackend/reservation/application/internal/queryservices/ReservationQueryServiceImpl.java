package com.flabum.squidzbackend.reservation.application.internal.queryservices;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetAllReservationsQuery;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetLocalByIdQuery;
import com.flabum.squidzbackend.reservation.domain.model.queries.GetReservationByIdQuery;
import com.flabum.squidzbackend.reservation.domain.services.ReservationQueryService;
import com.flabum.squidzbackend.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {

    private final ReservationRepository reservationRepository;

    public ReservationQueryServiceImpl(ReservationRepository reservationRepository){

        this.reservationRepository = reservationRepository;
    }


    @Override
    public List<Reservation> handle(GetAllReservationsQuery command) {
        return reservationRepository.findAll();
    }

    /**
     * Query handler to get Reservations bt Id
     * @param command containing id()
     * @return Reservation by id
     */
    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery command) {
        return reservationRepository.findById(command.reservationId());
    }
}
