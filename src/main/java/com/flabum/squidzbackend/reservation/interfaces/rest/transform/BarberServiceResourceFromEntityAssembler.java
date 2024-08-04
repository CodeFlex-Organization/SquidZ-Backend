package com.flabum.squidzbackend.reservation.interfaces.rest.transform;

import com.flabum.squidzbackend.reservation.domain.model.aggregates.Reservation;
import com.flabum.squidzbackend.reservation.domain.model.entities.BarberService;
import com.flabum.squidzbackend.reservation.interfaces.rest.resources.BarberServiceResource;
import com.flabum.squidzbackend.reservation.interfaces.rest.resources.ReservationResource;

public class BarberServiceResourceFromEntityAssembler {
    public static BarberServiceResource toResourceFromEntity(BarberService entity){
        return new BarberServiceResource(entity.getId(), entity.getName(), entity.getPrice(), entity.getDuration());
    }
}
