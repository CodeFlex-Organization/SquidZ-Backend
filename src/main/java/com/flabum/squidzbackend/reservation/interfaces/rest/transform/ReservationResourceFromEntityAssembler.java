package com.flabum.squidzbackend.reservation.interfaces.rest.transform;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.interfaces.rest.resources.ReservationResource;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation entity){
        return new ReservationResource(entity.getId(), entity.getDate(), entity.getTime());
    }
}